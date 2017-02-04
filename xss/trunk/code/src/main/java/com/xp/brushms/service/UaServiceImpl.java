package com.xp.brushms.service;

import com.xp.brushms.dao.UaDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Ua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("UaService")
public class UaServiceImpl implements UaService {
    @Autowired
    UaDao uaDao;

    @Override
    public Ua getById(String id) {
        return uaDao.getById(id);
    }

    @Override
    public void saveItem(Ua ua) {
        uaDao.saveItem(ua);
    }

    @Override
    public void remove(String id) {
        uaDao.remove(id);
    }

    @Override
    public Pagination<Ua> getUas(int pageNo, int pageSize, String id, String libId) {
        return uaDao.getUas(pageNo, pageSize,id,libId);
    }

    @Override
    public Ua getOneByRandom(String libId) {
        return uaDao.getOneByRandom(libId);
    }

    @Override
    public void removeByLib(String itemId) {
        uaDao.removeByLib(itemId);
    }

    @Override
    public List<Ua> getUasByLib(String libId) {
        return uaDao.getUasByLib(libId);
    }

    @Override
    public List<Ua> getUasByLib_AppleWebKit(String libId) {
        List<Ua> ret = new ArrayList();
        List<Ua> uasByLib = uaDao.getUasByLib(libId);
        if(uasByLib != null) {
            for (Ua ua : uasByLib) {
                if(ua.getValue() != null && ua.getValue().contains("AppleWebKit")) {
                    ret.add(ua);
                }
            }
        }
        return ret;
    }
}
