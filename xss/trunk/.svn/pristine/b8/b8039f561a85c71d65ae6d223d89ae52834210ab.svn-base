package com.xp.brushms.job;


import com.xp.brushms.entity.AdActiveTask;
import com.xp.brushms.service.AdActiveTaskLogService;
import com.xp.brushms.service.AdActiveTaskService;
import com.xp.brushms.util.DateUtils;
import com.xp.brushms.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class ONTaskJob {

    private static int dayActcount;
    @Autowired
    AdActiveTaskService adActiveTaskService;

    @Autowired
    AdActiveTaskLogService adActiveTaskLogService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    private void stat() {
        Date d = new Date();
        AdActiveTask task = adActiveTaskService.getById("00000055");
        if(task==null)
        {
            return;
        }
        if (d.getTime() <= task.getStart().getTime() || d.getTime() >= task.getEnd().getTime()) {
            return;
        }
        if (task.getHourTaskCount()[d.getHours()] == 0) {
            return;
        }
        String url = "http://dsp.9media.net/media/api/v0/get_today_cpm.api?aoId=1ef78706-9c1d-4fc4-9b90-62b190ea176b";
        int count = Integer.parseInt(HttpUtils.getAsString(url));
        if(dayActcount==0)
        {
            dayActcount = count;
        }
        else{
            if(dayActcount==count&&task.isEnable()==true)
            {
                task.setEnable(false);
                adActiveTaskService.saveItem(task);
            }
        }
    }
    public void execute( ) {
        logger.info("stat-ONTaskJob-start" + DateUtils.getDateTimeStr(new Date()));
        stat();
        logger.info("stat-ONTaskJob-job-finish " + DateUtils.getDateTimeStr(new Date()));
    }
}
