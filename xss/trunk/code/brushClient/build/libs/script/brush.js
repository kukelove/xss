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
//var blank_Page = false;
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
    page.blank_Page = false;
    page.clearCookies();
    if (userAgent) {
        page.settings.userAgent = userAgent;
    }
    registerPageTrigger(page, userAgent, writeLog);
    return page;
}

function registerPageTrigger(page, userAgent, writeLog) {
    page.onPageCreated = function(newPage) {
        page.blank_Page = true;
        //console.log("onPageCreated...");
        //针对新页面打开资源会自动设置phantomjs的ua
        if (userAgent) {
            newPage.customHeaders = {
                "User-Agent": userAgent
            };
        }
        registerPageTrigger(newPage, writeLog);
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

        page.onConsoleMessage = function (msg, lineNum, sourceId) {
            console.log('CONSOLE: ' + msg + ' (from line #' + lineNum + ' in "' + sourceId + '")');
        };

        //page.onResourceRequested = function(requestData, networkRequest) {
        //    console.log('Request (#' + requestData.id + '): ' + requestData.url);
        //};
    }
}

function waitFor(condition, onReady, timeOutMillis) {
        var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 5000, //< Default Max Timout is 5s
        start = new Date().getTime(),
        conditionResult = false,
        interval = setInterval(function() {
            if ( (new Date().getTime() - start < maxtimeOutMillis) && !conditionResult ) {
                conditionResult = (typeof(condition) === "string" ? eval(condition) : condition()); //< defensive code
            } else {
                clearInterval(interval); //< Stop this interval
                if(!conditionResult) {
                    // If condition still not fulfilled (timeout but condition is 'false')
                    console.log("'waitFor()' timeout");
                    //phantom.exit(1);
                } else {
                    // Condition fulfilled (timeout and/or condition is 'true')
                    console.log("'waitFor()' finished in " + (new Date().getTime() - start) + "ms.");
                    typeof(onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                }
            }
        }, 50); //< repeat check every 50ms
};


function renderPage(page, path) {
    var t = new Date();
    var tn = t.getFullYear() + "-" + (t.getMonth() + 1) + "-" + t.getDate() + " " + t.getHours() + "-" + t.getMinutes() + "-" + t.getSeconds() + "-" + t.getMilliseconds();
    path = path + "/" + t.getFullYear() + "-" + (t.getMonth() + 1) + "-" + t.getDate();
    if(page.pages && page.pages[0]) {
        console.log("render......child page url:" + page.pages[0].url);
        page.pages[0].render(path + "/" + tn + '.png');
     } else {
        console.log("render......main page url:" + page.url);
        page.render(path + "/" + tn + '.png');
    }
}

//获取最新页面的url
function newestPageUrl() {
    //page.pages 存储该page的子页面，page.pages[0]是最新打开的子页面。但是该方法不稳定：经常获取不到url
   /* if(page.pages && page.pages[0]) {
        return page.pages[0].url;
    } else {
        return page.url;
    }*/

    //获取主页url
    return page.url;
}

/**
 * matchUrl格式：blank|notCon:xxx1,xxx2|con:xxx|con:xxx
 * |分隔的规则，只要有一个结果为true则表示成功。
 * blank: 有子页面时为true
 * notCon:xxx,xxx : 不包含xxx1和xxx2是为true
 * con:xxx: 包含xxx时为true
 * @param matchUrl
 */
function matchUrl(matchUrl) {
    var pageUrl = newestPageUrl();
    if (!pageUrl) {
        Go("failed", "match-failed: " + matchUrl);
    }
    var match = false;
    var matchCons = matchUrl.split("|");
    for (var i = 0; i < matchCons.length; i++) {
        var matchCon = matchCons[i].trim();
        if(matchCon == "blank") {                       //判断是否打开过新页面
            console.log(task.taskId + " blank_page:" + page.blank_Page);
            if(page.blank_Page){
                match = true;
                break;
            }
        } else if (matchCon.indexOf("notCon:") >= 0) {      //非包含
            var matchT = true;
            matchCon = matchCon.substr(7);
            var notCons = matchCon.split(/[,，]/);
            for(var m = 0;m < notCons.length; m++) {
                var notCon = notCons[m].trim();
                if(pageUrl.indexOf(notCon) >= 0) {
                    matchT = false;
                    break;
                }
            }
            if(matchT) {
                match = true;
                break;
            }

        } else if (matchCon.indexOf("con:") >= 0) {      //包含
            matchCon = matchCon.substr(4);
            if(pageUrl.indexOf(matchCon) >= 0) {
                match = true;
                break;
            }
        }
    }
    if(!match) {
        Go("failed", "match-failed: " + matchUrl);
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
    console.log("failed_"+ task.taskId+": " + p);
    phantom.exit();
});

RegisterHandler("success", function () {
    console.log("success");
    phantom.exit();
});

RegisterHandler("next", function () {
    //console.log("next: " + actionIndex);
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
        } else if (action.action == "switch-to-page") {
            //waitFor(function condition(){
            //    return page.pages && page.pages[0];
            //}, function ready(){
            //    var pageT = page.pages[action.pageIndex];
            //    //console.log("pageT:" + JSON.stringify(pageT));
            //    if(pageT) {
            //        page = pageT;
            //    } else {
            //        console.log("result-" + (actionIndex - 1) + ": switch-to-page failed, page not found");
            //    }
            //},5000);
            //GoAfter("next",5000);
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