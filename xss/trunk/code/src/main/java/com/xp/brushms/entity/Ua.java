package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by sjh on 2016/1/21.
 */
public class Ua {
        @Id
        private String id;
        private String value;
        private String libId;
        private double  random;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }
}
