package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.OrgDao;
import com.gemini.admin.sys.entity.Org;
import com.gemini.admin.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@CacheConfig(cacheNames = "orgCache")
public class OrgServiceImpl implements OrgService {

    @Autowired
    OrgDao orgDao;

    @Override
    @Cacheable(key = "#id")
    public Org getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return orgDao.selectById(id);
    }

    @Override
    public IPage<Org> getList(IPage<Org> page, Wrapper<Org> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return orgDao.selectPage(page, queryWrapper);
    }

    @Override
    public List<Org> selectList(Wrapper<Org> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return orgDao.selectList(queryWrapper);
    }

    @Override
    //@CachePut(key="'ORG_CACHE_GETLIST'")
    public Org add(Org entity) {
        System.out.println("11111111111111111111add");
        orgDao.insert(entity);
        return entity;
    }

    @Override
    @CachePut(key = "#result.id")
    public Org update(Org entity) {
        System.out.println("11111111111111111111update");
        orgDao.updateById(entity);
        return entity;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        orgDao.deleteById(id);
    }
}
