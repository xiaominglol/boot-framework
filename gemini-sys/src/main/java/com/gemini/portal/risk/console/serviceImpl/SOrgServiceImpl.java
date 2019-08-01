package com.gemini.portal.risk.console.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SOrgMapper;
import com.uepay.corebusiness.risk.console.po.SOrgPo;
import com.uepay.corebusiness.risk.console.service.SOrgService;
import com.uepay.corebusiness.risk.console.vo.SOrgVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 组织架构表
 * @author wenge.cai
 */
@Service
public class SOrgServiceImpl extends BaseServiceImpl<SOrgVo, SOrgPo, SOrgMapper> implements SOrgService {

    @Override
    public QueryWrapper<SOrgPo> wrapper(SOrgVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeId()), "org_type_id", vo.getOrgTypeId())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeCode()), "org_type_code", vo.getOrgTypeCode())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeName()), "org_type_name", vo.getOrgTypeName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime())
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeId()), "org_type_id", vo.getOrgTypeId())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeCode()), "org_type_code", vo.getOrgTypeCode())
                .eq(!StringUtils.isEmpty(vo.getOrgTypeName()), "org_type_name", vo.getOrgTypeName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime());
    }
}