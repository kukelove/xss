package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.PCProxyMachineDao;
import com.xp.brushms.entity.PCProxyMachine;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by sjh on 2016/1/14.
 */
@Repository("pcProxyMachineDao")
public class PCProxyMachineDaoImpl extends MongodbBaseDaoImpl<PCProxyMachine> implements PCProxyMachineDao {


    @Override
    protected Class<PCProxyMachine> getEntityClass() {
        return PCProxyMachine.class;
    }

    @Override
    public PCProxyMachine getById(String id) {
        return this.findById(id);
    }

    @Override
    public void saveItem(PCProxyMachine machine) {
        this.save(machine);
    }

    @Override
    public void remove(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        findAndRemove(query);
    }

    @Override
    public Pagination<PCProxyMachine> getPCProxyMachines(int pageNo, int pageSize) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }
}
