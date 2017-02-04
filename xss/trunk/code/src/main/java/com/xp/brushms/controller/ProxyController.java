package com.xp.brushms.controller;

import com.xp.brushms.common.Config;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Proxy;
import com.xp.brushms.entity.Ua;
import com.xp.brushms.entity.UaLib;
import com.xp.brushms.entity.WorkMachine;
import com.xp.brushms.service.ConfigService;
import com.xp.brushms.service.ProxyService;
import com.xp.brushms.service.UaService;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by sjh on 2016/7/25.
 */
@Controller
@RequestMapping("proxy")
public class ProxyController {


    @Autowired
    ProxyService proxyService;


    //跳转页面
    @RequestMapping("/index.html")
    public String index() {

        return "workMachineManage/ProxyConfig";
    }


    @RequestMapping("/save.api")
    @ResponseBody
    public Msg saveProxy(@RequestBody Proxy editItem) {
        //存入数据库
        editItem.setId(UUID.randomUUID().toString());
        editItem.setCreated(new Date());
        proxyService.saveItem(editItem);
        return Msg.create(0, null,editItem );
    }
     //请求url，获得属性的值。
     @RequestMapping("/proxy.api")
     @ResponseBody
     public Pagination<Proxy> getPCProxyMachines(int pageNo, String id, String ip) {

         return proxyService.getProxy(pageNo, 20, id, ip);
     }
    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg removeTask(String itemId) {

        proxyService.remove(itemId);
        return Msg.create(0, null, 0);
    }


    @RequestMapping("/update.api")
    @ResponseBody
    public Msg update(@RequestBody Proxy proxy) {

        proxyService.saveItem(proxy);
        return  Msg.create(0, null, proxy);
    }

    @RequestMapping("/proxyList.api")
    @ResponseBody
    public Map getProxyList(){
        List<Proxy> list = proxyService.getProxyList();
        Map map = new HashMap();
        for(Proxy  u :list){
            map.put(u.getIp()+":"+u.getPort(), u.getIp()+":"+u.getPort());
        }

        return map;


    }



}





