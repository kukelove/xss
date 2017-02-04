package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_google_app_sendEvent {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("0000001");
        task.setTimeout(180000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        task.addOpenAction("http://cdn.yaomai9.com/ads/google-320x50-1.html");
        task.addSleepAction(30000);
        task.addRenderAction();

        String js =
                "var ele = document.getElementsByTagName('iframe')[0];" +
//                "var ele = document.getElementById('aswift_0_expand');" +
                "var rect = ele.getBoundingClientRect();" +
                "var left = rect.left + ele.scrollLeft;" +
                "var top = rect.top + ele.scrollTop;" +
                "var width = rect.width;" +
                "var height = rect.height;" +
                "console.log('ele left:' + left+',top:' + top+',width:'+width+',height:' + height);";

        task.addRunJsAction(js, null, null);
        task.addSleepAction(5000);
//
        String checkCode = "var frame_1 = window.frames[0];" +
                "var frame_2 = frame_1.frames['google_ads_frame1'];" +
                "if(!frame_2) {return 'exit-failed';}" +
                "var eles = frame_2.document.querySelectorAll('#google_image_div','#adunit','#adContent','.GoogleActiveViewClass');" +
                "if(!eles || eles.length == 0) {" +
                "   return 'exit-failed'" +
                "} else {" +
                "   return true" +
                "}";

        task.addClickAction(40 + 150, 142 + 25, checkCode);
        task.addSleepAction(10000);

        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
