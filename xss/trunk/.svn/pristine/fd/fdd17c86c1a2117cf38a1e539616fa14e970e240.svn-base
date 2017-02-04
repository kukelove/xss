package com.xp.brushms.cache;

import com.xp.brushms.entity.WorkMachine;
import com.xp.brushms.service.WorkMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18370 on 2016/9/27.
 */
@Component
public class MemCache {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private WorkMachineService workMachineService;

    private Map<String, WorkMachine> workMachineMap = new HashMap();

    private static long lastUpdateTime = 0;


    private void updateWorkMachineMap() {
        synchronized (workMachineMap) {
            if(workMachineMap.size() == 0) {
                List<WorkMachine> arr =  workMachineService.getWorkMachines();
                if(arr != null && arr.size() > 0) {
                    HashMap replace = new HashMap();
                    for(WorkMachine workMachine : arr){
                        replace.put(workMachine.getMac(), workMachine);
                    }
                    workMachineMap = replace;
                } else {
                    logger.error("workMachines is empty!");
                }
            }
        }
    }

    public WorkMachine getWorkMachine(String mac){

        if(workMachineMap.size() == 0) {
            updateWorkMachineMap();
        }

        Date now = new Date();
        if(now.getTime() - lastUpdateTime > 3600000){
            updateWorkMachineMap();
            lastUpdateTime = now.getTime();
        }
        return  workMachineMap.get(mac);

    }



}
