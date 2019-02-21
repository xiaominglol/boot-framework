package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.OptLogDao;
import com.gemini.admin.sys.entity.OptLog;
import com.gemini.admin.sys.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
public class OptLogServiceImpl implements OptLogService {

    @Autowired
    OptLogDao optLogDao;

    @Override
    public OptLog getById(Serializable id) {
        return optLogDao.selectById(id);
    }

    @Override
    public IPage<OptLog> getList(IPage<OptLog> page, Wrapper<OptLog> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return optLogDao.selectPage(page, queryWrapper);
    }

    @Override
    public OptLog add(OptLog optLog) {
        optLogDao.insert(optLog);
        return optLog;
    }

    @Override
    public OptLog update(OptLog entity) {
        return entity;
    }

    @Override
    public void delete(Serializable id) {
        optLogDao.delete(id);
    }


}
