package com.xp.brushms.service;


import com.xp.brushms.dao.PCProxyMachineDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.PCProxyMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by sjh on 2016/1/14.
 */
@Repository("proxyMachineService")
public class ProxyMachineServieImpl implements ProxyMachineService {

    @Autowired
    PCProxyMachineDao pcProxyMachineDao;

    @Override
    public PCProxyMachine getById(String id) {
        return pcProxyMachineDao.getById(id);
    }

    @Override
    public void saveItem(PCProxyMachine machine) {
        pcProxyMachineDao.saveItem(machine);
    }

    @Override
    public void remove(String id) {
        pcProxyMachineDao.remove(id);
    }

    @Override
    public Pagination<PCProxyMachine> getPCProxyMachines(int pageNo, int pageSize) {
        return pcProxyMachineDao.getPCProxyMachines(pageNo, pageSize);
    }
}
