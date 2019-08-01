package com.gemini.portal.risk.console.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SRoleMapper;
import com.uepay.corebusiness.risk.console.po.SRolePo;
import com.uepay.corebusiness.risk.console.service.SRoleService;
import com.uepay.corebusiness.risk.console.vo.SRoleVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 角色表
 * @author wenge.cai
 */
@Service
public class SRoleServiceImpl extends BaseServiceImpl<SRoleVo, SRolePo, SRoleMapper> implements SRoleService {

    @Override
    public QueryWrapper<SRolePo> wrapper(SRoleVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getCode()), "code", vo.getCode())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime())
                .eq(!StringUtils.isEmpty(vo.getCode()), "code", vo.getCode())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime());
    }
}