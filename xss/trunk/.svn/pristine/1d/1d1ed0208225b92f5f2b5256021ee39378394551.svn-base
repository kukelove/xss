package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_juxiao {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000001");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
//        task.addOpenAction("http://120.76.101.72:8343/track/api/v0/click/144e?am=00001076&loc=00000093&u=http%3A%2F%2Fcdn.yaomai9.com%2Fads%2Fjx-300-250-1.html");
        task.addOpenAction("http://cdn.yaomai9.com/ads/jx-300-250-1.html");
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

//        task.addRunJsAction(js, null, null);
//        task.addSleepAction(30000);

        task.addClickAction(150, 145, null);
        task.addSleepAction(30000);
        task.addRenderAction();
        task.addRunJsAction("","blank",null);


        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
