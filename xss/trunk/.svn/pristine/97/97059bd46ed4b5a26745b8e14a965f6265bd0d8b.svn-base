package com.xp.brushms.dao.mongo;

import com.cc.ccutils.DateUtils;
import com.xp.brushms.dao.AdActiveTaskLogDao;
import com.xp.brushms.entity.AdActiveTaskLog;
import com.xp.brushms.entity.WorkMachine;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sjh on 2016/1/14.
 */
@Repository("adActiveTaskLogDao")
public class AdActiveTaskLogDaoImpl extends MongodbBaseDaoImpl<AdActiveTaskLog> implements AdActiveTaskLogDao {
    @Override
    public AdActiveTaskLog getById(String id) {

        return this.findById(id);
    }

    @Override
    public void saveItem(AdActiveTaskLog log) {
        this.save(log);
    }

    @Override
    public void remove(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        findAndRemove(query);
    }

    @Override
    public Pagination<AdActiveTaskLog> getAdActiveTaskLogs(int pageNo, int pageSize, String id, Date start, Date end, String vmId, String taskId) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if(StringUtils.isEmpty(id) == false) {
            c.and("id").is(id);
        }
        if(StringUtils.isEmpty(vmId) == false) {
            c.and("vmId").is(vmId);
        }
        if(StringUtils.isEmpty(taskId) == false) {
            c.and("taskId").is(taskId);
        }
        if(StringUtils.isEmpty(start) == false&& StringUtils.isEmpty(end) == true){

            c.and("created").gte(start);
        }
        if(StringUtils.isEmpty(start) == true&& StringUtils.isEmpty(end) == false){
            c.and("created").lt(end);
        }
        if(StringUtils.isEmpty(start) == false&& StringUtils.isEmpty(end) == false){

            c.and("created").gte(start).lt(end);
        }

        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<AdActiveTaskLog> getAdActiveTaskLogList(WorkMachine workMachine, int count) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("machineType").is(workMachine.getMachineType());
        c.and("send").is(false);
        c.and("created").gte(DateUtils.addMinute(new Date(), -5));
        query.with(new Sort(Sort.Direction.DESC, "created"));
        query.addCriteria(c);
        query.limit(count);

        Update update = new Update();
        update.set("vmId", workMachine.getId());
        update.set("send", true);

        List<AdActiveTaskLog> ret = new ArrayList<AdActiveTaskLog>();
        for (int i = 0; i < count; i++) {
            AdActiveTaskLog log = findAndModify(query, update);
            if (log == null) {
                break;
            }
            ret.add(log);
        }
        return ret;
    }

    @Override
    public List<AdActiveTaskLog> getAdActiveTaskLogList(Date start,Date end) {

        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("created").gte(start).lt(end);
        query.addCriteria(c);
        return find(query);
    }

    @Override
    public void fetchTrackStatLogs(CollectionIterator<AdActiveTaskLog> iter, Date start, Date end) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("created").gte(start).lt(end);
        query.addCriteria(c);
        fetchCollections(query, iter);
    }

    @Override
    public void removeExpireLogs(Date expired) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("created").lt(expired);
        super.remove(query);
    }

    @Override
    protected Class getEntityClass() {
        return AdActiveTaskLog.class;
    }
}
