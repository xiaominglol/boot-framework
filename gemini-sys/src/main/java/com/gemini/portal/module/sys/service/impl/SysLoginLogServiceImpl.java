package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.portal.common.service.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysLoginLogMapper;
import com.gemini.portal.module.sys.po.SysLoginLogPo;
import com.gemini.portal.module.sys.service.SysLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
@Service
public class SysLoginLogServiceImpl extends BootCrudServiceImpl<SysLoginLogPo, SysLoginLogMapper> implements SysLoginLogService {

    @Override
    public QueryWrapper<SysLoginLogPo> wrapper(SysLoginLogPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getUserAccount()), "user_account", po.getUserAccount())
                .eq(!StringUtils.isEmpty(po.getUserName()), "user_name", po.getUserName())
                .eq(!StringUtils.isEmpty(po.getLoginStateId()), "login_state_id", po.getLoginStateId())
                .eq(!StringUtils.isEmpty(po.getLoginStateCode()), "login_state_code", po.getLoginStateCode())
                .eq(!StringUtils.isEmpty(po.getLoginStateName()), "login_state_name", po.getLoginStateName())
                .eq(!StringUtils.isEmpty(po.getMessage()), "message", po.getMessage());
//                .eq(!StringUtils.isEmpty(po.getCreateDatetime()), "create_datetime", po.getCreateDatetime());
    }
}
