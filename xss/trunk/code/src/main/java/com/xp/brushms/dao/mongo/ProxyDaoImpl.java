package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.ProxyDao;
import com.xp.brushms.entity.Proxy;
import com.xp.brushms.entity.WorkMachine;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by sjh on 2016/7/25.
 */
@Repository("ProxyDao")
public class ProxyDaoImpl extends MongodbBaseDaoImpl<Proxy> implements ProxyDao {


    @Override
    public void saveItem(Proxy proxy) {

        save(proxy);
    }

    @Override
    public Proxy getById(String id) {
        return findById(id);
    }

    @Override
    public Proxy getByMac(String ip) {
        Query query=new Query(Criteria.where("ip").is(ip));
        return findOne(query);
    }


    @Override
    public void remove(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        findAndRemove(query);

    }

    @Override
    public Pagination<Proxy> getProxy(int pageNo, int pageSize, String id, String ip) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if (StringUtils.isEmpty(id) == false) {

            c.and("id").is(id);
        }
        if (StringUtils.isEmpty(ip) == false) {
            c.and("mac").is(ip);
        }
        query.addCriteria(c);
        query.with(new Sort(Sort.Direction.DESC, "created"));
        return this.getPage(pageNo, pageSize, query);
    }

    @Override
    public List<Proxy> getProxyList() {
        return this.findAll();
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    @Override
    protected Class<Proxy> getEntityClass() {
        return Proxy.class;
    }
}
