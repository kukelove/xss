package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_haiyun_wap {

    public static void main(String[] args) {

        String jsEnvent_click = "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, false);" +
                "  ele.dispatchEvent(event);";

        String jsEvent_click_new = "var event = new MouseEvent('click', {'view': window,'bubbles': true,'cancelable': true});" +
                "ele.dispatchEvent(event);";

        RunTask task = new RunTask();
        task.setTaskId("00000061");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
        task.addOpenAction("http://120.76.101.72:8343/track/api/v0/click/1ef1?am=00001098&loc=00000093&u=http%3A%2F%2Fcdn.yaomai9.com%2Fads%2Fhaiyun-banner-1.html");
        task.addSleepAction(20000);
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

        task.addClickAction(0 + 200, 210 + 47, null);
        task.addSleepAction(10000);

        task.addRunJsAction("", "blank", null);
//        task.addRenderAction();
        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
