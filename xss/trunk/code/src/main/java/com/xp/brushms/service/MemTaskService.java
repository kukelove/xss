package com.xp.brushms.service;


import com.xp.brushms.entity.AdActiveTaskLog;
import com.xp.brushms.entity.TaskResultStat;
import com.xp.brushms.entity.WorkMachine;

import java.util.List;

/**
 * Created by huangzhimin on 16/5/27.
 */
public interface MemTaskService {
    public void tick();
    public void createTask(AdActiveTaskLog log);
    public void createTasks(List<AdActiveTaskLog> logs);
    public List<AdActiveTaskLog> getTasks(WorkMachine wm, int count);
    public void receiveTaskResults(List<TaskResultStat> results);
}
