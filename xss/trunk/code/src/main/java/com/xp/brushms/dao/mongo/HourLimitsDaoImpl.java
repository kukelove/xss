package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.HourLimitsDao;
import com.xp.brushms.entity.HourLimits;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("hourLimitsDao")
public class HourLimitsDaoImpl extends MongodbBaseDaoImpl<HourLimits> implements HourLimitsDao {


    @Override
    public HourLimits getById(String id) {
        return getById(id);
    }

    @Override
    public void saveItem(HourLimits hourLimits) {
        save(hourLimits);
    }

    @Override
    public void remove(String id) {
     ;
        Query query=new Query(Criteria.where("id").is(id));
        this.findAndRemove(query);
    }

    @Override
    public Pagination<HourLimits> getHourLimits(int pageNo, int pageSize) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        query.addCriteria(c);
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<HourLimits> getHourLimits() {
        return this.findAll();
    }

    @Override
    protected Class<HourLimits> getEntityClass() {
        return HourLimits.class;
    }
}
