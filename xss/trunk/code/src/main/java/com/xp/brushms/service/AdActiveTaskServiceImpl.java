package com.xp.brushms.service;


import com.xp.brushms.dao.AdActiveTaskDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/14.
 */
@Repository("adActiveTaskService")
public class AdActiveTaskServiceImpl implements AdActiveTaskService
{

    @Autowired
    AdActiveTaskDao adActiveTaskDao;

    @Override
    public void updateAdActiveTaskRunInfo(String taskId, long runCount, long noRunCount) {
        adActiveTaskDao.updateAdActiveTaskRunInfo(taskId, runCount, noRunCount);
    }

    @Override
    public AdActiveTask getById(String id) {
        return  adActiveTaskDao.getById(id);
    }

    @Override
    public void saveItem(AdActiveTask task) {
         adActiveTaskDao.saveItem(task);
    }

    @Override
    public void remove(String id) {
         adActiveTaskDao.remove(id);
    }

    @Override
    public Pagination<AdActiveTask> getAdActiveTasks(int pageNo, int pageSize, String id) {
        return adActiveTaskDao.getAdActiveTasks(pageNo, pageSize, id);
    }

    public List<AdActiveTask> getAdActiveTasks(){
        return adActiveTaskDao.getAdActiveTasks();
    }

    @Override
    public List<AdActiveTask> getAdActiveTasks_dynamic(Date predictTime) {
        return adActiveTaskDao.getAdActiveTasks_dynamic(predictTime);
    }
}
