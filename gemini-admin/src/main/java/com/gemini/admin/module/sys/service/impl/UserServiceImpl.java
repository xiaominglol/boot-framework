package com.gemini.admin.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.module.sys.mapper.UserMapper;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {

    /**
     * @Autowired(required=true)一定要加，否则ShiroRealm的userService获取的userDao为null，查询不到用户
     * 原因暂时未知，是因为集成了mybatis-plus，userDao继承了BaseMapper才出现的问题
     */
    @Autowired(required = true)
    UserMapper userMapper;

    @Override
    @Cacheable(key = "#id")
    public User getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return userMapper.selectById(id);
    }

    @Override
    public IPage<User> getPage(IPage<User> page, Wrapper<User> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<User> getList(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        return userMapper.selectList(qw);
    }

    @Override
    public User getByAccount(String account) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        return userMapper.selectOne(qw);
    }

    @Override
    public User add(User user) {
        System.out.println("-----------------------add");
        userMapper.insert(user);
        return user;
    }

    @Override
    @CachePut(key = "#result.account")
    public User update(User user) {
        System.out.println("----------------------update");
        userMapper.updateById(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        System.out.println("------------------delete");
        userMapper.deleteById(id);
    }

    @Override
    public Set<String> getRoleById(String account) {
        System.out.println("----------------------getRoleById");
        return userMapper.getRoleById(account);
    }

    @Override
    public Set<String> getPermissionsById(String account) {
        System.out.println("---------------------getPermissionsById");
        return userMapper.getPermissionsById(account);
    }

    @Override
    public List<Map<String, String>> getUserRole(String account) {
        System.out.println("------------------getUserRole");
        return userMapper.getUserRole(account);
    }

    @Override
    public void addUserRole(String account, String[] ids) {
        System.out.println("-------------------addUserRole");
        userMapper.addUserRole(account, ids);
    }

    @Override
    public void deleteUserRole(String account) {
        System.out.println("------------------------deleteUserRole");
        userMapper.deleteUserRole(account);
    }


}
