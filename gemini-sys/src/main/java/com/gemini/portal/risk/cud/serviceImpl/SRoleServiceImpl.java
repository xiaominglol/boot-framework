package com.gemini.portal.risk.cud.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SRoleDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SRoleMapper;
import com.uepay.corebusiness.risk.cud.service.po.SRolePo;
import com.uepay.corebusiness.risk.cud.service.service.SRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 角色表
 * @author wenge.cai
 */
@Service
public class SRoleServiceImpl extends BaseServiceImpl<SRoleDto, SRolePo, SRoleMapper> implements SRoleService {

    @Override
    public QueryWrapper<SRolePo> wrapper(SRoleDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getCode()), "code", dto.getCode())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime())
                .eq(!StringUtils.isEmpty(dto.getCode()), "code", dto.getCode())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime());
    }
}
