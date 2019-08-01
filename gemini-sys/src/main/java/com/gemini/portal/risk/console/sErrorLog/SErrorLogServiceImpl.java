package com.gemini.portal.risk.console.sErrorLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SErrorLogMapper;
import com.uepay.corebusiness.risk.console.po.SErrorLogPo;
import com.uepay.corebusiness.risk.console.service.SErrorLogService;
import com.uepay.corebusiness.risk.console.vo.SErrorLogVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 错误日志表
 * @author wenge.cai
 */
@Service
public class SErrorLogServiceImpl extends BaseServiceImpl<SErrorLogVo, SErrorLogPo, SErrorLogMapper> implements SErrorLogService {

    @Override
    public QueryWrapper<SErrorLogPo> wrapper(SErrorLogVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getUserAccount()), "user_account", vo.getUserAccount())
                .eq(!StringUtils.isEmpty(vo.getUserName()), "user_name", vo.getUserName())
                .eq(!StringUtils.isEmpty(vo.getOptTypeId()), "opt_type_id", vo.getOptTypeId())
                .eq(!StringUtils.isEmpty(vo.getOptTypeCode()), "opt_type_code", vo.getOptTypeCode())
                .eq(!StringUtils.isEmpty(vo.getOptTypeName()), "opt_type_name", vo.getOptTypeName())
                .eq(!StringUtils.isEmpty(vo.getDescription()), "description", vo.getDescription())
                .eq(!StringUtils.isEmpty(vo.getUrl()), "url", vo.getUrl())
                .eq(!StringUtils.isEmpty(vo.getMethod()), "method", vo.getMethod())
                .eq(!StringUtils.isEmpty(vo.getParams()), "params", vo.getParams())
                .eq(!StringUtils.isEmpty(vo.getResult()), "result", vo.getResult())
                .eq(!StringUtils.isEmpty(vo.getIp()), "ip", vo.getIp())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime())
                .eq(!StringUtils.isEmpty(vo.getUserAccount()), "user_account", vo.getUserAccount())
                .eq(!StringUtils.isEmpty(vo.getUserName()), "user_name", vo.getUserName())
                .eq(!StringUtils.isEmpty(vo.getOptTypeId()), "opt_type_id", vo.getOptTypeId())
                .eq(!StringUtils.isEmpty(vo.getOptTypeCode()), "opt_type_code", vo.getOptTypeCode())
                .eq(!StringUtils.isEmpty(vo.getOptTypeName()), "opt_type_name", vo.getOptTypeName())
                .eq(!StringUtils.isEmpty(vo.getDescription()), "description", vo.getDescription())
                .eq(!StringUtils.isEmpty(vo.getUrl()), "url", vo.getUrl())
                .eq(!StringUtils.isEmpty(vo.getMethod()), "method", vo.getMethod())
                .eq(!StringUtils.isEmpty(vo.getParams()), "params", vo.getParams())
                .eq(!StringUtils.isEmpty(vo.getResult()), "result", vo.getResult())
                .eq(!StringUtils.isEmpty(vo.getIp()), "ip", vo.getIp())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime());
    }
}