package com.xp.brushms.dao.mongo;

import com.xp.brushms.dao.PhoneDao;
import com.xp.brushms.entity.Phone;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by hzm on 2015/8/2.
 */
@Repository("phoneDao")
public class PhoneDaoImpl extends MongodbBaseDaoImpl<Phone> implements PhoneDao {
    @Override
    protected Class<Phone> getEntityClass() {
        return Phone.class;
    }

    @Override
    public Phone getPhone(String id) {
        return findById(id);
    }

    @Override
    public void savePhone(Phone phone) {
        save(phone);
    }

    @Override
    public Pagination<Phone> getPhones(int pageNo, int pageSize, String imei, String imsi, String phoneNumber) {
        BasicQuery query = createQuery();
        Criteria c = new Criteria();
        if (StringUtils.isEmpty(imei) == false) {
            c.and("imei").is(imei);
        }
        if (StringUtils.isEmpty(imsi) == false) {
            c.and("imsi").is(imsi);
        }
        if (StringUtils.isEmpty(phoneNumber) == false) {
            c.and("phoneNumber").is(phoneNumber);
        }
        return this.getPage(pageNo, pageSize, query);
    }
}
