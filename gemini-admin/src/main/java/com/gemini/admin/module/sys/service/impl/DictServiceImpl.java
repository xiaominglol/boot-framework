package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.module.sys.mapper.DictMapper;
import com.gemini.admin.module.sys.model.Dict;
import com.gemini.admin.module.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;


/**
 * @author gemini
 * @date 2018-10-25
 */
@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "dictCache")
public class DictServiceImpl implements DictService {

    @Autowired
    DictMapper dictMapper;

    @Override
    @Cacheable(key = "#id")
    public Dict getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return dictMapper.selectById(id);
    }

    @Override
    public IPage<Dict> getPage(IPage<Dict> page, Wrapper<Dict> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return dictMapper.selectPage(page, queryWrapper);
    }


    @Override
    public List<Dict> getList(Dict dict) {
        QueryWrapper<Dict> ew = new QueryWrapper<>();
        if(!StringUtils.isEmpty(dict.getParentCode())){
            ew.eq("parent_code", dict.getParentCode());
        }
        if(!StringUtils.isEmpty(dict.getStatus())){
            ew.eq("status", dict.getStatus());
        }
        return dictMapper.selectList(ew);
    }

    @Override
    public Dict add(Dict dict) {
        dictMapper.insert(dict);
        return dict;
    }

    @Override
    @CachePut(key = "#result.id")
    public Dict update(Dict dict) {
        dictMapper.updateById(dict);
        return dict;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        dictMapper.deleteById(id);
    }


}
