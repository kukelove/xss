package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.UserDao;
import com.xp.brushms.entity.User;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

/**
 * Created by hzm on 2015/7/10.
 */
@Repository("userDao")
public class UserDaoImpl extends MongodbBaseDaoImpl<User> implements UserDao {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUser(String name) {
        BasicQuery query = createQuery();
        query.addCriteria(new Criteria().where("name").is(name));
        return this.findOne(query);
    }

    @Override
    public void saveUser(User user) {
        this.save(user);
    }
}
