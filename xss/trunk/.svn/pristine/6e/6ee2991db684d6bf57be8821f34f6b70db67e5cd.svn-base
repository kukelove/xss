package com.xp.brushms.controller;

import com.xp.brushms.entity.HourAdTaskReport;
import com.xp.brushms.service.ReportAdTaskService;
import com.xp.brushms.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by sjh on 2016/1/25.
 */
@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    ReportAdTaskService reportAdTaskService;

    @RequestMapping("/index.html")
    public ModelAndView index(String id) {
        if(id==null)
        {
            id = "";
        }
        ModelAndView mav = new ModelAndView("report/adTaskReport");
        mav.addObject("id", id);
        return  mav ;
    }

    @RequestMapping("HourAdTaskReportList.api")
    @ResponseBody
    public List  getHourAdTaskReportList(String id, Date start, Date end)
    {

       return reportAdTaskService.getHourAdTaskReports(id, start, end );
    }


    @RequestMapping("DayAdTaskReportList.api")
    @ResponseBody
    public List  getDayAdTaskReport(String id, Date start, Date end)
    {

        Comparator<HourAdTaskReport> comparator = new Comparator<HourAdTaskReport>() {
            public int compare(HourAdTaskReport s1, HourAdTaskReport s2) {
                return s1.getId().compareTo(s2.getId());
            }
        };
        List<HourAdTaskReport>  hourAdTaskReports=reportAdTaskService.getHourAdTaskReports(id, start, end );
        if(hourAdTaskReports!=null){
            List<HourAdTaskReport> list=new ArrayList();
            Map<String, HourAdTaskReport> map =new HashMap();
            for(HourAdTaskReport h : hourAdTaskReports){
                Date date= DateUtils.getDate(h.getVisitTime());
                String dateStr=DateUtils.getDateStr(h.getVisitTime());
                if (!map.containsKey(dateStr)) {
                    h.setVisitTime(date);
                    map.put(dateStr,h);
                }else{
                    HourAdTaskReport item = map.get(dateStr);
                    item.setTotle(item.getTotle() + h.getTotle());
                    item.setNoRun(item.getNoRun() + h.getNoRun());
                    item.setSuccessCount(item.getSuccessCount() + h.getSuccessCount());
                    item.setFailCount(item.getFailCount() + h.getFailCount());
                    item.setRunCount(item.getRunCount()+h.getRunCount());
                }
            }
            for(String key: map.keySet()){
                list.add(map.get(key));
            }
            Collections.sort(list,comparator);
            return list;
        }
        else{
            return null;
        }

    }
}
