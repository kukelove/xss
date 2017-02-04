package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_sendEvent {

    public static void main(String[] args) {

        RunTask task = new RunTask();
        task.setTaskId("00000001");
        task.setTimeout(60000);
        task.setWriteLog(true);
        task.setProxy("127.0.0.1:8888");
//        task.setUa("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        task.addOpenAction("https://www.baidu.com/");
        task.addSleepAction(5000);
        task.addRenderAction();

        //获取相对于当前window左上角的的位置
        String js_pos = "function pageXY(elem) {" +
                "            var obj = {};" +
                "            obj.left = elem.offsetParent ? elem.offsetLeft + pageXY(elem.offsetParent).left : elem.offsetLeft;\n" +
                "            obj.top = elem.offsetParent ? elem.offsetTop + pageXY(elem.offsetParent).top : elem.offsetTop;\n" +
                "            return obj;" +
                "        }";

        String js = js_pos +
                "var ele = document.querySelector('#u1 > a:nth-child(2)');" +
                "console.log('ele:' + ele);" +
                "var pos = pageXY(ele);" +
                "console.log('left:' + pos.left+',top:' + pos.top);";

        String js_bak =
                "var ele = document.querySelector('#u1 > a:nth-child(2)');" +
                "console.log('ele:' + ele);" +
                "var left = ele.getBoundingClientRect().left + ele.scrollLeft;" +
                "var top = ele.getBoundingClientRect().top + ele.scrollTop;" +
                "console.log('left:' + left+',top:' + top);";

        task.addRunJsAction(js, null, null);

        String js_before = "console.log('before click document.URL:' + document.URL);";
        task.addRunJsAction(js_before, null, null);
        task.addClickAction(385, 19,null);
        task.addSleepAction(5000);
        String js_after = "console.log('after click document.URL:' + document.URL);";
        task.addRunJsAction(js_after, null, null);
        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);

        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
