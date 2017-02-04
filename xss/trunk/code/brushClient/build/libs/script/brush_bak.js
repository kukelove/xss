var system = require('system');
var confContent = system.args[1];
var nc = "";
for (var i = 0; i < confContent.length; i += 2) {
    nc += "%" + confContent.substr(i, 2);
}

var task = JSON.parse(decodeURIComponent(nc));
var timeout = task.timeout;
setTimeout(function () {
    Go("failed", "timeout");
}, timeout);

initPhantom(task.proxy);
var blank_Page = false;
var page = buildPage(task.ua, task.writeLog);
var actions = task.actions;
var actionIndex = 0;
var result = null;

function initPhantom(proxy) {
    phantom.clearCookies();
    if (proxy) {
        var ipPort = task.proxy.split(":");
        phantom.setProxy(ipPort[0], ipPort[1], '', '', '');
    }
}

function buildPage(userAgent, writeLog) {
    var page = require("webpage").create();
    page.clearCookies();
    if (userAgent) {
        page.settings.userAgent = userAgent;
    }
    //针对新页面打开资源会自动设置phantomjs的ua
    page.onPageCreated = function(newPage) {
        //console.log('A new child page was created! Its requested URL is not yet available, though.');
        blank_Page = true;
        if (userAgent) {
            newPage.customHeaders = {
                "User-Agent": userAgent
            };
        }
    };

    if (writeLog) {
        /**
         * This callback is invoked when there is a JavaScript execution error.
         * It is a good way to catch problems when evaluating a script in the web page context.
         */
        page.onError = function (msg, trace) {
            var msgStack = ['ERROR: ' + msg];
            if (trace && trace.length) {
                msgStack.push('TRACE:');
                trace.forEach(function (t) {
                    msgStack.push(' -> ' + t.file + ': ' + t.line + (t.function ? ' (in function "' + t.function + '")' : ''));
                });
            }
            console.error(msgStack.join('\n'));
        };

        //page.onResourceRequested = function(requestData, networkRequest) {
        //    console.log('Request (#' + requestData.id + '): ' + requestData.url);
        //};
        //page.onResourceReceived = function(response) {
        //    console.log('Response (#' + response.url + ', stage "' + response.stage + '") ');
        //};
        //page.onResourceError = function(resourceError) {
        //    console.log('Unable to load resource (#' + resourceError.id + 'URL:' + resourceError.url + ')');
        //    console.log('Error code: ' + resourceError.errorCode + '. Description: ' + resourceError.errorString);
        //};
        //page.onResourceTimeout = function(request) {
        //    console.log('Response (#' + request.id + '): ' + requestData.url);
        //};

        page.onConsoleMessage = function (msg, lineNum, sourceId) {
            console.log('CONSOLE: ' + msg + ' (from line #' + lineNum + ' in "' + sourceId + '")');
        };
    }

    return page;
}

function renderPage(page, path) {
    var t = new Date();
    var tn = t.getFullYear() + "-" + (t.getMonth() + 1) + "-" + t.getDate() + " " + t.getHours() + "-" + t.getMinutes() + "-" + t.getSeconds() + "-" + t.getMilliseconds();
    page.render(path + "/" + tn + '.png');
}

function matchUrl(matchUrl) {
    if(matchUrl.trim() == "blank"){
        console.log("blank:"+blank_Page)
        if(!blank_Page){
            Go("failed", "match-failed:" + matchUrl);
        }
    }else{
        if (!page.url) {
            Go("failed", "match-failed:" + matchUrl);
        }
        var match = false;
        var matchCons = matchUrl.split("|");
        for (var i = 0; i < matchCons.length; i++) {
            if (page.url.indexOf(matchCons[i].trim()) == -1) {
                continue;
            } else {
                match = true;
            }
        }
        for (var i = 0; i < matchCons.length; i++) {
            if (page.url.indexOf(matchCons[i].trim()) == -1) {
                continue;
            } else {
                match = true;
            }
        }
        if(!match) {
            Go("failed", "match-failed: " + matchUrl);
        }
    }

}

/**
 * 依据checkCode判断是否需要执行action
 * checkCode 返回true/false/exit-success/exit-failed
 * @param checkCode
 */
function preAction(checkCode) {
    var checkResult = page.evaluateJavaScript("function(){" + checkCode + "}");
    if(typeof(checkResult) == "string") {
        if(checkResult.trim() == "true") {
            return true;
        } else if(checkResult.trim() == "false") {
            return false;
        } else if(checkResult.trim() == "exit-success") {
            Go('success');
            return;
        } else if(checkResult.trim() == "exit-failed") {
            Go('failed', 'checkCode exit-failed,action index:' + (actionIndex - 1));
            return;
        }
            return false;
    }
    if(checkResult === true) {
        return true;
    } else
        return false;
}



var statusHandlers = {};
function RegisterHandler(status, handler) {
    statusHandlers[status] = handler;
}

//在jquery环境下执行callback()
//引入jquery很容易导致js冲突
function InjectJQuery(callback) {
    var jqueryTag = page.evaluate(function () {
        if (window.jQuery) return true;
        else return false;
    });
    if (jqueryTag) {
        callback();
    } else {
        if (page.injectJs("jquery.min.js")) {
            callback();
        } else {
            Go("failed", "jquery-failed");
        }
    }
}

function notInjectJQuery(callback) {
    callback();
}

function Go(status, ps) {
    var handler = statusHandlers[status];
    if (handler) {
        handler(ps);
    }
}
function GoAfter(status, timeout, ps) {
    setTimeout(function () {
        Go(status, ps);
    }, timeout);
}

RegisterHandler("failed", function (p) {
    console.log("failed: " + p);
    phantom.exit();
});

RegisterHandler("success", function () {
    console.log("success");
    phantom.exit();
});

RegisterHandler("next", function () {
    console.log("next: " + actionIndex);
    if (actionIndex >= actions.length) {
        Go("success");
        return;
    }
    var action = actions[actionIndex];
    actionIndex++;

    //当前action是否需要执行
    var needExecute = true;
    if (action.checkCode) {
        needExecute = preAction(action.checkCode);
    }

    if (needExecute) {
        if (action.action == "open") {
            page.open(action.url, function (status) {
                //if (status != "success") {
                //    Go("failed", "open-failed");
                //    return;
                //}
                //Go("next");
            });
            Go("next");
        } else if (action.action == "sleep") {
            GoAfter("next", action.timeout);
        } else if (action.action == "run-js") {
            if (action.matchUrl) {
                matchUrl(action.matchUrl);
            }
            notInjectJQuery(function () {
                result = page.evaluateJavaScript("function(){" + action.code + "}");
                if (result) {
                    if(typeof(result) == "string") {
                        if(result.trim() == "exit-success") {
                            GoAfter("success", 5000);
                            return;
                        }else if(result.trim() == "exit-failed") {
                            Go("failed","run-js failed! action index:" + (actionIndex - 1));
                            return;
                        }
                    }else {
                        result = JSON.stringify(result);
                    }
                    console.log("result-" + (actionIndex - 1) + ": " + result);
                }
                Go("next");
            });
        } else if (action.action == "render") {
            renderPage(page, "../images/" + task.taskId);
            Go("next");
        } else if (action.action == "switch-to-frame") {
            page.switchToFrame(action.frame);
            Go("next");
        } else if (action.action == "click") {
            page.sendEvent('click', action.mouseX, action.mouseY, button='left');
            Go("next");
        }
    } else {
        console.log("result-" + (actionIndex - 1) + ": not need execute");
        Go("next");
    }
});

Go("next");