package com.xp.brushms.client.test;

import com.xp.brushms.client.MemCache;
import com.xp.brushms.client.PhantomJsUtils;
import com.xp.brushms.client.RunTask;

/**
 * 主入口
 */
public class Test_pro_iframe_test {

    public static void main(String[] args) {

//        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
//                "  event.initEvent('click', true, true);" +
//                "  ele.dispatchEvent(event);";
        String jsEnvent_click =  "var theEvent = document.createEvent('MouseEvent');" +
                "            theEvent.initMouseEvent('click', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "            ele.dispatchEvent(theEvent);";

        RunTask task = new RunTask();
        task.setTaskId("00000055");
        task.setTimeout(120000);
        task.setWriteLog(true);
//        task.setProxy("112.5.16.35:2999");
        task.setProxy("127.0.0.1:8888");
        task.setUa("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        task.addOpenAction("http://www.yaomai9.com/xlwc.html");
        task.addSleepAction(30000);
        task.addRenderAction();

        String js = "console.log('window location:' + window.location);" +
                "console.log('window title:' + window.title);" +
                "console.log('window frames length:' + window.frames.length);" +
                "var frames_obj = document.getElementsByTagName('iframe');" +
                "for(var i=0;i<frames_obj.length;i++) {" +
                "var frame_obj = frames_obj[i];" +
                "console.log('frame_obj src:' + frame_obj.getAttribute('src'));" +
                "}" +
                "var frames_win = window.frames;" +
                "for(var i=0;i<frames_win.length;i++) {" +
                "var frame_win = frames_win[i];" +
                "console.log('frame_win location:' + frame_win.location);" +
                "console.log('frame_win document:' + frame_win.document);" +
//                "console.log('frame document body innerHTML:' + frame.document.body.innerHTML);" +
                "}" +
                "var frame_sub = frames_win[0];" +
                "var frame_sub_frames = frame_sub.frames;" +
                "console.log('frame_sub_frames length:' + frame_sub_frames.length);" +
                "for(var i=0;i<frame_sub_frames.length;i++) {" +
                "var frame_win_sub = frame_sub_frames[i];" +
                "console.log('frame_win_sub location:' + frame_win_sub.location);" +
                "}" +
                "var frame_sub_2 = frame_sub_frames[1];" +
                "var frames_final = frame_sub_2.frames;" +
                "console.log('frames_final length:' + frames_final.length);" +
                "var frame_final = frames_final[1];" +
                "console.log('frames_final_1 location:' + frames_final[0].location);" +
                "console.log('frames_final_2 location:' + frames_final[1].location);" +
                "var eles = frame_final.document.querySelectorAll('body > div > a');" +
                "console.log('a length:' + eles.length);" +
                "var ele = eles[0];" +
                "console.log('ele:' + ele);" +
                "console.log('ele href:' + ele.getAttribute('href'));" +
                "ele.target = '_self';"+
                "console.log('ele target:' + ele.getAttribute('target'));"+
                jsEnvent_click;

        task.addRunJsAction(js, null,null);

//        task.addSwitchToFrameAction(0, null);
//
//        task.addSleepAction(2000);
//        task.addRunJsAction("console.log('URL:' + document.location.href);",null,null);
       /* String checkCode = "var eles = document.querySelectorAll('body > div > a'); console.log('URL:' + document.URL);console.log('eles.length:' + eles.length);if (eles.length == 0) return true;";
        task.addSwitchToFrameAction(0, checkCode);
        task.addSleepAction(2000);
        task.addSwitchToFrameAction(0, checkCode);
        task.addSleepAction(2000);
        String jsEnvent_click =  "  var event = document.createEvent('Events');" +
                "  event.initEvent('click', true, true);" +
                "  ele.dispatchEvent(event);";

        String js = "var eles = document.getElementsByTagName('a');" +
                "console.log('URL:' + document.URL);" +
                "console.log('js eles.length:' + eles.length);"+
                "            if(eles.length == 0) {" +
                "                return;" +
                "            }" +
                "            var ele = eles[0];" + jsEnvent_click;

        task.addRunJsAction(js, null, null);*/
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
