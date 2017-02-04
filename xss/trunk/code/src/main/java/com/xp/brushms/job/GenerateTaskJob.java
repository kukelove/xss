package com.xp.brushms.job;

import com.xp.brushms.entity.*;
import com.xp.brushms.service.*;
import com.xp.brushms.util.DateUtils;
import com.xp.brushms.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


/**
 * 定时生成任务
 */
@Component
public class GenerateTaskJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final int intervalMin = 10;        //要与定时器配置中设置的执行间隔分钟数一致

    @Autowired
    private AdActiveTaskService adActiveTaskService;
    @Autowired
    private UaService uaService;
    @Resource
    protected MemTaskService memTaskService;
    @Autowired
    private ReportAdTaskService reportAdTaskService;
    @Autowired
    private ProxyService proxyService;
    // 动态任务最后获取数据模型
    class DynamicCountPo {
        public Date time;
        public int count;
    }
    private static Map<String, DynamicCountPo> dynamicMap = new HashMap();

    private void stat() {
        //任务队列
        List<AdActiveTaskLog> pos = new ArrayList<AdActiveTaskLog>();
        List<AdActiveTask> tasks = adActiveTaskService.getAdActiveTasks();
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int minNum = intervalMin;   //分钟数
        if ((60 - minute) < minNum) {
            minNum = 60 - minute;
        }

        Map<String, List<Ua>> uaLibs = new HashMap();
        for (AdActiveTask task : tasks) {

            if (!task.isEnable() || !(task.getStart().compareTo(d) <= 0 && task.getEnd().compareTo(d) >= 0)) {
                continue;
            }
            List<AdBrushAction> adBrushActions = task.getAdBrushActions();
            if( adBrushActions == null || adBrushActions.size() == 0) {
                continue;
            }
            //日成功量达到后不再派发任务
            if(task.getDaySuccessLimit() > 0) {
                float r = 0.99f;
                HourAdTaskReport todayStat = reportAdTaskService.getTodayStat(task.getId());
                if(todayStat.getSuccessCount() >= task.getDaySuccessLimit() * r) {
                    continue;
                }
            }

            int count;

            //如果是动态分配
            if(task.isDynamic()) {
                //通过api得到当前值
                int aipCount = Integer.parseInt(HttpUtils.getAsString(task.getDynamicApi()));
                //得到动态对象
                DynamicCountPo dynamicCountPo = dynamicMap.get(task.getId());
                //如果对象是10分钟前创建的，则分配任务数
                if (dynamicCountPo != null && d.getTime() - dynamicCountPo.time.getTime() < 800000) {
                    count = (int)((aipCount - dynamicCountPo.count)*task.getClickRate());
                    dynamicCountPo.time = d;
                    dynamicCountPo.count = aipCount;
                } else {
                    dynamicCountPo = new DynamicCountPo();
                    dynamicCountPo.time = d;
                    dynamicCountPo.count = aipCount;
                    dynamicMap.put(task.getId(), dynamicCountPo);
                    continue;
                }
            } else {
                int hourCount = task.getHourTaskCount()[hour];  //当前时钟计划执行次数
                if(hourCount == 0) {
                    continue;
                }
                int minCount = hourCount / 60;        //平均每一分钟执行次数
                count = minCount * minNum;
                if (minute + minNum >= 60) {      //该hour内最后一次
                    count += hourCount % 60;
                }
            }

            List<Ua> uas = uaLibs.get(task.getUaLib());
            if (uas == null) {
//                uas = uaService.getUasByLib(task.getUaLib());
                uas = uaService.getUasByLib_AppleWebKit(task.getUaLib());
                uaLibs.put(task.getUaLib(), uas);
            }

            if(count == 0) {
                continue;
            }

            int timeInterval = minNum * 60000 / count;
            for (int i = 0; i < count; i++) {
                AdActiveTaskLog log = new AdActiveTaskLog();
                log.setTaskId(task.getId());

                //在这个执行方法中根据权重来选择代理
		TreeMap<String,Integer> select_map = new TreeMap();
		TreeMap<String,Integer> start_map = new TreeMap();
		TreeMap<String,Integer> end_map = new TreeMap();
		List<Proxy> list = proxyService.getProxyList();
        AdActiveTask adActiveTask= adActiveTaskService.getById(task.getId());
                if(list.size()>0&&adActiveTask.getProxy()!=null) {
                    Map<String, Integer> map = new HashMap();
                    for (Proxy u : list) {
                        map.put(u.getIp() + ":" + u.getPort(), u.getWeight());
                    }

                    for (int k = 0; k < adActiveTask.getProxy().size(); k++) {
                        adActiveTask.getProxy().get(k);
                        if (map.containsKey(adActiveTask.getProxy().get(k))) {
                            select_map.put(adActiveTask.getProxy().get(k).toString(), map.get(adActiveTask.getProxy().get(k)));

                        }
                    }

                    int all = 0;
                    String new_proxy = "";
                    if (select_map.size() > 0) {
                        for (String key : select_map.keySet()) {
                            all = all + select_map.get(key);
                            int all1 = all - select_map.get(key);
                            start_map.put(key, all1);
                            end_map.put(key, all);
                        }

                    double proxy_random = Math.random() * all;
                    for (String key : start_map.keySet()) {
                        if (start_map.get(key) <= proxy_random && proxy_random < end_map.get(key))
                            new_proxy = key;
                    }
                    log.setProxy(new_proxy);
                }
                }

//                log.setProxy(task.getProxy());
                String ua = uas.get((int) (Math.random() * uas.size())).getValue();
                log.setUa(ua);
                log.setTimeout(task.getTimeout());
                log.setWriteLog(task.isWriteLog());
                log.setLoadImages(task.isLoadImages());
                log.setMachineType(task.getWorkMachineType());
                log.setCreated(DateUtils.addMillisecond(d, i * timeInterval));
                log.setActions(adBrushActions);
                pos.add(log);
                }
                logger.info("generate-task: " + task.getName() + " " + count);
        }
        Collections.sort(pos, new Comparator<AdActiveTaskLog>() {
            public int compare(AdActiveTaskLog arg0, AdActiveTaskLog arg1) {
                return arg0.getCreated().compareTo(arg1.getCreated());
            }
        });
        memTaskService.createTasks(pos);
    }

    public void execute() {
        logger.info("generate task begin>>>>>> " + DateUtils.getDateTimeStr(new Date()));
        stat();
        logger.info("generate task end>>>>>> " + DateUtils.getDateTimeStr(new Date()));
    }
}
