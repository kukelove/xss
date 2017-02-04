package com.xp.brushms.job;


import com.xp.brushms.entity.AdActiveTask;
import com.xp.brushms.service.AdActiveTaskService;
import com.xp.brushms.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * Created by sjh on 2016/7/13.
 */
@Component
public class OffTaskJob {

    @Autowired
    AdActiveTaskService adActiveTaskService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());



    private void stat() {
        AdActiveTask task = adActiveTaskService.getById("00000055");
        task.setEnable(true);
        adActiveTaskService.saveItem(task);
    }

    public void execute( ) {
        logger.info("stat-OffTaskJob-start" + DateUtils.getDateTimeStr(new Date()));
        stat();
        logger.info("stat-OffTaskJob-job-finish " + DateUtils.getDateTimeStr(new Date()));
    }
}
