package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.AdActiveTaskDao;
import com.xp.brushms.entity.AdActiveTask;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sjh on 2016/1/14.
 */
@Repository("adActiveTaskDao")
public class AdActiveTaskDaoImpl extends MongodbBaseDaoImpl<AdActiveTask> implements AdActiveTaskDao {
    @Override
    public void updateAdActiveTaskRunInfo(String taskId, long runCount, long noRunCount) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("id").is(taskId);

        query.addCriteria(c);
        Update update = new Update();
        update.set("runTask", runCount);
        update.set("noRunTask", noRunCount);

        super.updateFirst(query, update);
    }

    @Override
    public AdActiveTask getById(String id) {

        return this.findById(id);
    }

    @Override
    public List<AdActiveTask> getByStr(Map map) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        for (Object key : map.keySet()) {

            c.and(key.toString()).is(map.get(key));
        }
        query.addCriteria(c);
        return find(query);
    }

    @Override
    public void saveItem(AdActiveTask task) {
        this.save(task);
    }

    @Override
    protected Class<AdActiveTask> getEntityClass() {
        return AdActiveTask.class;
    }

    @Override
    public void remove(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        findAndRemove(query);
    }

    @Override
    public Pagination<AdActiveTask> getAdActiveTasks(int pageNo, int pageSize, String id) {

        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if (StringUtils.isEmpty(id) == false) {
            c.and("id").is(id);
        }
        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<AdActiveTask> getAdActiveTasks() {

        return  this.findAll();
    }

    @Override
    public List<AdActiveTask> getAdActiveTasks_dynamic(Date predictTime) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("dynamic").is(true);
        c.and("predictTime").lte(predictTime);
        query.addCriteria(c);
        return this.find(query);
    }
}
