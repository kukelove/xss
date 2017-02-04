package com.xp.brushms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Skyline on 2016/7/16.
 */
public class TaskResultStat implements Serializable{

    private static final long serialVersionUID = 1L;

    private String taskId;
    private long success;
    private long failed;
    private Date createTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
