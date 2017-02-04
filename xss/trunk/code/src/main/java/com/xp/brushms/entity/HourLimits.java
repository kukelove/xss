package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by sjh on 2016/1/21.
 */
public class HourLimits {
    @Id
    private String id;
    private String name;
    private int[] hourLimits = new int[24];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getHourLimits() {
        return hourLimits;
    }

    public void setHourLimits(int[] hourLimits) {
        this.hourLimits = hourLimits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
