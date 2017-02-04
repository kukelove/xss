package com.xp.brushms.job;

import com.xp.brushms.entity.AdActiveTask;
import com.xp.brushms.service.AdActiveTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Date;
import java.util.List;


/**
 * Note:
 * Created by wjw
 * Date: 2016/8/16.15:43
 */

public class PreClickRateJob {

    @Autowired
    AdActiveTaskService adActiveTaskService;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public void execute() {
        logger.info("PreClickRateJob every 10mimutes");
        List<AdActiveTask> sdActiveTasks = adActiveTaskService.getAdActiveTasks_dynamic(new Date());
        if(sdActiveTasks != null) {
            for (AdActiveTask a : sdActiveTasks) {
                if(a.getPredictRate()>0) {
                    a.setClickRate(a.getPredictRate());
                    a.setPredictRate(0);
                    a.setPredictTime(null);
                    adActiveTaskService.saveItem(a);
                }
            }
        }
    }
}
