package com.xp.brushms.service;

import com.xp.brushms.dao.HourLimitsDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourLimits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("hourLimitsService")
public class HourLimitsServiceImpl implements  HourLimitsService{
    @Autowired
    HourLimitsDao hourLimitsDao;

    @Override
    public HourLimits getById(String id) {
        return hourLimitsDao.getById(id);
    }

    @Override
    public void saveItem(HourLimits hourLimits) {
        hourLimitsDao.saveItem(hourLimits);
    }

    @Override
    public void remove(String id) {
        hourLimitsDao.remove(id);
    }

    @Override
    public Pagination<HourLimits> getHourLimits(int pageNo, int pageSize) {
        return hourLimitsDao.getHourLimits(pageNo, pageSize);
    }

    @Override
    public List<HourLimits> getHourLimits() {
        return hourLimitsDao.getHourLimits();
    }
}
