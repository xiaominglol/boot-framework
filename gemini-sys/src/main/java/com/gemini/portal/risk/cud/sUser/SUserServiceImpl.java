package com.gemini.portal.risk.cud.sUser;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SUserDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SUserMapper;
import com.uepay.corebusiness.risk.cud.service.po.SUserPo;
import com.uepay.corebusiness.risk.cud.service.service.SUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表
 * @author wenge.cai
 */
@Service
public class SUserServiceImpl extends BaseServiceImpl<SUserDto, SUserPo, SUserMapper> implements SUserService {

    @Override
    public QueryWrapper<SUserPo> wrapper(SUserDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getAccount()), "account", dto.getAccount())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getPassword()), "password", dto.getPassword())
                .eq(!StringUtils.isEmpty(dto.getPicture()), "picture", dto.getPicture())
                .eq(!StringUtils.isEmpty(dto.getOrgId()), "org_id", dto.getOrgId())
                .eq(!StringUtils.isEmpty(dto.getOrgName()), "org_name", dto.getOrgName())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime())
                .eq(!StringUtils.isEmpty(dto.getAccount()), "account", dto.getAccount())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getPassword()), "password", dto.getPassword())
                .eq(!StringUtils.isEmpty(dto.getPicture()), "picture", dto.getPicture())
                .eq(!StringUtils.isEmpty(dto.getOrgId()), "org_id", dto.getOrgId())
                .eq(!StringUtils.isEmpty(dto.getOrgName()), "org_name", dto.getOrgName())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime());
    }
}
