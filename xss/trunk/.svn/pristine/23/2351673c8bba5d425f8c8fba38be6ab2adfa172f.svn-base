package com.xp.brushms.service;

import com.xp.brushms.dao.CodeDao;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 18370 on 2016/9/27.
 */
@Repository("codeService")
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Override
    public Code getById(String id) {
        return codeDao.getById(id);
    }

    @Override
    public Code getByName(String name) {
        return codeDao.getByName(name);
    }

    @Override
    public void saveItem(Code code) {
        codeDao.saveItem(code);
    }

    @Override
    public void remove(String id) {
        codeDao.remove(id);
    }

    @Override
    public Pagination<Code> getCodes(int pageNo, int pageSize) {
        return codeDao.getCodes(pageNo, pageSize);
    }

    @Override
    public List<Code> getCodes() {
        return codeDao.getCodes();
    }
}
