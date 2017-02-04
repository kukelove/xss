package com.xp.brushms.dao;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.App;

import java.util.List;

/**
 * Created by huangzhimin on 16/5/18.
 */
public interface AppRepository {
    void save(App app);
    public Pagination<App> getApps(String name, String packageName, int pageNo, int pageSize);
    public App findAppById(String id);
    public List<App> getEnableApps();
}
