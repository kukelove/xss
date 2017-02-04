package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.UaLibDao;
import com.xp.brushms.entity.UaLib;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/1/21.
 */
@Repository("UaLibDao")
public class UaLibDaoImpl extends MongodbBaseDaoImpl<UaLib> implements UaLibDao {


    @Override
    protected Class<UaLib> getEntityClass() {
        return UaLib.class;
    }

    @Override
    public UaLib getById(String id) {
        return findById(id);
    }

    @Override
    public void saveItem(UaLib ua) {
        save(ua);
    }

    @Override
    public void remove(String id) {
        BasicQuery query = createQuery();
        query.addCriteria(new Criteria().where("id").is(id));
        this.findAndRemove(query);

    }

    @Override
    public Pagination<UaLib> getUaLibs(int pageNo, int pageSize) {

        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<UaLib> getUaLibs() {
        return this.findAll();
    }
}
