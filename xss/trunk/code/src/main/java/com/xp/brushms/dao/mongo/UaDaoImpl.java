package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.UaDao;
import com.xp.brushms.entity.Ua;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("UaDao")
public class UaDaoImpl extends MongodbBaseDaoImpl<Ua> implements UaDao {
    @Override
    protected Class<Ua> getEntityClass() {
        return Ua.class;
    }

    @Override
    public Ua getById(String id) {
        return this.findById(id);
    }

    @Override
    public Ua getOneByRandom(String libId) {
        double b = Math.random();
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("libId").is(libId);
        c.and("random").lt(b);
        query.addCriteria(c);
        Ua ua = findOne(query);
        if(ua==null){
            BasicQuery query2 = createQuery();
            Criteria c2 = new Criteria();
            c2.and("libId").is(libId);
            c2.and("random").gte(b);
            query2.addCriteria(c2);
            return this.findOne(query2);
        }
        else{
            return ua;
        }
    }

    @Override
    public void saveItem(Ua ua) {
        this.save(ua);
    }

    @Override
    public void remove(String id) {

        BasicQuery query = createQuery();
        query.addCriteria(new Criteria().where("libId").is(id));
        this.findAndRemove(query);

    }

    @Override
    public Pagination<Ua> getUas(int pageNo, int pageSize, String id, String libId) {

        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if(StringUtils.isEmpty(id)==false){
            c.and("id").regex(id);
        }
        if(StringUtils.isEmpty(libId)==false){
            c.and("libId").regex(libId);
        }
        query.addCriteria(c);;
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public void removeByLib(String itemId) {
        BasicQuery query= createQuery();
        Criteria c = new Criteria();
        c.and("libId").is(itemId);;
        query.addCriteria(c);
        this.findAndRemove(query);
    }

    @Override
    public List<Ua> getUasByLib(String libId) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("libId").is(libId);
        query.addCriteria(c);
        return find(query);
    }
}
