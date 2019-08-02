package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysDictMapper;
import com.gemini.portal.module.sys.po.SysDictPo;
import com.gemini.portal.module.sys.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 字典表
 * @author wenge.cai
 */
@Service
public class SysDictServiceImpl extends BootCrudServiceImpl<SysDictPo, SysDictMapper> implements SysDictService {

    @Override
    public QueryWrapper<SysDictPo> wrapper(SysDictPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getPid()), "pid", po.getPid())
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyId()), "modify_id", po.getModifyId())
                .eq(!StringUtils.isEmpty(po.getModifyName()), "modify_name", po.getModifyName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
