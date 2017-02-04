package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by huangzhimin on 16/5/18.
 */
public class App {
    @Id
    private String id;
    private String name;
    private String appName;
    private String origPackageName;
    private String packageName;
    private String md5;
    private String signBaidu;
    private String path;
    private int weight;
    private long size;
    private boolean uploaded;
    private boolean enable;
    private Date updateTime;
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrigPackageName() {
        return origPackageName;
    }

    public void setOrigPackageName(String origPackageName) {
        this.origPackageName = origPackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSignBaidu() {
        return signBaidu;
    }

    public void setSignBaidu(String signBaidu) {
        this.signBaidu = signBaidu;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
