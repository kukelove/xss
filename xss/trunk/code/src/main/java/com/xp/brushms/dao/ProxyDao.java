package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Proxy;
import com.xp.brushms.entity.WorkMachine;

import java.util.List;

/**
 * Created by sjh on 2016/7/25.
 */
public interface ProxyDao {
    public void saveItem(Proxy proxy);
    public Proxy getById(String id);
    public Proxy getByMac(String ip);
    public void remove(String id);
    public Pagination<Proxy> getProxy(int pageNo, int pageSize, String id, String ip);
    public List<Proxy> getProxyList();

}
