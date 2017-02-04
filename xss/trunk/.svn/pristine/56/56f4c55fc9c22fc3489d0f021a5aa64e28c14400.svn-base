package com.xp.brushms.controller.twsb;

import com.xp.brushms.auth.NoAuth;
import com.xp.brushms.entity.Ua;
import com.xp.brushms.service.UaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sjh on 2016/3/4.
 */
@Controller
@RequestMapping("/ua")
public class RootLogController {

    @Autowired
    UaService uaService;

    /*@RequestMapping("/getUa.api")
    @ResponseBody
    public List get(int count, String type) {

        List<String> uas = new ArrayList<String>();
        for(int i=0; i<count; i++) {
            String a = uaService.getOneByRandom(type).getValue();
            uas.add(a);
        }
        return uas;
    }*/

    @RequestMapping("/getUa.api")
    @ResponseBody
    @NoAuth
    public List<String> getUas(String type,int count) {
        List<String> ret = new ArrayList();
        if(count == 1) {
            ret.add(uaService.getOneByRandom(type).getValue());
            return ret;
        }
        List<Ua> uas = uaService.getUasByLib(type);
        if(uas == null || uas.size() == 0) {
            return ret;
        }
        if(uas.size() < count) {
            count = uas.size();
        }
        int imax = uas.size() - count;
        int i = 0;
        if(imax > 0){
            Random random = new Random();
            i = random.nextInt(imax);
        }
        while (ret.size() < count) {
            ret.add(uas.get(i).getValue());
            i ++;
        }
        return ret;

    }


}
