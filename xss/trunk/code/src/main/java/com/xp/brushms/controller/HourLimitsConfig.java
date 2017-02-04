package com.xp.brushms.controller;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourLimits;
import com.xp.brushms.service.HourLimitsService;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sjh on 2016/1/21.
 */
@Controller
@RequestMapping("hourLimits")
public class HourLimitsConfig {
    @Autowired
    HourLimitsService hourLimitsService;

    @RequestMapping("/index.html")
    public String index() {

        return "config/hourLimitsConfig";
    }

    @RequestMapping("/add.api")
    @ResponseBody
    public Msg add(@RequestBody HourLimits hourLimits){

        hourLimitsService.saveItem(hourLimits);
        return Msg.create(0, null, hourLimits);

    }
    @RequestMapping("/hourLimits.api")
    @ResponseBody
    public Pagination<HourLimits> getAdActiveTasks(int pageNo){

        return hourLimitsService.getHourLimits(pageNo, 20);
    }

    @RequestMapping("/hourLimitsList.api")
    @ResponseBody
    public Map<String, int[]> getAdActiveTasksList(){

        List<HourLimits> list = hourLimitsService.getHourLimits();
        Map map = new HashMap();
        for(HourLimits  u :list){

            map.put(u.getName(), u.getHourLimits());

        }
        return map;
    }

    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg removeTaskLog(String itemId) {

        hourLimitsService.remove(itemId);
        return Msg.create(0, null, 0);
    }
}
