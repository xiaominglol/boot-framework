package com.gemini.portal.risk.console.sLoginLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SLoginLogMapper;
import com.uepay.corebusiness.risk.console.po.SLoginLogPo;
import com.uepay.corebusiness.risk.console.service.SLoginLogService;
import com.uepay.corebusiness.risk.console.vo.SLoginLogVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Service
public class SLoginLogServiceImpl extends BaseServiceImpl<SLoginLogVo, SLoginLogPo, SLoginLogMapper> implements SLoginLogService {

    @Override
    public QueryWrapper<SLoginLogPo> wrapper(SLoginLogVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getUserAccount()), "user_account", vo.getUserAccount())
                .eq(!StringUtils.isEmpty(vo.getUserName()), "user_name", vo.getUserName())
                .eq(!StringUtils.isEmpty(vo.getLoginStateId()), "login_state_id", vo.getLoginStateId())
                .eq(!StringUtils.isEmpty(vo.getLoginStateCode()), "login_state_code", vo.getLoginStateCode())
                .eq(!StringUtils.isEmpty(vo.getLoginStateName()), "login_state_name", vo.getLoginStateName())
                .eq(!StringUtils.isEmpty(vo.getMessage()), "message", vo.getMessage())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime())
                .eq(!StringUtils.isEmpty(vo.getUserAccount()), "user_account", vo.getUserAccount())
                .eq(!StringUtils.isEmpty(vo.getUserName()), "user_name", vo.getUserName())
                .eq(!StringUtils.isEmpty(vo.getLoginStateId()), "login_state_id", vo.getLoginStateId())
                .eq(!StringUtils.isEmpty(vo.getLoginStateCode()), "login_state_code", vo.getLoginStateCode())
                .eq(!StringUtils.isEmpty(vo.getLoginStateName()), "login_state_name", vo.getLoginStateName())
                .eq(!StringUtils.isEmpty(vo.getMessage()), "message", vo.getMessage())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime());
    }
}