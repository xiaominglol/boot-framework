package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.LoginLogDao;
import com.gemini.admin.sys.entity.LoginLog;
import com.gemini.admin.sys.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
public class LoingLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogDao loginLogDao;

    @Override
    public LoginLog getById(Serializable id) {
        return loginLogDao.selectById(id);
    }

    @Override
    public IPage<LoginLog> getList(IPage<LoginLog> page, Wrapper<LoginLog> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return loginLogDao.selectPage(page, queryWrapper);
    }

    @Override
    public LoginLog add(LoginLog loginLog) {
        loginLogDao.insert(loginLog);
        return loginLog;
    }

    @Override
    public LoginLog update(LoginLog entity) {
        return entity;
    }

    @Override
    public void delete(Serializable id) {
        loginLogDao.delete(id);
    }


}
