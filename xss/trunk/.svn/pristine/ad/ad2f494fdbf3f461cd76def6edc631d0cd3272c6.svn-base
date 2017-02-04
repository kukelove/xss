package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.CollectionIterator;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTaskLog;

import java.util.Date;
import java.util.List;


/**
 * Created by sjh on 2016/1/14.
 */
public interface AdActiveTaskLogService {
    public AdActiveTaskLog getById(String id);
    public void saveItem(AdActiveTaskLog log);
    public void remove(String id);
    public Pagination<AdActiveTaskLog> getAdActiveTaskLogs(int pageNo, int pageSize, String id, Date start, Date end, String vmId, String taskId);
//    public List<AdActiveTaskLog> getAdActiveTaskLogList(WorkMachine workMachine, int count);
    public List<AdActiveTaskLog> getAdActiveTaskLogList(Date start, Date end);
    public void removeExpireLogs(Date expired);
    public void fetchTrackStatLogs(CollectionIterator<AdActiveTaskLog> iter, Date start, Date end);
}
