package com.gemini.portal.risk.cud.sOrg;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SOrgDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SOrgMapper;
import com.uepay.corebusiness.risk.cud.service.po.SOrgPo;
import com.uepay.corebusiness.risk.cud.service.service.SOrgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 组织架构表
 * @author wenge.cai
 */
@Service
public class SOrgServiceImpl extends BaseServiceImpl<SOrgDto, SOrgPo, SOrgMapper> implements SOrgService {

    @Override
    public QueryWrapper<SOrgPo> wrapper(SOrgDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeId()), "org_type_id", dto.getOrgTypeId())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeCode()), "org_type_code", dto.getOrgTypeCode())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeName()), "org_type_name", dto.getOrgTypeName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime())
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeId()), "org_type_id", dto.getOrgTypeId())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeCode()), "org_type_code", dto.getOrgTypeCode())
                .eq(!StringUtils.isEmpty(dto.getOrgTypeName()), "org_type_name", dto.getOrgTypeName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime());
    }
}
