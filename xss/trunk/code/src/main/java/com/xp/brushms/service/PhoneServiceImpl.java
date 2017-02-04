package com.xp.brushms.service;

import com.xp.brushms.dao.PhoneDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hzm on 2015/8/2.
 */
@Repository("phoneService")
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    protected PhoneDao phoneDao;
    @Override
    public Phone getPhone(String id) {
        return phoneDao.getPhone(id);
    }

    @Override
    public void savePhone(Phone phone) {
        phoneDao.savePhone(phone);
    }

    @Override
    public Pagination<Phone> getPhones(int pageNo, int pageSize, String imei, String imsi, String phoneNumber) {
        return phoneDao.getPhones(pageNo, pageSize, imei, imsi, phoneNumber);
    }
}
