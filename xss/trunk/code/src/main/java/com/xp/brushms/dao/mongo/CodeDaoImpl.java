package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.CodeDao;
import com.xp.brushms.entity.Code;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 18370 on 2016/9/27.
 */
@Repository("CodeDao")
public class CodeDaoImpl extends MongodbBaseDaoImpl<Code> implements CodeDao {
    @Override
    public Code getById(String id) {
        return findById(id);
    }

    @Override
    public void saveItem(Code code) {
        save(code);
    }

    @Override
    public void remove(String id) {
        BasicQuery query = createQuery();
        query.addCriteria(new Criteria().where("id").is(id));
        this.findAndRemove(query);
    }

    @Override
    public Code getByName(String name) {
        BasicQuery query = createQuery();
        query.addCriteria(new Criteria().where("name").is(name));
        return this.findOne(query);
    }

    @Override
    public Pagination<Code> getCodes(int pageNo, int pageSize) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        query.addCriteria(c);
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<Code> getCodes() {
        return findAll();
    }

    @Override
    protected Class<Code> getEntityClass() {
        return Code.class;
    }
}
