package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_pro {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000055");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        task.addOpenAction("http://www.yaomai9.com/xlwc.html");
        task.addSleepAction(20000);
        task.addRenderAction();
        //获取相对于当前window左上角的的位置
        String js_pos = "function pageXY(elem) {" +
                "            var obj = {};" +
                "            obj.left = elem.offsetParent ? elem.offsetLeft + pageXY(elem.offsetParent).left : elem.offsetLeft;\n" +
                "            obj.top = elem.offsetParent ? elem.offsetTop + pageXY(elem.offsetParent).top : elem.offsetTop;\n" +
                "            return obj;" +
                "        }";

        String js =  "var ele = document.getElementsByTagName('iframe')[0];" +
                "var rect = ele.getBoundingClientRect();" +
                "var left = rect.left + ele.scrollLeft;" +
                "var top = rect.top + ele.scrollTop;" +
                "var width = rect.width;" +
                "var height = rect.height;" +
                "console.log('ele left:' + left+',top:' + top+',width:'+width+',height:' + height);";

        task.addRunJsAction(js, null, null);
        task.addSleepAction(5000);
        task.addClickAction(150, 125,null);
        task.addSleepAction(2000);

        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
