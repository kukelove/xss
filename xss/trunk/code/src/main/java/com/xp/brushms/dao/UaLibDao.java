package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.UaLib;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
public interface UaLibDao {
    public UaLib getById(String id);
    public void saveItem(UaLib ua);
    public void remove(String id);
    public Pagination<UaLib> getUaLibs(int pageNo, int pageSize);
    public List<UaLib> getUaLibs();

}
