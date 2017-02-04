package com.xp.brushms.controller;

import com.xp.brushms.common.Config;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Ua;
import com.xp.brushms.entity.UaLib;
import com.xp.brushms.service.ConfigService;
import com.xp.brushms.service.UaLibService;
import com.xp.brushms.service.UaService;
import com.xp.brushms.vo.Msg;
import com.xp.brushms.vo.UaLibVo;
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
 * Created by sjh on 2016/1/21.
 */
@Controller
@RequestMapping("uaLib")
public class UaLibConfig {

    @Autowired
    private Config config;

    @Autowired
    protected ConfigService configService;

    @Autowired
    UaLibService uaLibService;

    @Autowired
    UaService uaService;

    @RequestMapping("/index.html")
    public String index() {

        return "config/UaLibConfig";
    }
    @RequestMapping("/uaLibs.api")
    @ResponseBody
    public Pagination<UaLib> getUaLib(int pageNo){

        return uaLibService.getUaLibs(pageNo, 20);

    }

    @RequestMapping("/uaLibsList.api")
    @ResponseBody
    public Map getUalibList(){
        List<UaLib> list = uaLibService.getUaLibsList();
        Map map = new HashMap();
        for(UaLib  u :list){

            map.put(u.getId(), u.getName());

        }
        return map;
    }

    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg remove(String itemId){

        uaLibService.remove(itemId);
        uaService.removeByLib(itemId);
        return Msg.create(0 , null, 0);

    }




    @RequestMapping("add.api")
    @ResponseBody
    public Msg addRootPackage(@RequestBody UaLibVo m) {


        int no = configService.getInt("_ualib_id_auto_", 1);
        String id = String.format("%08d", no);
        while (true) {
            UaLib c = uaLibService.getById(id);
            if (c != null) {
                no++;
            } else {
                break;
            }
            id = String.format("%05d", no);
        }
        UaLib uaLib = new UaLib();
        uaLib.setId(id);
        uaLib.setName(m.getName());
        uaLib.setCreated(new Date());
        uaLibService.saveItem(uaLib);
        configService.set("_ualib_id_auto_", no + 1);

        for (int i = 0; i < m.getUploadFiles().size(); i++) {
            File uploadFile = new File(config.getUploadDir() + m.getUploadFiles().get(i));
            if (!uploadFile.exists()) {
                return Msg.create(1, "未上传完全", null);
            }

        }
        try {
            for (int i = 0; i < m.getUploadFiles().size(); i++) {
                File uploadFile = new File(config.getUploadDir() + m.getUploadFiles().get(i));
                BufferedReader br = new BufferedReader(new FileReader(uploadFile));
                String s = null;
                while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    Ua ua = new Ua();
                    ua.setLibId(id);
                    ua.setValue(s.trim());
                    ua.setRandom(Math.random());
                    uaService.saveItem(ua);
                }

            }
        }catch(Exception e){
            return Msg.create(2, "存储apk失败", null);

        }
        return Msg.create(0, null, null);

    }



}
