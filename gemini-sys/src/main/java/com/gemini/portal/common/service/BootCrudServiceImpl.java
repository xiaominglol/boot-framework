package com.gemini.portal.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.boot.framework.mybatis.po.BasePo;
import com.gemini.boot.framework.mybatis.service.UidService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Service基類的实现
 *
 * @author 小明不读书
 */
@EnableAsync
@Slf4j
public abstract class BootCrudServiceImpl<Po extends BasePo, DetailPo, Mapper extends BaseMapper<Po>, DetailMapper extends BaseMapper<DetailPo>> implements BootCrudService<Po, DetailPo, Mapper, DetailMapper> {

    @Autowired
    protected Mapper mapper;

    @Autowired
    protected DetailMapper detailMapper;

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

    @Override
    public DetailMapper detailMapper() {
        return detailMapper;
    }
}
