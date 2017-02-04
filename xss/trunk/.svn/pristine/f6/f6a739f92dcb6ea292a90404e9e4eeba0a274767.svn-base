package com.xp.brushms.service;


import com.xp.brushms.dao.AdActiveTaskLogDao;
import com.xp.brushms.dao.mongo.CollectionIterator;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTaskLog;
import com.xp.brushms.entity.WorkMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/14.
 * 
 */
@Repository("adActiveTaskLogService")
public class AdActivieTaskLogServiceImpl implements AdActiveTaskLogService{
   @Autowired
   AdActiveTaskLogDao adActiveTaskLogDao;

    @Override
    public AdActiveTaskLog getById(String id) {
         return adActiveTaskLogDao.getById(id);
    }

    @Override
    public void saveItem(AdActiveTaskLog log) {
         adActiveTaskLogDao.saveItem(log);
    }

    @Override
    public void remove(String id) {
         adActiveTaskLogDao.remove(id);
    }

    @Override
    public Pagination<AdActiveTaskLog> getAdActiveTaskLogs(int pageNo, int pageSize, String id, Date start, Date end, String vmId, String taskId) {
        return adActiveTaskLogDao.getAdActiveTaskLogs(pageNo, pageSize, id, start, end, vmId, taskId);
    }

//    @Override
    public List<AdActiveTaskLog> getAdActiveTaskLogList(WorkMachine workMachine, int count) {
        return  adActiveTaskLogDao.getAdActiveTaskLogList(workMachine, count);

    }

    public List<AdActiveTaskLog> getAdActiveTaskLogList(Date start, Date end){
        return  adActiveTaskLogDao.getAdActiveTaskLogList(start, end);
    }

    @Override
    public void removeExpireLogs(Date expired) {
        adActiveTaskLogDao.removeExpireLogs(expired);
    }

    @Override
    public void fetchTrackStatLogs(CollectionIterator<AdActiveTaskLog> iter, Date start, Date end) {
        adActiveTaskLogDao.fetchTrackStatLogs(iter, start, end);
    }
}
