package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTask;

/**
 * Created by huangzhimin on 16/1/13.
 */
public interface TaskService {

//    大任务操作
    public AdActiveTask getById(String id);
    public void saveItem(AdActiveTask task);
    public void remove(String id);
    public Pagination<AdActiveTask> getPage(int pageNo, int pageSize);
}
