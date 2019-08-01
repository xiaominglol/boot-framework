package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.portal.module.sys.mapper.UserMapper;
import com.gemini.portal.module.sys.model.User;
import com.gemini.portal.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author 小明不读书
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * @Autowired(required=true)一定要加，否则ShiroRealm的userService获取的userDao为null，查询不到用户 原因暂时未知，是因为集成了mybatis-plus，userDao继承了BaseMapper才出现的问题
     */
    @Autowired(required = true)
    UserMapper userMapper;

    @Override
    public User getByAccount(String account) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        return userMapper.selectOne(qw);
    }

    @Override
    public Set<String> getRoleById(String account) {
        return userMapper.getRoleById(account);
    }

    @Override
    public Set<String> getPermissionsById(String account) {
        return userMapper.getPermissionsById(account);
    }

    @Override
    public List<Map<String, String>> getUserRole(String account) {
        return userMapper.getUserRole(account);
    }

    @Override
    public void addUserRole(String account, String[] ids) {
        userMapper.addUserRole(account, ids);
    }

    @Override
    public void deleteUserRole(String account) {
        userMapper.deleteUserRole(account);
    }


}
