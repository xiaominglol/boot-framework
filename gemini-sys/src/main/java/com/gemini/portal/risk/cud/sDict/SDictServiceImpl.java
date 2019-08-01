package com.gemini.portal.risk.cud.sDict;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SDictDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SDictMapper;
import com.uepay.corebusiness.risk.cud.service.po.SDictPo;
import com.uepay.corebusiness.risk.cud.service.service.SDictService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 字典表
 * @author wenge.cai
 */
@Service
public class SDictServiceImpl extends BaseServiceImpl<SDictDto, SDictPo, SDictMapper> implements SDictService {

    @Override
    public QueryWrapper<SDictPo> wrapper(SDictDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getCode()), "code", dto.getCode())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getDescription()), "description", dto.getDescription())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime())
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getCode()), "code", dto.getCode())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getDescription()), "description", dto.getDescription())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime());
    }
}
