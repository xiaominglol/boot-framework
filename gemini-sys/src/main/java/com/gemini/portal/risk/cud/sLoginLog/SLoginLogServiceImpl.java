package com.gemini.portal.risk.cud.sLoginLog;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SLoginLogDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SLoginLogMapper;
import com.uepay.corebusiness.risk.cud.service.po.SLoginLogPo;
import com.uepay.corebusiness.risk.cud.service.service.SLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Service
public class SLoginLogServiceImpl extends BaseServiceImpl<SLoginLogDto, SLoginLogPo, SLoginLogMapper> implements SLoginLogService {

    @Override
    public QueryWrapper<SLoginLogPo> wrapper(SLoginLogDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getUserAccount()), "user_account", dto.getUserAccount())
                .eq(!StringUtils.isEmpty(dto.getUserName()), "user_name", dto.getUserName())
                .eq(!StringUtils.isEmpty(dto.getLoginStateId()), "login_state_id", dto.getLoginStateId())
                .eq(!StringUtils.isEmpty(dto.getLoginStateCode()), "login_state_code", dto.getLoginStateCode())
                .eq(!StringUtils.isEmpty(dto.getLoginStateName()), "login_state_name", dto.getLoginStateName())
                .eq(!StringUtils.isEmpty(dto.getMessage()), "message", dto.getMessage())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime())
                .eq(!StringUtils.isEmpty(dto.getUserAccount()), "user_account", dto.getUserAccount())
                .eq(!StringUtils.isEmpty(dto.getUserName()), "user_name", dto.getUserName())
                .eq(!StringUtils.isEmpty(dto.getLoginStateId()), "login_state_id", dto.getLoginStateId())
                .eq(!StringUtils.isEmpty(dto.getLoginStateCode()), "login_state_code", dto.getLoginStateCode())
                .eq(!StringUtils.isEmpty(dto.getLoginStateName()), "login_state_name", dto.getLoginStateName())
                .eq(!StringUtils.isEmpty(dto.getMessage()), "message", dto.getMessage())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime());
    }
}
