package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.WorkMachine;

import java.util.List;

/**
 * Created by huangzhimin on 16/1/13.
 */
public interface WorkMachineService {
    public WorkMachine getById(String id);
    public void saveItem(WorkMachine machine);
    public void remove(String id);
    public Pagination<WorkMachine> getWorkMachines(int pageNo, int pageSize, String id, String mac, String ip);
    public Pagination<WorkMachine> getWorkMachinesSort(int pageNo, int pageSize, String id, String mac,String ip);
    public List<WorkMachine> getWorkMachineList(int machineType, int count);
    public WorkMachine getByMac(String mac);
    public List<WorkMachine> getWorkMachines();
    public void updateItemStatus(String id ,String status);

}
