package com.xp.brushms.controller;

import com.xp.brushms.auth.NoAuth;
import com.xp.brushms.entity.App;
import com.xp.brushms.service.AppService;
import com.xp.brushms.service.ConfigService;
import com.xp.brushms.service.WorkMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by huangzhimin on 16/5/27.
 */
@RestController
@RequestMapping("/api/v0/")
@NoAuth
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource
    protected AppService appService;

    @Autowired
    protected ConfigService configService;

    @Autowired
    WorkMachineService workMachineService;

    static class ProduceApp {
        public String md5;
        public String origPackageName;
        public String newPackageName;
        public String apkUrl;
        public long size;
        public int weight;
    }

    @RequestMapping("test.api")
    public String test() throws InterruptedException {
       logger.info("test.api>>>>>>>>>>>>>");
        Thread.sleep(50000l);
        return "ok";
    }
    class Vo{
        Data lastConnectTime;
        String ip;
        String mac;
        String macs;
        String hostname;
        String machineType;
        Object extra;
        String version;
    }

    @RequestMapping("ch_test.api")
    public List<App> getEnableApps() {
//        String wms = HttpUtils.getAsString("http://112.5.16.35:3000/machines?key=a87b6a2dfe4804d535571e044841398b");
//        ArrayList<HashMap> arry =  JsonUtils.parseJson(wms, ArrayList.class);
//        for(int i= 0 ;i<arry.size();i++){
//            HashMap v = arry.get(i);
//            if(!v.get("machineType").equals("redirector")){
//
//                if(workMachineService.getByMac(v.get("mac").toString())!=null){
//                    continue;
//                }
//                WorkMachine workMachine = new WorkMachine();
//                int no = configService.getInt("_workMachine_id_auto_", 1);
//                String id = String.format("%08d", no);
//                while (true) {
//                    WorkMachine c = workMachineService.getById(id);
//                    if (c != null) {
//                        no++;
//                    } else {
//                        break;
//                    }
//                    id = String.format("%08d", no);
//                }
//                configService.set("_workMachine_id_auto_", no + 1);
//                workMachine.setId(id);
//                workMachine.setIp(v.get("ip").toString());
//                workMachine.setMac(v.get("mac").toString());
//                workMachine.setMacs(v.get("macs").toString());
//                workMachineService.saveItem(workMachine);
//            }
//        }
        return  null;
    }
}
