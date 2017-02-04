package com.xp.brushms.controller;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.AdActiveTask;
import com.xp.brushms.service.AdActiveTaskLogService;
import com.xp.brushms.service.AdActiveTaskService;
import com.xp.brushms.service.ConfigService;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by sjh on 2016/1/14.
 */
@Controller
@RequestMapping("/adActiveTask")
public class AdActiveTaskController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected ConfigService configService;

    @Autowired
    AdActiveTaskService adActiveTaskService;

    @Autowired
    AdActiveTaskLogService adActiveTaskLogService;

    @RequestMapping("/index.html")
    public String index() {

        return "taskManage/adActiveTask";
    }

    @RequestMapping("/adActiveTasksInfo.api")
    @ResponseBody
    public Map<String,String> getAdActiveTasksInfo(){

        Map<String,String> map = new HashMap();

        List<AdActiveTask> adActiveTasks = adActiveTaskService.getAdActiveTasks();
        if(adActiveTasks == null) {
            return map;
        }
        for (AdActiveTask adActiveTask : adActiveTasks) {
            map.put(adActiveTask.getId(),adActiveTask.getName());
        }
        return map;
    }

    @RequestMapping("/adActiveTasks.api")
    @ResponseBody
    public Pagination<AdActiveTask> getAdActiveTasks(int pageNo,String  id){

        return adActiveTaskService.getAdActiveTasks(pageNo, 20, id);

    }
    @RequestMapping("/add.api")
    @ResponseBody
    public Msg addChapter(@RequestBody AdActiveTask adActiveTask) {
        int no = configService.getInt("_chapter_id_auto_", 1);
        String id = String.format("%08d", no);
        while (true) {
            AdActiveTask c = adActiveTaskService.getById(id);
            if (c != null) {
                no++;
            } else {
                break;
            }
            id = String.format("%08d", no);
        }
        configService.set("_chapter_id_auto_", no + 1);
        adActiveTask.setId(id);
        adActiveTask.setCreated(new Date());
        int sum = 0;
        for(int i = 0; i<24; i++ ){
            sum =sum + adActiveTask.getHourLimits()[i];

        }
        if(sum == 0){
            int hourLimit = (int)adActiveTask.getDayLimit()/24;
            int yu = (int)adActiveTask.getDayLimit()%24;
            for(int i = 0; i<24 ;i++){
                if(i<=yu-1){
                    adActiveTask.getHourTaskCount()[i] = hourLimit + 1;
                }
                else{
                    adActiveTask.getHourTaskCount()[i] = hourLimit;
                }
            }
        }
        if(sum>0)
        {
            int hourLimit = (int)adActiveTask.getDayLimit()/sum;
            int yu = (int)adActiveTask.getDayLimit()%sum;
            int hourLimit2 = yu/24;
            int yu2 = yu%24;

            for(int i = 0; i<24 ;i++){
                if(i<=yu2-1){
                    adActiveTask.getHourTaskCount()[i] = (hourLimit*adActiveTask.getHourLimits()[i]) + hourLimit2+yu2;
                }
                else{
                    adActiveTask.getHourTaskCount()[i] = (hourLimit*adActiveTask.getHourLimits()[i]) + hourLimit2;
                }
            }

        }
        int[] minLimits = new int[144];
        for(int i = 0; i<24; i++){
            int a = adActiveTask.getHourTaskCount()[i]/6;
            int b = adActiveTask.getHourTaskCount()[i]%6;
            for(int j=0; j<6; j++){
                if(j<b-1){
                    minLimits[i*6+j] = a + 1;
                }
                else{
                    minLimits[i*6+j] = a;
                }
            }
        }
        adActiveTask.setMinTaskCount(minLimits);
        adActiveTaskService.saveItem(adActiveTask);
        return Msg.create(0, null, adActiveTask);
    }

    @RequestMapping("/remove.api")
    @ResponseBody
    public Msg removeTask(String itemId) {

        adActiveTaskService.remove(itemId);
        return Msg.create(0, null, 0);
    }

    @RequestMapping("/update.api")
    @ResponseBody
    public Msg update(@RequestBody AdActiveTask adActiveTask) {

        adActiveTask.setCreated(new Date());
        int sum = 0;
        for(int i = 0; i<24; i++ ){
            sum =sum + adActiveTask.getHourLimits()[i];

        }
        if(sum == 0){
            int hourLimit = (int)adActiveTask.getDayLimit()/24;
            int yu = (int)adActiveTask.getDayLimit()%24;
            for(int i = 0; i<24 ;i++){
                if(i<=yu-1){
                    adActiveTask.getHourTaskCount()[i] = hourLimit + 1;
                }
                else{
                    adActiveTask.getHourTaskCount()[i] = hourLimit;
                }
            }
        }
        if(sum>0)
        {
            int hourLimit = (int)adActiveTask.getDayLimit()/sum;
            int yu = (int)adActiveTask.getDayLimit()%sum;
            int hourLimit2 = yu/24;
            int yu2 = yu%24;

            for(int i = 0; i<24 ;i++){
                if(i<=yu2-1){
                    adActiveTask.getHourTaskCount()[i] = (hourLimit*adActiveTask.getHourLimits()[i]) + hourLimit2+yu2;
                }
                else{
                    adActiveTask.getHourTaskCount()[i] = (hourLimit*adActiveTask.getHourLimits()[i]) + hourLimit2;
                }
            }

        }
        int[] minLimits = new int[144];
        for(int i = 0; i<24; i++){
            int a = adActiveTask.getHourTaskCount()[i]/6;
            int b = adActiveTask.getHourTaskCount()[i]%6;
            for(int j=0; j<6; j++){
                if(j<b-1){
                    minLimits[i*6+j] = a + 1;
                }
                else{
                    minLimits[i*6+j] = a;
                }
            }
        }
        adActiveTask.setMinTaskCount(minLimits);
        adActiveTaskService.saveItem(adActiveTask);
        return Msg.create(0, null, adActiveTask);
    }
    @RequestMapping("/task_enable.api")
    @ResponseBody
    public Msg changeSwitch(AdActiveTask m) {


        adActiveTaskService.saveItem(m);
        return Msg.create(0, null, 0);
    }




    @RequestMapping("/adBrushAction.html")
    public ModelAndView adBrushActions(String taskId) {

        AdActiveTask  adActiveTask =  adActiveTaskService.getById(taskId);
        ModelAndView mav = new ModelAndView("taskManage/adBrushAction");
        mav.addObject("adActiveTask", adActiveTask);
        return  mav ;

    }
    @RequestMapping("/saveAdBrushActions.api")
    @ResponseBody
    public Msg saveAdBrushActions(@RequestBody AdActiveTask adActiveTask) {

        adActiveTaskService.saveItem(adActiveTask);
        return  Msg.create() ;
    }


}