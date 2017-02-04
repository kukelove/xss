package com.xp.brushms.controller;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.PCProxyMachine;
import com.xp.brushms.service.ProxyMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sjh on 2016/1/14.
 */
@Controller
@RequestMapping("/pcProxyMachine")
public class PCProxyMachineController {
    @Autowired
    ProxyMachineService proxyMachineService;

    @RequestMapping("/index.html")
    public String index() {

        return "proxyMachineManage/pcProxyMachine";
    }
    @RequestMapping("/pcProxyMachines.api")
    @ResponseBody
    public Pagination<PCProxyMachine> getPCProxyMachines(int pageNo) {

        return proxyMachineService.getPCProxyMachines(pageNo, 20);
    }
}
