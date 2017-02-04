package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by hzm on 2015-09-10.
 */
public class GlobalConfig {
    @Id
    private String id;
    private Object value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
