package com.gemini.admin.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.UserDao;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {

    @Autowired(required=true)
    UserDao userDao;

    @Override
    @Cacheable(key = "#id")
    public User getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return userDao.selectById(id);
    }

    @Override
    public IPage<User> getList(IPage<User> page, Wrapper<User> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return userDao.selectPage(page, queryWrapper);
    }

    @Override
    public List<User> selectList(Wrapper<User> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return userDao.selectList(queryWrapper);
    }

    @Override
    public User add(User user) {
        System.out.println("-----------------------add");
        userDao.insert(user);
        return user;
    }

    @Override
    @CachePut(key = "#result.account")
    public User update(User user) {
        System.out.println("----------------------update");
        userDao.updateById(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        System.out.println("------------------delete");
        userDao.deleteById(id);
    }

    @Override
    public Set<String> getRoleById(String account) {
        System.out.println("----------------------getRoleById");
        return userDao.getRoleById(account);
    }

    @Override
    public Set<String> getPermissionsById(String account) {
        System.out.println("---------------------getPermissionsById");
        return userDao.getPermissionsById(account);
    }

    @Override
    public List<Map<String, String>> getUserRole(String account) {
        System.out.println("------------------getUserRole");
        return userDao.getUserRole(account);
    }

    @Override
    public void addUserRole(String account, String[] ids) {
        System.out.println("-------------------addUserRole");
        userDao.addUserRole(account, ids);
    }

    @Override
    public void deleteUserRole(String account) {
        System.out.println("------------------------deleteUserRole");
        userDao.deleteUserRole(account);
    }


}
