package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.portal.common.service.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysErrorLogMapper;
import com.gemini.portal.module.sys.po.SysErrorLogPo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 错误日志表
 *
 * @author 小明不读书
 */
@Service
public class SysErrorLogServiceImpl extends BootCrudServiceImpl<SysErrorLogPo, SysErrorLogMapper> implements SysErrorLogService {

    @Override
    public QueryWrapper<SysErrorLogPo> wrapper(SysErrorLogPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getUserAccount()), "user_account", po.getUserAccount())
                .eq(!StringUtils.isEmpty(po.getUserName()), "user_name", po.getUserName())
                .eq(!StringUtils.isEmpty(po.getOptTypeId()), "opt_type_id", po.getOptTypeId())
                .eq(!StringUtils.isEmpty(po.getOptTypeCode()), "opt_type_code", po.getOptTypeCode())
                .eq(!StringUtils.isEmpty(po.getOptTypeName()), "opt_type_name", po.getOptTypeName())
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getUrl()), "url", po.getUrl())
                .eq(!StringUtils.isEmpty(po.getMethod()), "method", po.getMethod())
                .eq(!StringUtils.isEmpty(po.getParams()), "params", po.getParams())
                .eq(!StringUtils.isEmpty(po.getResult()), "result", po.getResult())
                .eq(!StringUtils.isEmpty(po.getIp()), "ip", po.getIp());
//                .eq(!StringUtils.isEmpty(po.getCreateDatetime()), "create_datetime", po.getCreateDatetime());
    }
}
