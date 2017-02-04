package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTask;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/14.
 */
public interface AdActiveTaskService {
    public void updateAdActiveTaskRunInfo(String taskId, long runCount, long noRunCount);
    public AdActiveTask getById(String id);
    public void saveItem(AdActiveTask task);
    public void remove(String id);
    public Pagination<AdActiveTask> getAdActiveTasks(int pageNo, int pageSize, String id);
    public List<AdActiveTask> getAdActiveTasks();
    public List<AdActiveTask> getAdActiveTasks_dynamic(Date predictTime);
}
