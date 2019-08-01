package com.gemini.portal.risk.console.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SUserMapper;
import com.uepay.corebusiness.risk.console.po.SUserPo;
import com.uepay.corebusiness.risk.console.service.SUserService;
import com.uepay.corebusiness.risk.console.vo.SUserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表
 * @author wenge.cai
 */
@Service
public class SUserServiceImpl extends BaseServiceImpl<SUserVo, SUserPo, SUserMapper> implements SUserService {

    @Override
    public QueryWrapper<SUserPo> wrapper(SUserVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getAccount()), "account", vo.getAccount())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getPassword()), "password", vo.getPassword())
                .eq(!StringUtils.isEmpty(vo.getPicture()), "picture", vo.getPicture())
                .eq(!StringUtils.isEmpty(vo.getOrgId()), "org_id", vo.getOrgId())
                .eq(!StringUtils.isEmpty(vo.getOrgName()), "org_name", vo.getOrgName())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime())
                .eq(!StringUtils.isEmpty(vo.getAccount()), "account", vo.getAccount())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getPassword()), "password", vo.getPassword())
                .eq(!StringUtils.isEmpty(vo.getPicture()), "picture", vo.getPicture())
                .eq(!StringUtils.isEmpty(vo.getOrgId()), "org_id", vo.getOrgId())
                .eq(!StringUtils.isEmpty(vo.getOrgName()), "org_name", vo.getOrgName())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime());
    }
}