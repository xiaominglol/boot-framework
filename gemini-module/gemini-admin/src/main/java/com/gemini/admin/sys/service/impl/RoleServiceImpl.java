package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.RoleDao;
import com.gemini.admin.sys.entity.Role;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 角色service层
 *
 * @author 小明
 * @date 2018-02-12
 */
@Service
@CacheConfig(cacheNames = "roleCache")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    @Cacheable(key = "#id")
    public Role getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return roleDao.selectById(id);
    }

    @Override
    public IPage<Role> getList(IPage<Role> page, Wrapper<Role> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return roleDao.selectPage(page, queryWrapper);
    }

    @Override
    public Role add(Role role) {
        roleDao.insert(role);
        return role;
    }

    @Override
    @CachePut(key = "#result.id")
    public Role update(Role role) {
        roleDao.updateById(role);
        return role;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<Map<String, String>> getAut(Integer id) {
        return roleDao.getAut(id);
    }

    @Override
    public void addAut(Integer id, Integer[] ids) {
        roleDao.addAut(id, ids);
    }

    @Override
    public void deleteAut(Integer id) {
        roleDao.deleteAut(id);
    }

}
