package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by sjh on 2016/1/21.
 */
public class UaLib {
    @Id
    private String id;
    private String  name;
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
}
