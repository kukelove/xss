package com.xp.brushms.client;

import com.cc.ccutils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunTask implements Serializable {

	private static final long serialVersionUID = 1L;

	public String taskId;	//任务ID
	public long timeout;
	public String proxy;    //ip:port
	public String ua;		//userAgent
	public boolean writeLog;	//是否打印日志
	public boolean loadImages = true;	//是否加载图片
	public List<Map<String, Object>> actions = new ArrayList<>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public boolean isWriteLog() {
		return writeLog;
	}

	public void setWriteLog(boolean writeLog) {
		this.writeLog = writeLog;
	}

	public boolean isLoadImages() {
		return loadImages;
	}

	public void setLoadImages(boolean loadImages) {
		this.loadImages = loadImages;
	}

	public List<Map<String, Object>> getActions() {
		return actions;
	}

	public void setActions(List<Map<String, Object>> actions) {
		this.actions = actions;
	}

	public final void addSleepAction(long timeout) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "sleep");
		a.put("timeout", timeout);
		actions.add(a);
	}

	public final void addRunJsAction(String code, String matchUrl,String checkCode) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "run-js");
		a.put("code", code);
		if (StringUtils.isEmpty(matchUrl) == false) {
			a.put("matchUrl", matchUrl);
		}
		if (StringUtils.isEmpty(checkCode) == false) {
			a.put("checkCode", checkCode);
		}
		actions.add(a);
	}

	public final void addOpenAction(String url) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "open");
		a.put("url", url);
		actions.add(a);
	}

	public final void addRenderAction() {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "render");
		actions.add(a);
	}

	public final void addSwitchToFrameAction(Object frame,String checkCode) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "switch-to-frame");
		a.put("frame",frame);
		if (StringUtils.isEmpty(checkCode) == false) {
			a.put("checkCode", checkCode);
		}
		actions.add(a);
	}

	public final void addClickAction(int x,int y,String checkCode) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "click");
		a.put("mouseX", x);
		a.put("mouseY", y);
		if (StringUtils.isEmpty(checkCode) == false) {
			a.put("checkCode", checkCode);
		}
		actions.add(a);
	}

	//page == 0 表示最新的子页面
	public final void addSwitchToPageAction(int page) {
		Map<String, Object> a = new HashMap<>();
		a.put("action", "switch-to-page");
		a.put("pageIndex", page);
		actions.add(a);
	}

}
