package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.DictDao;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@CacheConfig(cacheNames = "dictCache")
public class DictServiceImpl implements DictService {

    @Autowired
    DictDao dictDao;

    @Override
    @Cacheable(key = "#id")
    public Dict getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return dictDao.selectById(id);
    }

    @Override
    public IPage<Dict> getList(IPage<Dict> page, Wrapper<Dict> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return dictDao.selectPage(page, queryWrapper);
    }


    @Override
    public List<Dict> selectList(Wrapper<Dict> queryWrapper) {
        return dictDao.selectList(queryWrapper);
    }

    @Override
    public Dict add(Dict dict) {
        dictDao.insert(dict);
        return dict;
    }

    @Override
    @CachePut(key = "#result.id")
    public Dict update(Dict dict) {
        dictDao.updateById(dict);
        return dict;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        dictDao.deleteById(id);
    }


}
