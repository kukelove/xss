package com.xp.brushms.client;

import com.cc.ccutils.JsonUtils;
import com.xp.brushms.client.utils.HttpUtils;
import com.xp.brushms.client.utils.ThreadUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 主入口
 */
public class Main_bak {

    private static LinkedList<RunTask> taskQueue = new LinkedList();     //任务队列
    private static final int fetchNum = 200;     //每次获取任务数

    private static final int threadNum = 100; //刷量并发数

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 接口地址：http://192.168.11.10:8012/api/wm/v0/get_ad_active_tasks.api?mac=FC-AA-14-4D-E0-3A&count=1
     */
    public static void main(String[] args) throws Exception {

//        //上报日志
        ThreadUtil.executeMore(new Runnable() {
            public void run() {
                reportTaskResults();
            }
        });

        //获取任务
        ThreadUtil.executeMore(new Runnable() {
            public void run() {
                fetchTask();
            }
        });

        //执行任务
        for (int i=0; i < threadNum; i++) {
            ThreadUtil.executeMore(new Runnable() {
                public void run() {
                    doAllTask();
                }
            });
        }

    }

    private static void doAllTask() {

        RunTask task = null;

        while (true) {
            try {
                synchronized (taskQueue) {
                    task = taskQueue.poll();
                }
                if(task == null) {
                    Thread.sleep(1000);
                    continue;
                }
                PhantomJsUtils.TaskResult result = PhantomJsUtils.execute(task);
                if(result.success) {
                    MemCache.success(task.getTaskId());
                } else {
                    MemCache.failed(task.getTaskId());
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }finally {
                task = null;
            }

        }
    }

    //从服务端获取任务
    private static void fetchTask() {
        // 任务接口
//        String api = "http://192.168.11.10:8012/api/wm/v0/get_ad_active_tasks.api?mac=FC-AA-14-4D-E0-3A&count=" + fetchNum;
        String api = "http://183.250.161.227:10002/api/wm/v0/get_ad_active_tasks.api?mac=FC-AA-14-4D-E0-3A&count=" + fetchNum;

        while (true) {
            try {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean needFetch = false;
                synchronized (taskQueue) {
                    if(taskQueue.size() < fetchNum) {
                        needFetch = true;
                    }
                }
                if(needFetch) {
                    String taskJson = HttpUtils.getAsString(api);
                    if (taskJson == null || taskJson.trim().equals("[]")) {
                        System.out.println(" 没有数据 " + dateFormat.format(new Date()));
                        continue;
                    }

                    List<RunTask> runTasks = JsonUtils.parseJsonList(taskJson, RunTask.class);
                    if (runTasks == null || runTasks.size() == 0) {
                        System.out.println(" 没有数据 " + dateFormat.format(new Date()));
                        continue;
                    }
                    //
                    for (RunTask runTask : runTasks) {
                        List<Map<String, Object>> actions = runTask.getActions();
                        if(actions != null) {
                            for (Map<String, Object> action : actions) {
                                if(action.get("action").toString().trim().equals("switch-to-frame")){
                                    Object frame = action.get("frame");
                                    if(null != frame && StringUtils.isNumeric(frame.toString().trim())) {
                                        action.put("frame", Integer.parseInt(frame.toString().trim()));
                                    }
                                }
                            }
                        }
                    }

                    synchronized (taskQueue) {
                        taskQueue.addAll(runTasks);
                    }
                }
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }


    //上报任务日志
    private static void reportTaskResults() {

        // 任务接口
        String api = "http://183.250.161.227:10002/api/wm/v0/report_task_result.api";
//        String api = "http://192.168.2.24:8012/api/wm/v0/report_task_result.api";

//        Gson gson = new Gson();

        while (true) {
            try {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<TaskResultStat> taskResultStats = MemCache.swapTaskResults();
                if(taskResultStats == null || taskResultStats.size() == 0) {
                    continue;
                }
//                HttpUtils.postJsonAsString(api, gson.toJson(taskResultStats));
                HttpUtils.postJsonAsString(api,JsonUtils.toJson(taskResultStats));
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }

    }

}