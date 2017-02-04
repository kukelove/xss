package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Ua;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
public interface UaDao {
    public Ua getById(String id);
    public Ua getOneByRandom(String libId);
    public void saveItem(Ua ua);
    public void remove(String id);
    public Pagination<Ua> getUas(int pageNo, int pageSize, String id, String libId);
    public void  removeByLib(String itemId);
    public List<Ua> getUasByLib(String libId);
}
