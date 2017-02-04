package com.xp.brushms.dao.mongo;


import com.xp.brushms.dao.ReportAdTaskDao;
import com.xp.brushms.entity.HourAdTaskReport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/26.
 */
@Repository("reportAdTaskDao")
public class ReportAdTaskDaoImpl extends MongodbBaseDaoImpl<HourAdTaskReport> implements ReportAdTaskDao {
    @Override
    protected Class<HourAdTaskReport> getEntityClass() {
        return HourAdTaskReport.class;
    }

    @Override
    public HourAdTaskReport getById(String id) {
        return findById(id);
    }

    @Override
    public void saveItem(HourAdTaskReport hourAdTaskReport) {
        this.save(hourAdTaskReport);
    }

    @Override
    public void remove(String id) {
        this.remove(id);
    }

    @Override
    public Pagination<HourAdTaskReport> getHourAdTaskReports(int pageNo, int pageSize) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<HourAdTaskReport> getHourAdTaskReports(String id, Date start, Date end) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if(StringUtils.isEmpty(start) == false&& StringUtils.isEmpty(end) == true){

            c.and("visitTime").gte(start);
        }
        if(StringUtils.isEmpty(start) == true&& StringUtils.isEmpty(end) == false){
            c.and("visitTime").lt(end);
        }
        if(StringUtils.isEmpty(start) == false&& StringUtils.isEmpty(end) == false){

            c.and("visitTime").gte(start).lt(end);
        }
        if (StringUtils.isEmpty(id) == false) {
            c.and("taskId").is(id);
        }
        query.addCriteria(c);
        return find(query);
    }
}
