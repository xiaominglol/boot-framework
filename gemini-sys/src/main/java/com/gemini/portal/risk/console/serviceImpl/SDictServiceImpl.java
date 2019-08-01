package com.gemini.portal.risk.console.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SDictMapper;
import com.uepay.corebusiness.risk.console.po.SDictPo;
import com.uepay.corebusiness.risk.console.service.SDictService;
import com.uepay.corebusiness.risk.console.vo.SDictVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 字典表
 * @author wenge.cai
 */
@Service
public class SDictServiceImpl extends BaseServiceImpl<SDictVo, SDictPo, SDictMapper> implements SDictService {

    @Override
    public QueryWrapper<SDictPo> wrapper(SDictVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getCode()), "code", vo.getCode())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getDescription()), "description", vo.getDescription())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime())
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getCode()), "code", vo.getCode())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getDescription()), "description", vo.getDescription())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime());
    }
}