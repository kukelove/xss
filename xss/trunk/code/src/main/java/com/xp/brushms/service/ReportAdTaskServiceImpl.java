package com.xp.brushms.service;


import com.xp.brushms.dao.ReportAdTaskDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.HourAdTaskReport;
import com.xp.brushms.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/26.
 */
@Repository("reportAdTaskService")
public class ReportAdTaskServiceImpl implements ReportAdTaskService {


    @Autowired
    ReportAdTaskDao reportAdTaskDao;
    @Override
    public HourAdTaskReport getById(String id) {
        return reportAdTaskDao.getById(id);
    }

    @Override
    public void saveItem(HourAdTaskReport hourAdTaskReport) {
        reportAdTaskDao.saveItem(hourAdTaskReport);
    }

    @Override
    public void remove(String id) {
        reportAdTaskDao.remove(id);
    }

    @Override
    public Pagination<HourAdTaskReport> getHourAdTaskReports(int pageNo, int pageSize) {
        return reportAdTaskDao.getHourAdTaskReports(pageNo, pageSize);
    }

    @Override
    public List<HourAdTaskReport> getHourAdTaskReports(String id, Date start, Date end) {
        return reportAdTaskDao.getHourAdTaskReports(id, start, end);

    }

    //今日统计数据
    @Override
    public HourAdTaskReport getTodayStat(String taskId) {
        HourAdTaskReport report = new HourAdTaskReport();
        report.setTaskId(taskId);
        List<HourAdTaskReport> reports = getHourAdTaskReports(taskId, DateUtils.todayStart(), DateUtils.todayEnd());
        if(reports == null || reports.size() == 0) {
            return report;
        }
        for (HourAdTaskReport taskReport : reports) {
            report.setTotle(report.getTotle() + taskReport.getTotle());
            report.setRunCount(report.getRunCount() + taskReport.getRunCount());
            report.setNoRun(report.getNoRun() + taskReport.getNoRun());
            report.setSuccessCount(report.getSuccessCount() + taskReport.getSuccessCount());
            report.setFailCount(report.getFailCount() + taskReport.getFailCount());
        }
        return report;
    }
}
