package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.twsb.RootLogDao;
import com.xp.brushms.entity.twsb.RootLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sjh on 2016/3/4.
 */
@Repository("RootLogDao")
public class RootLogDaoImpl extends MongodbBaseDaoImpl<RootLog> implements RootLogDao {
    @Override
    protected Class<RootLog> getEntityClass() {
        return RootLog.class;
    }

    @Override
    public void saveItem(RootLog rootLog) {
        save(rootLog);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<RootLog> getRootLogs() {
        return this.findAll();
    }
}
