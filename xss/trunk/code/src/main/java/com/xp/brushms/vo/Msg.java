package com.xp.brushms.vo;

/**
 * Created by huangzhimin on 16/3/15.
 */
public class Msg {
    private int status;
    private String message;
    private Object extra;

    public static Msg create() {
        return Msg.create(0, null, null);
    }
    public static Msg create(int status, String message) {
        return Msg.create(status, message, null);
    }
    public static Msg create(int status, String message, Object extra) {
        Msg ret = new Msg();
        ret.status = status;
        ret.message = message;
        ret.extra = extra;
        return ret;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
