package com.xp.brushms.entity;

/**
 * 刷量步骤
 * open:打开指定url
 * sleep:睡眠timeout毫秒
 * run-js:执行javascript代码：code
 * render:截图
 * switch-to-frame：进入指定frame
 * click：点击
 * Created by Skyline on 2016/6/24.
 */
public class AdBrushAction {
    private String action;      //open, sleep,run-js,render,switch-to-frame,click
    private String url;         //action为open时，url必填
    private long timeout;     //action为sleep时，timeout必填
    private String code;        //action为run-js时，code必填
    private String matchUrl;    //action为run-js时，可选matchUrl
    /**
     * javascript代码，用以判断该action是否需要执行；及是否中途退出(成功/失败)
     * 返回 true 表示该action需要执行
     * 返回 false 表示该action不执行
     * 返回 exit-success 表示退出（成功）
     * 返回 exit-failed 表示退出（失败）
     */
    private String checkCode;
    /**
     * switch-to-frame时必填，标识frame的name/id/位置，位置时0标识第一个frame
     */
    private Object frame;

    private int mouseX;     //action为click时，点击x坐标
    private int mouseY;     //action为click时，点击y坐标


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatchUrl() {
        return matchUrl;
    }

    public void setMatchUrl(String matchUrl) {
        this.matchUrl = matchUrl;
    }

    public Object getFrame() {
        return frame;
    }

    public void setFrame(Object frame) {
        this.frame = frame;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }
}
