package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_phantomjs {

    public static void main(String[] args) {

        String jsEnvent_click = "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, false);" +
                "  ele.dispatchEvent(event);";

        String jsEvent_click_new = "var event = new MouseEvent('click', {'view': window,'bubbles': true,'cancelable': true});" +
                "ele.dispatchEvent(event);";

        RunTask task = new RunTask();
        task.setTaskId("00000001");
        task.setTimeout(180000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        task.addOpenAction("http://cdn.yaomai9.com/ads/jx_banner_2.html");
        task.addSleepAction(10000);

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
        task.addSleepAction(10000);

        String js2 = "console.log('cur url:' + document.URL);";
        task.addRunJsAction(js2,"blank | notCon: http://cdn.yaomai9.com",null);
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
