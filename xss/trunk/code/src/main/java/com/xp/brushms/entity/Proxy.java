package com.xp.brushms.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by sjh on 2016/1/21.
 */
public class Proxy {
    @Id
    private String id;
    private String ip;
    private String  port;
    private int weight;
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
