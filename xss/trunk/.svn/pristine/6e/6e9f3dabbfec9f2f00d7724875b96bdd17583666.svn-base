package com.xp.brushms.controller;

import com.xp.brushms.common.Config;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Code;
import com.xp.brushms.service.CodeService;
import com.xp.brushms.service.ConfigService;
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
 * Created by 18370 on 2016/9/27.
 */
@Controller
@RequestMapping("code")
public class CodeController {

    @Autowired
    private Config config;

    @Autowired
    protected ConfigService configService;

    @Autowired
    CodeService codeService;

    @RequestMapping("/index.html")
    public String index() {

        return "config/CodeConfig";
    }

    @RequestMapping("/codes.api")
    @ResponseBody
    public Pagination<Code> getUaLib(int pageNo){

        return codeService.getCodes(pageNo, 20);

    }

    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg remove(String itemId){

        codeService.remove(itemId);
        return Msg.create(0, null, 0);

    }



    @RequestMapping("/add.api")
    @ResponseBody
    public Msg saveCode(@RequestBody Code code) {

        codeService.saveItem(code);
        return Msg.create(0, "", code);
    }


    @RequestMapping("/codesinfo.api")
    @ResponseBody
    public Map getUalibList(){
        List<Code> list = codeService.getCodes();
        Map map = new HashMap();
        for(Code  u :list){
            map.put(u.getId(), u.getName());
        }
        return map;
    }

}
