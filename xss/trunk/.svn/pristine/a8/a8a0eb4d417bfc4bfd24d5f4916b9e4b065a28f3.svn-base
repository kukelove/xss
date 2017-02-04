package com.xp.brushms.service;


import com.xp.brushms.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzm on 2015-09-10.
 */
@Repository("configService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    protected ConfigDao configDao;

    public static class Value {
        public Object value;
        public long time;
    }
    private static Map<String, Value> m = new HashMap<String, Value>();
    public synchronized static Object getCacheValue(String name, long refreshTime) {
        Value v = m.get(name);
        if (v == null) return null;
        if (new Date().getTime() > v.time + refreshTime) {
            return null;
        }
        return v.value;
    }
    public synchronized static void setCacheValue(String name, Object value) {
        Value v = new Value();
        v.value = value;
        v.time = new Date().getTime();
        m.put(name, v);
    }
    @Override
    public double getDouble(String name, double defv, long refreshTime) {
        Object v = getCacheValue(name, refreshTime);
        if (v != null) {
            return (Double)v;
        }
        double dv = configDao.getDouble(name, defv);
        setCacheValue(name, dv);
        return dv;
    }

    @Override
    public double getDouble(String name, double defv) {
        return configDao.getDouble(name, defv);
    }

    @Override
    public int getInt(String name, int defv) {
        return configDao.getInt(name, defv);
    }

    @Override
    public float getFloat(String name, float defv) {
        return configDao.getFloat(name, defv);
    }

    @Override
    public String getString(String name, String defv) {
        return configDao.getString(name, defv);
    }

    @Override
    public Object get(String name) {
        return configDao.get(name);
    }

    @Override
    public void set(String name, Object value) {
        configDao.set(name, value);
    }

    @Override
    public long getLong(String name, long defv) {
        Object v = getCacheValue(name, defv);
        if (v != null) {
            return (Long)v;
        }
        long lv = configDao.getLong(name, defv);
        setCacheValue(name, defv);
        return lv;
    }
}
