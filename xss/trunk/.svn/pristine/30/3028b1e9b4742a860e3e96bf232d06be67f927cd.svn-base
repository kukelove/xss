package com.xp.brushms.client.test;

import com.cc.ccutils.JsonUtils;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_qunaer_nzwxsc3 {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000001");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        task.addOpenAction("http://touch.qunar.com/?bd_source=nzwxsc3");
        task.addSleepAction(15000);

        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, true);" +
                "  ele.dispatchEvent(event);";

        String jsEnvent_tap =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('tap', true, true);" +
                "  ele.dispatchEvent(event);";

        String js_1 = "var url = document.URL;" +
                "var ele;" +
                "var eles;" +
                "eles = document.querySelectorAll('div.qn_nav > ul > li > a');" +
                "if(eles.length == 0) {return 'exit-failed';}" +
                "var ele = eles[Math.round(Math.random() * (eles.length-1))];"
                + jsEnvent_click;

        task.addRunJsAction(js_1, null, null);
        task.addSleepAction(15000);
        task.addRenderAction();

        task.addRunJsAction("","notCon:touch.qunar.com/?bd_source=nzwxsc3",null);

        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);
        System.out.println("result:" + JsonUtils.toJson(result));

    }

}
