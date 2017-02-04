package com.xp.brushms.service;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourAdTaskReport;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/26.
 */
public interface ReportAdTaskService {
    public HourAdTaskReport getById(String id);
    public void saveItem(HourAdTaskReport hourAdTaskReport);
    public void remove(String id);
    public Pagination<HourAdTaskReport> getHourAdTaskReports(int pageNo, int pageSize);
    public List<HourAdTaskReport> getHourAdTaskReports(String id, Date start, Date end);
    public HourAdTaskReport getTodayStat(String id);
}
