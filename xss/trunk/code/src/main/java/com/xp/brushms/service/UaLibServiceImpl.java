package com.xp.brushms.service;


import com.xp.brushms.dao.UaLibDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.UaLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("UaLibService")
public class UaLibServiceImpl implements UaLibService{

    @Autowired
    UaLibDao uaLibDao;

    @Override
    public UaLib getById(String id) {
        return uaLibDao.getById(id);
    }

    @Override
    public void saveItem(UaLib ua) {
        uaLibDao.saveItem(ua);
    }

    @Override
    public void remove(String id) {
        uaLibDao.remove(id);
    }

    @Override
    public Pagination<UaLib> getUaLibs(int pageNo, int pageSize) {
        return uaLibDao.getUaLibs(pageNo, pageSize);
    }

    @Override
    public List<UaLib> getUaLibsList() {
        return uaLibDao.getUaLibs();
    }
}
