package com.xp.brushms.dao.mongo;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public abstract class MongodbBaseDao2Impl  {


    public <T> Pagination<T> getPage(int pageNo, int pageSize, Query query, Class<T> cls) {
        long totalCount = this.mongoTemplate.count(query, cls);
        Pagination<T> page = new Pagination<T>(pageNo, pageSize, totalCount);
        query.skip(page.getFirstResult());
        query.limit(pageSize);
        List<T> datas = this.find(query, cls);
        page.setDatas(datas);
        return page;
    }

    public BasicQuery createQuery() {
        return new BasicQuery(createObject());
    }

    public DBObject createObject() {
        return BasicDBObjectBuilder.start().get();
    }


    public <T> List<T> find(Query query, Class<T> cls) {
        return mongoTemplate.find(query, cls);
    }


    public <T> T findOne(Query query, Class<T> cls) {
        return mongoTemplate.findOne(query, cls);
    }

    public <T> List<T> findAll(Class<T> cls) {
        return this.mongoTemplate.findAll(cls);
    }

    public <T> T findAndModify(Query query, Update update, Class<T> cls) {

        return this.mongoTemplate.findAndModify(query, update, cls);
    }

    public <T> T findAndRemove(Query query, Class<T> cls) {
        return this.mongoTemplate.findAndRemove(query, cls);
    }

    public void updateFirst(Query query, Update update, Class cls) {
        mongoTemplate.updateFirst(query, update, cls);
    }

    public <T> T save(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }

    public <T> T findById(String id, Class<T> cls) {
        return mongoTemplate.findById(id, cls);
    }

    public <T> T findById(String id, String collectionName, Class<T> cls) {
        return mongoTemplate.findById(id, cls, collectionName);
    }

    public <T> void fetchCollections(final Query query, final CollectionIterator<T> iter, final Class<T> cls) {
        mongoTemplate.execute(cls, new CollectionCallback<T>() {
            @Override
            public T doInCollection(DBCollection dbCollection) throws MongoException, DataAccessException {

                DBCursor cursor = dbCollection.find(query.getQueryObject());
                while (cursor.hasNext()) {
                    DBObject v = cursor.next();
                    try {
                        MongoConverter reader = mongoTemplate.getConverter();
                        T t = reader.read(cls, v);
                        iter.onFetchOne(t);
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    @Autowired
    protected MongoTemplate mongoTemplate;
}
