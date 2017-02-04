package com.xp.brushms.dao;

import com.xp.brushms.dao.mongo.MongodbBaseDao2Impl;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.App;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huangzhimin on 16/5/18.
 */
@Repository
public class AppRepositoryImpl extends MongodbBaseDao2Impl implements AppRepository {

    @Override
    public void save(App app) {
        super.save(app);
    }

    @Override
    public Pagination<App> getApps(String name, String packageName, int pageNo, int pageSize) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if (StringUtils.isEmpty(name) == false) {
            c.and("name").regex(name);
        }
        if (StringUtils.isEmpty(packageName) == false) {
            c.orOperator(new Criteria().where("origPackageName").regex(packageName),
                    new Criteria().where("packageName").regex(packageName));
        }
        query.addCriteria(c);
        return getPage(pageNo, pageSize, query, App.class);
    }
    @Override
    public App findAppById(String id) {
        return findById(id, App.class);
    }

    @Override
    public List<App> getEnableApps() {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        c.and("enable").is(true);
        query.addCriteria(c);
        return find(query, App.class);
    }
}
