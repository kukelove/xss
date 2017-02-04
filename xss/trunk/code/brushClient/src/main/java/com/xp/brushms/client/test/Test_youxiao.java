package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_youxiao {

    public static void main(String[] args) {

        String jsEnvent_click = "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, false);" +
                "  ele.dispatchEvent(event);";

        String jsEvent_click_new = "var event = new MouseEvent('click', {'view': window,'bubbles': true,'cancelable': true});" +
                "ele.dispatchEvent(event);";

        RunTask task = new RunTask();
        task.setTaskId("00000063");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
//        task.setUa("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
        task.setUa("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        task.addOpenAction("http://cdn.yaomai9.com/ads/jx_banner_2.html");
        task.addSleepAction(30000);
        task.addRenderAction();

        String js =  "var ele = document.querySelector('#showContBox > div > img');" +
                "if(!ele) {ele = document.querySelector('.SspCon')}" +
                "if(!ele) {console.log('ele not find');return;}" +
                "var rect = ele.getBoundingClientRect();" +
                "var left = rect.left + ele.scrollLeft;" +
                "var top = rect.top + ele.scrollTop;" +
                "var width = rect.width;" +
                "var height = rect.height;" +
                "console.log('ele left:' + left+',top:' + top+',width:'+width+',height:' + height);";

        task.addRunJsAction(js, null, null);
        task.addSleepAction(5000);

        task.addClickAction(0 + 200, 238 + 31, null);
        task.addSleepAction(20000);
        task.addRunJsAction("", "blank", null);
        task.addRenderAction();
        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
