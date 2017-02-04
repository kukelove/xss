package com.xp.brushms.controller;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTaskLog;
import com.xp.brushms.service.AdActiveTaskLogService;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by sjh on 2016/1/14.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    AdActiveTaskLogService adActiveTaskLogService;

    @RequestMapping("/adActivieTaskLog.html")
    public String index() {

        return "log/adActivieTaskLog";
    }

    @RequestMapping("/adActivieTaskLogs.api")
    @ResponseBody
    public Pagination<AdActiveTaskLog> getAdActivieTaskLogs(int pageNo, String id, Date start, Date end, String taskId, String vmId){

        return adActiveTaskLogService.getAdActiveTaskLogs(pageNo, 20, id, start, end, vmId, taskId);
    }
    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg removeTaskLog(String itemId) {

        adActiveTaskLogService.remove(itemId);
        return Msg.create(0, null, 0);
    }
}
