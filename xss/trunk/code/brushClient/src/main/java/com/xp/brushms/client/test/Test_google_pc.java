package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_google_pc {

    public static void main(String[] args) {

        String jsEnvent_click = "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, false);" +
                "  ele.dispatchEvent(event);";

        RunTask task = new RunTask();
        task.setTaskId("00000057");
        task.setTimeout(180000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
        task.addOpenAction("http://cdn.yaomai9.com/ads/google-300x250-1.html");
        task.addSleepAction(30000);
        task.addRenderAction();

        String js =  "var frame_1 = window.frames[0];" +
                "var frame_2 = frame_1.frames['google_ads_frame1'];" +
                "console.log('frame_2:' + frame_2);" +
                "if(!frame_2) {return 'exit-failed';}" +
                "console.log('frame url:' + frame_2.document.URL);" +
                "var eles = frame_2.document.querySelectorAll('a');" +
                "console.log('eles length:' + eles.length);" +
                "if(!eles || eles.length == 0) {" +
                "   return 'exit-failed';" +
                "}" +
                "var ele;" +
                "for(var i=0;i<eles.length;i++) {" +
                "   var href = eles[i].getAttribute('href');" +
                "   if(href && href.indexOf('aclk?sa=l&ai=') >= 0) {" +
                "       console.log('href:' + href);" +
                "       console.log('target:' + eles[i].target);" +
                "       ele = eles[i];" +
                "       break;" +
                "   }" +
                "}" +
                "if(!ele) {" +
                "   return 'exit-failed';" +
                "}" +
                "if(ele.target == '_blank') {" +
                "   ele.target = '_self';" +
                "}" +
                jsEnvent_click;

        task.addRunJsAction(js, null, null);
        task.addSleepAction(5000);
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