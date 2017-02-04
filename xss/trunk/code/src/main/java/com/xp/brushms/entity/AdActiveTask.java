package com.xp.brushms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangzhimin on 16/1/13.
 */
public class AdActiveTask extends Task {
    protected Date start;
    protected Date end;
    protected long dayLimit;
    protected long daySuccessLimit;
    protected long totalLimit;
    protected int[] hourLimits = new int[24];
    protected int[] hourTaskCount    = new int[24];
    protected int[] minTaskCount;
    protected String uaLib;
    protected String url;
    protected ArrayList proxy;
    protected int runTask;
    protected int noRunTask;
    protected  long timeout;
    protected boolean writeLog;  //是否打印日志
    protected boolean loadImages = true;  //是否加载图片

    protected boolean dynamic;  //是否动态分配
    protected String dynamicApi;  //动态分配api
    private double clickRate;   //点击率
    private double conversionRate; //转换率
    private double successRate; //任务成功率
    private double predictRate;   //预设点击率
    private Date predictTime;   //预设点击率时间

    public double getPredictRate() {
        return predictRate;
    }

    public void setPredictRate(double predictRate) {
        this.predictRate = predictRate;
    }

    public Date getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(Date predictTime) {
        this.predictTime = predictTime;
    }

    protected List<AdBrushAction> adBrushActions;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(long dayLimit) {
        this.dayLimit = dayLimit;
    }

    public long getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(long totalLimit) {
        this.totalLimit = totalLimit;
    }

    public int[] getHourLimits() {
        return hourLimits;
    }

    public void setHourLimits(int[] hourLimits) {
        this.hourLimits = hourLimits;
    }

    public int[] getHourTaskCount() {
        return hourTaskCount;
    }

    public void setHourTaskCount(int[] hourTaskCount) {
        this.hourTaskCount = hourTaskCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUaLib() {
        return uaLib;
    }

//    public String getProxy() {
//        return proxy;
//    }
//
//    public void setProxy(String proxy) {
//        this.proxy = proxy;
//    }


    public ArrayList getProxy() {
        return proxy;
    }

    public void setProxy(ArrayList proxy) {
        this.proxy = proxy;
    }

    public void setUaLib(String uaLib) {
        this.uaLib = uaLib;
    }

    public int[] getMinTaskCount() {
        return minTaskCount;
    }

    public void setMinTaskCount(int[] minTaskCount) {
        this.minTaskCount = minTaskCount;
    }

    public int getRunTask() {
        return runTask;
    }

    public void setRunTask(int runTask) {
        this.runTask = runTask;
    }

    public int getNoRunTask() {
        return noRunTask;
    }

    public void setNoRunTask(int noRunTask) {
        this.noRunTask = noRunTask;
    }

    public long getDaySuccessLimit() {
        return daySuccessLimit;
    }

    public void setDaySuccessLimit(long daySuccessLimit) {
        this.daySuccessLimit = daySuccessLimit;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String getDynamicApi() {
        return dynamicApi;
    }

    public void setDynamicApi(String dynamicApi) {
        this.dynamicApi = dynamicApi;
    }

    public double getClickRate() {
        return clickRate;
    }

    public void setClickRate(double clickRate) {
        this.clickRate = clickRate;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public List<AdBrushAction> getAdBrushActions() {
        return adBrushActions;
    }

    public void setAdBrushActions(List<AdBrushAction> adBrushActions) {
        this.adBrushActions = adBrushActions;
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
