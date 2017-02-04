package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_render {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("0000001");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        task.addOpenAction("http://news.baidu.com/");
        task.addSleepAction(10000);
        task.addRenderAction();

        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, true);" +
                "  ele.dispatchEvent(event);";

        String js =  "var ele = document.querySelector('#channel-all > div > ul > li:nth-child(5) > a');" +
                jsEnvent_click;

        task.addRunJsAction(js, null, null);
        task.addSleepAction(20000);
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
