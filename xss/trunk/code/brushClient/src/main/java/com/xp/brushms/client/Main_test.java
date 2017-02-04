package com.xp.brushms.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主入口
 */
public class Main_test {

    public static void main(String[] args) {

        System.out.println("usr.dir:" + System.getProperty("user.dir"));

        RunTask task = new RunTask();
        task.setTaskId("0000001");
        task.setTimeout(30000);
        List<Map<String, Object>> actionList = new ArrayList();

        Map<String, Object> action1 = new HashMap();
        action1.put("action", "open");
        action1.put("url", "http://www.baidu.com");

        Map<String, Object> action2 = new HashMap();
        action2.put("action", "sleep");
        action2.put("sleep", "5000");

        actionList.add(action1);
        actionList.add(action2);

        task.setActions(actionList);

        PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);
        System.out.println("success:" + result.success);
        if (result.success) {
            MemCache.success(task.getTaskId());
        } else {
            MemCache.failed(task.getTaskId());
        }

    }

}
