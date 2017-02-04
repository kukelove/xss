package com.xp.brushms.dao;

import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Code;
import com.xp.brushms.entity.UaLib;

import java.util.List;

/**
 * Created by 18370 on 2016/9/27.
 */
public interface CodeDao {
    public Code getById(String id);
    public void saveItem(Code code);
    public void remove(String id);
    public Code getByName(String name);
    public Pagination<Code> getCodes(int pageNo, int pageSize);
    public List<Code> getCodes();
}
