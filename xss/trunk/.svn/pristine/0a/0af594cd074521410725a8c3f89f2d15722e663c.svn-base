package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangzhimin on 16/1/13.
 */
public class Task {
    @Id
    protected String id;
    protected String name;
    protected boolean enable;
    protected Map<String, Object> extra = new HashMap<String, Object>();
    protected int workMachineType;
    protected Date created;

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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public int getWorkMachineType() {
        return workMachineType;
    }

    public void setWorkMachineType(int workMachineType) {
        this.workMachineType = workMachineType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
