package com.gemini.portal.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 主子表 - 增删查改
 * <p>
 * 1、子表是自己
 * 2、子表是其他
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public abstract class BaseDetailServiceImpl<Po, DetailPo, Mapper extends BaseMapper<Po>, DetailMapper extends BaseMapper<DetailPo>> extends BaseServiceImpl<Po, Mapper> implements BaseDetailService<Po, DetailPo, Mapper, DetailMapper> {

    @Autowired
    protected DetailMapper detailMapper;

    @Override
    public DetailMapper detailMapper() {
        return detailMapper;
    }
}
