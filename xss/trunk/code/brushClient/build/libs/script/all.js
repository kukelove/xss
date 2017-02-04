var system = require('system');
var confContent = system.args[1];
var debug = false;
if (system.args.length > 2) {
    debug = system.args[2] == 'debug';
}
var nc = "";
for (var i = 0; i < confContent.length; i+=2) {
    nc += "%" + confContent.substr(i, 2);
}
var config = JSON.parse(decodeURIComponent(nc));
var timeout = config.timeout;
var actions = config.actions;

setTimeout(function(){
    Go("failed", timeout);
}, timeout);
var actionIndex = 0;
var result = null;
var page = require('webpage').create();
page.settings.userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
if (config.userAgent) {
    page.settings.userAgent = config.userAgent;
}
var noJquery = false;
if (config.noJquery) {
    noJquery = true;
}

var statusHandlers = {};
function RegisterHandler(status, handler) {
    statusHandlers[status] = handler;
}
function InjectJQuery(callback) {
    if (noJquery) {
        callback();
        return;
    }
    var jqueryTag = page.evaluate(function(){
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
function Go(status, ps) {
    var handler = statusHandlers[status];
    if (handler) {
        handler(ps);
    }
}
function GoAfter(status, timeout, ps) {
    setTimeout(function(){
        Go(status, ps);
    }, timeout);
}

RegisterHandler("failed", function(p){
    console.log("failed: " + p);
    phantom.exit();
});

RegisterHandler("success", function(){
    console.log("success");
    phantom.exit();
});

RegisterHandler("next", function() {
    if (debug) {
        console.log("next: " + actionIndex + " " + page.url);
        if (actionIndex > 0) {
            page.render(actionIndex + ".png");
        }
    } else {
        console.log("next: " + actionIndex);
    }
    if (actionIndex >= actions.length) {
        Go("success");
        return;
    }
    var action = actions[actionIndex];
    actionIndex++;
    if (action.action == "open") {
        page.open(action.url, function(status) {
        });
        Go("next");
    } else if (action.action == "sleep") {
        GoAfter("next", action.sleep);
    } else if (action.action == "run-js") {
        if (action.matchUrl) {
            if (!page.url || page.url.indexOf(action.matchUrl) != 0) {
                Go("failed", "match-failed");
                return;
            }
        }
        InjectJQuery(function(){
            result = page.evaluateJavaScript("function(){" + action.code + "}");
            if (result) {
                if (typeof(result) == "object" && result.type=="go-next") {
                    actionIndex = result.next;
                    Go("next");
                    return;
                }
                if (typeof(result) != "string") {
                    result = JSON.stringify(result);
                }
                console.log("result-" + (actionIndex-1) + ": " + result);
            }
            Go("next");
        });
    }
});



Go("next");