package com.xp.brushms.service;

import com.xp.brushms.entity.AdActiveTaskLog;
import com.xp.brushms.entity.HourAdTaskReport;
import com.xp.brushms.entity.TaskResultStat;
import com.xp.brushms.entity.WorkMachine;
import com.xp.brushms.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by huangzhimin on 16/5/27.
 */
@Service("memTaskService")
public class MemTaskServiceImpl implements MemTaskService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    protected AdActiveTaskService adActiveTaskService;

    @Autowired
    protected ReportAdTaskService reportAdTaskService;

    private LinkedList<AdActiveTaskLog> logs = new LinkedList<AdActiveTaskLog>();
    private Object lockLogs = new Object();
    private List<TaskResult> taskResults = new ArrayList<TaskResult>();
    private Object lockTR = new Object();
    static class TaskResult {
        public String taskId;
        public long failed;
        public long success;
        public long requestCount;
        public long runCount;
        public long noRunCount;
        public Date created;
    }
    private void pushTaskResult(TaskResult tr) {
        synchronized (lockTR) {
            taskResults.add(tr);
        }
    }
    private void pushTaskResults(Collection<TaskResult> trs) {
        synchronized (lockTR) {
            taskResults.addAll(trs);
        }
    }
    private List<TaskResult> swapTaskResults() {
        synchronized (lockTR) {
            List<TaskResult> ret = taskResults;
            taskResults = new ArrayList<TaskResult>();
            return ret;
        }
    }
    private void remoteExpireTasks() {
        Date now = new Date();
        List<AdActiveTaskLog> removed = new ArrayList<AdActiveTaskLog>();
        synchronized (lockLogs) {
            Iterator<AdActiveTaskLog> iter = logs.iterator();
            while (iter.hasNext()) {
                AdActiveTaskLog t = iter.next();
                if (now.getTime() - t.getCreated().getTime() > 300000) {
                    iter.remove();
                    removed.add(t);
                } else {
                    break;
                }
            }
        }

        Map<String, TaskResult> trs = new HashMap<String, TaskResult>();
        for (AdActiveTaskLog t : removed) {
            TaskResult tr = trs.get(t.getTaskId());
            if (tr == null) {
                tr = new TaskResult();
                tr.taskId = t.getTaskId();
                tr.created = t.getCreated();
                trs.put(tr.taskId, tr);
            }
            tr.noRunCount += 1;
        }
        for (TaskResult tr : trs.values()) {
            pushTaskResult(tr);
        }
    }

    private List<AdActiveTaskLog> getTasks(int machineType, int count) {
        remoteExpireTasks();
        List<AdActiveTaskLog>  removed = new ArrayList<AdActiveTaskLog>();
        long now = new Date().getTime();
        synchronized (lockLogs) {
            Iterator<AdActiveTaskLog> iter = logs.iterator();
            while (iter.hasNext() && removed.size() < count) {
                AdActiveTaskLog t = iter.next();
                if (t.getMachineType() == machineType && t.getCreated().getTime() <= now
                        ) {
                    iter.remove();
                    removed.add(t);
                }
            }
        }
        Map<String, TaskResult> trs = new HashMap<String, TaskResult>();
        for (AdActiveTaskLog t : removed) {
            TaskResult tr = trs.get(t.getTaskId());
            if (tr == null) {
                tr = new TaskResult();
                tr.taskId = t.getTaskId();
                tr.created = t.getCreated();
                trs.put(tr.taskId, tr);
            }
            tr.runCount += 1;
        }
        for (TaskResult tr : trs.values()) {
            pushTaskResult(tr);
        }
        return removed;
    }

    private void appendTask(AdActiveTaskLog log) {
        synchronized (lockLogs) {
            logs.add(log);
        }
        TaskResult tr = new TaskResult();
        tr.created = log.getCreated();
        tr.requestCount = 1;
        tr.taskId = log.getTaskId();
        pushTaskResult(tr);
    }

    private void appendTasks(List<AdActiveTaskLog> mlogs) {
        synchronized (lockLogs) {
            logs.addAll(mlogs);
        }
        Map<String, TaskResult> trs = new HashMap<String, TaskResult>();
        for (AdActiveTaskLog t : mlogs) {
            TaskResult tr = trs.get(t.getTaskId());
            if (tr == null) {
                tr = new TaskResult();
                tr.taskId = t.getTaskId();
                tr.created = t.getCreated();
                trs.put(tr.taskId, tr);
            }
            tr.requestCount += 1;
        }
        for (TaskResult tr : trs.values()) {
            pushTaskResult(tr);
        }
    }

    @Override
    public void createTask(AdActiveTaskLog log) {
        appendTask(log);
    }

    @Override
    public void createTasks(List<AdActiveTaskLog> logs) {
        appendTasks(logs);
    }

    @Override
    public List<AdActiveTaskLog> getTasks(WorkMachine wm, int count) {
        return getTasks(wm.getMachineType(), count);
    }

    @Override
    public void receiveTaskResults(List<TaskResultStat> results) {
        if(results == null || results.size() == 0) {
            return;
        }

        Map<String,TaskResult> resultMap = new HashMap();

        for (TaskResultStat result : results) {
            TaskResult taskResult = resultMap.get(result.getTaskId());
            if(taskResult == null) {
                taskResult = new TaskResult();
                taskResult.taskId = result.getTaskId();
            }
            taskResult.success += result.getSuccess();
            taskResult.failed += result.getFailed();
            resultMap.put(taskResult.taskId, taskResult);
        }

        pushTaskResults(resultMap.values());

    }


    public void tick() {
        List<TaskResult> trs = swapTaskResults();
        logger.info("[update-report-begin] " + trs.size());
        Map<String, TaskResult> trMap = new HashMap<String, TaskResult>();
        for (TaskResult tr : trs) {
            TaskResult t = trMap.get(tr.taskId);
            if (t == null) {
                t = tr;
                trMap.put(tr.taskId, t);
            } else {
                t.requestCount += tr.requestCount;
                t.runCount += tr.runCount;
                t.noRunCount += tr.noRunCount;
                t.failed += tr.failed;
                t.success += tr.success;
            }
        }

        Date created = DateUtils.getHourFormatTime(new Date());
        String hourStr = DateUtils.getDateHour(created);
        long total = 0;
        long runCount = 0;
        long successCount = 0;
        for (TaskResult tr : trMap.values()) {
            adActiveTaskService.updateAdActiveTaskRunInfo(tr.taskId, tr.runCount, tr.noRunCount);
            String id = tr.taskId + hourStr;
            HourAdTaskReport report = reportAdTaskService.getById(id);
            if (report == null) {
                report = new HourAdTaskReport();
                report.setId(id);
                report.setVisitTime(created);
                report.setTaskId(tr.taskId);
            }
            report.setTotle(report.getTotle() + tr.requestCount);
            report.setRunCount(report.getRunCount() + tr.runCount);
            report.setNoRun(report.getNoRun() + tr.noRunCount);
            report.setSuccessCount(report.getSuccessCount() + tr.success);
            report.setFailCount(report.getFailCount() + tr.failed);

            reportAdTaskService.saveItem(report);
            total += tr.requestCount;
            runCount += tr.runCount;
            successCount += tr.success;
        }

        logger.info("[update-report-end] " + trMap.size() + " total=" + total + " run=" + runCount + " success=" + successCount);
    }
}
