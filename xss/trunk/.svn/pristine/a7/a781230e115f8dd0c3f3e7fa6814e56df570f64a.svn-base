package com.xp.brushms.service;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.App;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by huangzhimin on 16/5/19.
 */
public interface AppService {
    public List<App> getEnableApps();
    public Pagination<App> getApps(String name, String packageName, int pageNo, int pageSize);
    public void saveApp(App app);
    public App getApp(String id);
}
