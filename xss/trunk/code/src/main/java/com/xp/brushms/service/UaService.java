package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Ua;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
public interface UaService {
    public Ua getById(String id);
    public void saveItem(Ua ua);
    public void remove(String id);
    public Pagination<Ua> getUas(int pageNo, int pageSize, String id, String libId);
    public Ua getOneByRandom(String libId);
    public void  removeByLib(String itemId);
    public List<Ua> getUasByLib(String libId);
    public List<Ua> getUasByLib_AppleWebKit(String libId);
}
