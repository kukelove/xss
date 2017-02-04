package com.xp.brushms.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangzhimin on 16/1/13.
 */
public class SdkTask extends Task {
    private String packageName;
    private String mainActivity;
    private String md5;
    private long apkSize;
    private Map<Integer, String> payPoints = new HashMap<Integer, String>();

    public Map<Integer, String> getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Map<Integer, String> payPoints) {
        this.payPoints = payPoints;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(String mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }
}
