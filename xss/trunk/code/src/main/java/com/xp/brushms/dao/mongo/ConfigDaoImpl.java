package com.xp.brushms.dao.mongo;


import com.xp.brushms.dao.ConfigDao;
import com.xp.brushms.entity.GlobalConfig;
import org.springframework.stereotype.Repository;

/**
 * Created by hzm on 2015-09-10.
 */
@Repository("configDao")
public class ConfigDaoImpl extends MongodbBaseDao2Impl implements ConfigDao {

    public double getDouble(String name, double defv) {
        Object v = get(name);
        if (v == null) return defv;
        return Double.parseDouble(v.toString());
    }


    public int getInt(String name, int defv) {
        Object v = get(name);
        if (v == null) return defv;
        return Integer.parseInt(v.toString());
    }


    public float getFloat(String name, float defv) {
        Object v = get(name);
        if (v == null) return defv;
        return Float.parseFloat(v.toString());
    }


    public String getString(String name, String defv) {
        Object v = get(name);
        if (v == null) return defv;
        return v.toString();
    }


    public Object get(String name) {
        GlobalConfig config = findById(name, GlobalConfig.class);
        if (config == null) return null;
        return config.getValue();
    }


    public void set(String name, Object value) {
        GlobalConfig config = new GlobalConfig();
        config.setId(name);
        config.setValue(value);
        save(config);
    }


    public long getLong(String name, long defv) {
        Object v = get(name);
        if (v == null) return defv;
        return Long.parseLong(v.toString());
    }
}
