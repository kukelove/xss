package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourLimits;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
public interface HourLimitsDao{
    public HourLimits getById(String id);
    public void saveItem(HourLimits hourLimits);
    public void remove(String id);
    public Pagination<HourLimits> getHourLimits(int pageNo, int pageSize);
    public List<HourLimits> getHourLimits();
}
