package com.xp.brushms.client.test;

import com.cc.ccutils.JsonUtils;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_phantomjs_pages2 {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000032");
        task.setTimeout(180000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        task.addOpenAction("https://www.baidu.com/index.php?tn=mswin_oem_dg");
        task.addSleepAction(10000);

        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, true);" +
                "  ele.dispatchEvent(event);";

        String js = "var ele;" +
                "ele = document.querySelector('#u1 > a:nth-child(2)');" +
                jsEnvent_click;

        task.addRunJsAction(js, "con:baidu", null);
        task.addSleepAction(10000);
        task.addRunJsAction("", "notCon:www.baidu.com,news.baidu.com| blank", null);
        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);
        System.out.println("result:" + JsonUtils.toJson(result));

    }

}
