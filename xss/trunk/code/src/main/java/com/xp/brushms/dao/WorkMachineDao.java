package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.WorkMachine;

import java.util.List;

/**
 * Created by sjh on 2016/1/19.
 */
public interface WorkMachineDao {
    public WorkMachine getById(String id);
    public WorkMachine getByMac(String ip);
    public void saveItem(WorkMachine machine);
    public void updateItemStatus(String id, String status);
    public void remove(String id);
    public Pagination<WorkMachine> getWorkMachines(int pageNo, int pageSize, String id, String mac, String ip);
    public List<WorkMachine> getWorkMachineBy_id_mac(String id, String mac,String ip);
    public List<WorkMachine> getWorkMachineList(int machineType, int count);
    public List<WorkMachine> getWorkMachines();
}
