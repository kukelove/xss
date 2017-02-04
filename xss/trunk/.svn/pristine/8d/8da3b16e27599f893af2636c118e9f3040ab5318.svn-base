package com.xp.brushms.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Skyline on 2016/7/16.
 */
public class MemCache {

    private static Map<String,TaskResultStat> taskResultStatMap = new HashMap();


    public static void success(String taskId) {
        synchronized (taskResultStatMap) {
            TaskResultStat result = taskResultStatMap.get(taskId);
            if(result == null) {
                result = new TaskResultStat();
                result.setTaskId(taskId);
            }
            result.setSuccess(result.getSuccess() + 1);
            taskResultStatMap.put(taskId, result);
        }
    }

    public static void failed(String taskId) {
        synchronized (taskResultStatMap) {
            TaskResultStat result = taskResultStatMap.get(taskId);
            if(result == null) {
                result = new TaskResultStat();
                result.setTaskId(taskId);
            }
            result.setFailed(result.getFailed() + 1);
            taskResultStatMap.put(taskId, result);
        }
    }

    public static List<TaskResultStat> swapTaskResults() {
        synchronized (taskResultStatMap) {
            List<TaskResultStat> ret = new ArrayList();
           /* for (TaskResultStat taskResultStat : taskResultStatMap.values()) {
                ret.add(taskResultStat);
            }*/
            ret.addAll(taskResultStatMap.values().stream().collect(Collectors.toList()));
            taskResultStatMap = new HashMap();
            return ret;
        }
    }


}
