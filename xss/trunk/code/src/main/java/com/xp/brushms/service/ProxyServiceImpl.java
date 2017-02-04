package com.xp.brushms.service;


import com.xp.brushms.dao.ProxyDao;
import com.xp.brushms.dao.UaLibDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Proxy;
import com.xp.brushms.entity.UaLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/7/25.
 */
@Repository("ProxyService")
public class ProxyServiceImpl implements ProxyService{

    @Autowired
    ProxyDao  proxyDao;

    @Override
    public void saveItem(Proxy proxy) {

        proxyDao.saveItem(proxy);
    }

    @Override
    public Proxy getById(String id) {
        return proxyDao.getById(id);
    }

    @Override
    public Proxy getByMac(String ip) {
        return proxyDao.getByMac(ip);
    }

    @Override
    public void remove(String id) {
        proxyDao.remove(id);

    }

    @Override
    public Pagination<Proxy> getProxy(int pageNo, int pageSize, String id, String ip) {
        return proxyDao.getProxy(pageNo, pageSize, id, ip);
    }

    @Override
    public List<Proxy> getProxyList() {
        return proxyDao.getProxyList();
    }


}

