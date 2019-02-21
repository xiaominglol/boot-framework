package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.ExcpLogDao;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.service.ExcpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
public class ExcpLogServiceImpl implements ExcpLogService {

    @Autowired
    ExcpLogDao excpLogDao;

    @Override
    public ExcpLog getById(Serializable id) {
        return excpLogDao.selectById(id);
    }

    @Override
    public IPage<ExcpLog> getList(IPage<ExcpLog> page, Wrapper<ExcpLog> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return excpLogDao.selectPage(page, queryWrapper);
    }

    @Override
    public ExcpLog add(ExcpLog excpLog) {
        excpLogDao.insert(excpLog);
        return excpLog;
    }

    @Override
    public ExcpLog update(ExcpLog entity) {
        return entity;
    }

    @Override
    public void delete(Serializable id) {
        excpLogDao.delete(id);
    }


}
