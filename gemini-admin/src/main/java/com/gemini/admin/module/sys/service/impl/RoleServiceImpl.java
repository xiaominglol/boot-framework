package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.module.sys.mapper.RoleMapper;
import com.gemini.admin.module.sys.model.Role;
import com.gemini.admin.module.sys.service.RoleService;
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


/**
 * 角色service层
 *
 * @author 小明
 * @date 2018-02-12
 */
@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "roleCache")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    @Cacheable(key = "#id")
    public Role getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> getList(Role role) {
        return null;
    }

    @Override
    public IPage<Role> getPage(IPage<Role> page, Wrapper<Role> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return roleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Role add(Role role) {
        roleMapper.insert(role);
        return role;
    }

    @Override
    @CachePut(key = "#result.id")
    public Role update(Role role) {
        roleMapper.updateById(role);
        return role;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        roleMapper.deleteById(id);
    }

    @Override
    public List<Map<String, String>> getAut(Integer id) {
        return roleMapper.getAut(id);
    }

    @Override
    public void addAut(Integer id, Integer[] ids) {
        roleMapper.addAut(id, ids);
    }

    @Override
    public void deleteAut(Integer id) {
        roleMapper.deleteAut(id);
    }

}
