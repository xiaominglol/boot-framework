package com.gemini.portal.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.boot.framework.mybatis.po.BasePo;
import com.gemini.boot.framework.mybatis.service.UidService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 单表 - 增删查改
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
@EnableAsync
@Slf4j
public abstract class BaseServiceImpl<Po extends BasePo, Mapper extends BaseMapper<Po>> implements BaseService<Po, Mapper> {

    @Autowired
    protected Mapper mapper;

    @Autowired
    private UidService uidService;

    @Override
    public Long uid() {
        return uidService.getUID();
    }

    @Override
    public Logger log() {
        return log;
    }

    @Override
    public Mapper mapper() {
        return mapper;
    }

}
