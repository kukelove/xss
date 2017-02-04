package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourAdTaskReport;

import java.util.Date;
import java.util.List;

public interface ReportAdTaskDao {


    public HourAdTaskReport getById(String id);
    public void saveItem(HourAdTaskReport hourAdTaskReport);
    public void remove(String id);
    public Pagination<HourAdTaskReport> getHourAdTaskReports(int pageNo, int pageSize);
    public List<HourAdTaskReport> getHourAdTaskReports(String id, Date start, Date end);

}
