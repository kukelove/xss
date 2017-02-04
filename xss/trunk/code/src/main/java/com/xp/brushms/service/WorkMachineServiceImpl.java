package com.xp.brushms.service;

import com.xp.brushms.dao.WorkMachineDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.WorkMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by sjh on 2016/1/19.
 */
@Repository("workMachineService")
public class WorkMachineServiceImpl implements  WorkMachineService{

    @Autowired
    WorkMachineDao workMachineDao;

    @Override
    public WorkMachine getById(String id) {
        return workMachineDao.getById(id);
    }

    @Override
    public void saveItem(WorkMachine machine) {
        workMachineDao.saveItem(machine);
    }

    @Override
    public void remove(String id) {
        workMachineDao.remove(id);
    }

    @Override
    public Pagination<WorkMachine> getWorkMachines(int pageNo, int pageSize, String id, String mac, String ip) {
        return workMachineDao.getWorkMachines(pageNo, pageSize, id, mac, ip);
    }

    public class WorkMachineComparator implements Comparator<WorkMachine> {
        public int compare(WorkMachine m1, WorkMachine m2) {
            return fetchCompareValue(m1) - fetchCompareValue(m2);
        }
        private Integer fetchCompareValue(WorkMachine machine) {
            Map<String,Integer> vmap = new HashMap();
            vmap.put("启动",1);
            vmap.put("停止",2);
            vmap.put("更新",3);
            vmap.put("部署",4);
            vmap.put("say",5);

            String status = machine.getStatus();
            if(status!=null&&status.indexOf("启动") >= 0) {
                return vmap.get("启动");
            }else if(status!=null&&status.indexOf("停止") >= 0) {
                return vmap.get("停止");
            }else if(status!=null&&status.indexOf("更新") >= 0) {
                return vmap.get("更新");
            }else if(status!=null&&status.indexOf("部署") >= 0) {
                return vmap.get("部署");
            }else if(status!=null&&status.indexOf("say") >= 0) {
                return vmap.get("say");
            }else{
                return 6;
            }
        }
    }
    @Override
    public Pagination<WorkMachine> getWorkMachinesSort(int pageNo, int pageSize, String id, String mac,String ip) {

        //依据id，mac查询所有
        //排序
        //手动分页
//        List<WorkMachine> list = new ArrayList<WorkMachine>();
        List<WorkMachine> workMachine_all=workMachineDao.getWorkMachineBy_id_mac(id,mac,ip);
        WorkMachineComparator comparator = new WorkMachineComparator();
        Collections.sort(workMachine_all, comparator);
        Pagination<WorkMachine> page = new Pagination<WorkMachine>(pageNo, pageSize, workMachine_all.size());
        List<WorkMachine> datas =null;
        if(page.getFirstResult() + pageSize<=workMachine_all.size()-1) {
             datas = workMachine_all.subList(page.getFirstResult(), page.getFirstResult() + pageSize);
        }else{
            datas=workMachine_all.subList(page.getFirstResult(), workMachine_all.size());
        }
        page.setDatas(datas);
        return page;
    }

    @Override
    public List<WorkMachine> getWorkMachineList(int machineType, int count) {
        return workMachineDao.getWorkMachineList(machineType, count);
    }

    @Override
    public WorkMachine getByMac(String mac) {
        return workMachineDao.getByMac(mac);
    }

    @Override
    public List<WorkMachine> getWorkMachines() {
        return workMachineDao.getWorkMachines();
    }

    @Override
    public void updateItemStatus(String id, String status) {
        workMachineDao.updateItemStatus(id, status);
    }
}
