package com.xp.brushms.cache;

import com.xp.brushms.entity.WorkMachine;
import com.xp.brushms.service.WorkMachineService;
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
public class MachCache {

    @Autowired
    private WorkMachineService workMachineService;

    private Map<String, WorkMachine> workMachineMap = new HashMap();

    private static long timecount = 0;


    private void updateWorkMachineMap() {
        List<WorkMachine> arr =  workMachineService.getWorkMachines();
        HashMap replace = new HashMap();
        for(WorkMachine workMachine : arr){
            replace.put(workMachine.getMac(), workMachine);
        }
        workMachineMap = replace;
    }

    public WorkMachine getWorkMachine(String mac){

        if(workMachineMap.size() == 0) {
            synchronized (workMachineMap) {
                if(workMachineMap.size() == 0) {
                    updateWorkMachineMap();
                }
            }
        }

        Date now = new Date();
        if(now.getTime() - timecount>3600000){
            updateWorkMachineMap();
            timecount = now.getTime();
        }
        return  workMachineMap.get(mac);

    }



}
