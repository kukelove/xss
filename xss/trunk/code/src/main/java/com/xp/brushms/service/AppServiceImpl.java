package com.xp.brushms.service;

import com.xp.brushms.dao.AppRepository;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.App;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huangzhimin on 16/5/19.
 */
@Service
public class AppServiceImpl implements AppService {
    @Resource
    protected AppRepository appRepository;


    @Override
    public List<App> getEnableApps() {
        return appRepository.getEnableApps();
    }

    @Override
    public Pagination<App> getApps(String name, String packageName, int pageNo, int pageSize) {
        return appRepository.getApps(name, packageName, pageNo, pageSize);
    }

    @Override
    public void saveApp(App app) {
        appRepository.save(app);
    }

    @Override
    public App getApp(String id) {
        return appRepository.findAppById(id);
    }
}
