package com.xp.brushms.job;

import com.xp.brushms.service.MemTaskService;
import com.xp.brushms.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by sjh on 2016/1/26.
 */
@Component
public class ReportTaskLogJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private MemTaskService memTaskService;

    public void execute( ) {
        logger.info("report task log begin>>>>>> " + DateUtils.getDateTimeStr(new Date()));
        memTaskService.tick();
        logger.info("report task log end>>>>>> " + DateUtils.getDateTimeStr(new Date()));
    }

}
