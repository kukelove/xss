package com.xp.brushms.controller;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Ua;
import com.xp.brushms.service.ConfigService;
import com.xp.brushms.service.UaLibService;
import com.xp.brushms.service.UaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Controller
@RequestMapping("ua")
public class UaController {


    @Autowired
    protected ConfigService configService;

    @Autowired
    UaService uaService;

    @Autowired
    UaLibService uaLibService;

    @RequestMapping("/index.html")
    public String index() {

        return "config/UaConfig";
    }


    @RequestMapping("/uas.api")
    @ResponseBody
    public Pagination<Ua> getAdActiveTasks(int pageNo, String id, String libId){

        return uaService.getUas(pageNo, 20,id,libId);

    }



}
