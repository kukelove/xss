package com.xp.brushms.dao;


import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Phone;

/**
 * Created by hzm on 2015/8/2.
 */
public interface PhoneDao {
    public Phone getPhone(String id);
    public void savePhone(Phone phone);
    public Pagination<Phone> getPhones(int pageNo, int pageSize, String imei, String imsi, String phoneNumber);
}