package com.xp.brushms.client.test;

import com.cc.ccutils.JsonUtils;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_jd {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000032");
        task.setTimeout(60000);
        task.setWriteLog(true);
        task.setLoadImages(false);
//        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        task.setUa("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1);");
        task.addOpenAction("http://mc.funshion.com/interface/mc?mcid=3118&source=f1");
        task.addSleepAction(30000);

        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, true);" +
                "  ele.dispatchEvent(event);";

        String js = "var eles;" +
                "eles = document.querySelectorAll('#navitems-2014 > ul > li > a');" +
                "console.log('eles length:' + eles.length);" +
                "if(eles.length == 0) {return 'exit-failed';}" +
                "var ele = eles[Math.round(Math.random() * eles.length)];" +
                "console.log('ele href:' + ele.getAttribute('href'));" +
                jsEnvent_click;

        task.addRenderAction();
        task.addRunJsAction(js, null, null);
        task.addSleepAction(5000);
        task.addRunJsAction("","blank",null);
        task.addRenderAction();

        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);
        System.out.println("result:" + JsonUtils.toJson(result));

    }

}
