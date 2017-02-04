package com.xp.brushms.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by huangzhimin on 16/1/13.
 */
public class AdActiveTaskLog {

    protected String taskId;
    private String proxy;       //ip:port
    private String ua;          //userAgent
    private long timeout;
    private boolean writeLog;
    private boolean loadImages;
    private List<AdBrushAction> actions;
    private int machineType;
    private Date created;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getMachineType() {
        return machineType;
    }

    public void setMachineType(int machineType) {
        this.machineType = machineType;
    }

    public List<AdBrushAction> getActions() {
        return actions;
    }

    public void setActions(List<AdBrushAction> actions) {
        this.actions = actions;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isWriteLog() {
        return writeLog;
    }

    public void setWriteLog(boolean writeLog) {
        this.writeLog = writeLog;
    }

    public boolean isLoadImages() {
        return loadImages;
    }

    public void setLoadImages(boolean loadImages) {
        this.loadImages = loadImages;
    }
}
