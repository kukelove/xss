package com.xp.brushms.job;

import com.cc.ccutils.DateUtils;
import com.xp.brushms.service.AdActiveTaskLogService;
import com.xp.brushms.service.ReportAdTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by sjh on 2016/3/22.
 */
@Component
public class LogCleanJob {

    @Autowired
    AdActiveTaskLogService adActiveTaskLogService;
    @Autowired
    ReportAdTaskService reportAdTaskService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private void stat() {

        Date expired = DateUtils.addDay(DateUtils.getToday(), -3);
        adActiveTaskLogService.removeExpireLogs(expired);
    }
    public void execute( ) {
        logger.info("stat-LogCleanJob-start" + DateUtils.getDateTimeStr(new Date()));
        stat();
        logger.info("stat-LogCleanJob-job-finish " + DateUtils.getDateTimeStr(new Date()));
    }

}
