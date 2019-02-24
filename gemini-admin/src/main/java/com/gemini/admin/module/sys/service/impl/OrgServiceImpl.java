package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.module.sys.mapper.OrgMapper;
import com.gemini.admin.module.sys.model.Org;
import com.gemini.admin.module.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "orgCache")
public class OrgServiceImpl implements OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Override
    @Cacheable(key = "#id")
    public Org getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return orgMapper.selectById(id);
    }

    @Override
    public IPage<Org> getPage(IPage<Org> page, Wrapper<Org> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return orgMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Org> getList(Org org) {
        QueryWrapper<Org> qw = new QueryWrapper<>();
        return orgMapper.selectList(qw);
    }

    @Override
    //@CachePut(key="'ORG_CACHE_GETLIST'")
    public Org add(Org entity) {
        System.out.println("11111111111111111111add");
        orgMapper.insert(entity);
        return entity;
    }

    @Override
    @CachePut(key = "#result.id")
    public Org update(Org entity) {
        System.out.println("11111111111111111111update");
        orgMapper.updateById(entity);
        return entity;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        orgMapper.deleteById(id);
    }
}
