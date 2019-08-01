package com.gemini.portal.risk.console.sOptLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SOptLogMapper;
import com.uepay.corebusiness.risk.console.po.SOptLogPo;
import com.uepay.corebusiness.risk.console.service.SOptLogService;
import com.uepay.corebusiness.risk.console.vo.SOptLogVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 操作日志表
 * @author wenge.cai
 */
@Service
public class SOptLogServiceImpl extends BaseServiceImpl<SOptLogVo, SOptLogPo, SOptLogMapper> implements SOptLogService {

    @Override
    public QueryWrapper<SOptLogPo> wrapper(SOptLogVo vo) {
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
                .eq(!StringUtils.isEmpty(vo.getTime()), "time", vo.getTime())
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
                .eq(!StringUtils.isEmpty(vo.getTime()), "time", vo.getTime())
                .eq(!StringUtils.isEmpty(vo.getCreateDatetime()), "create_datetime", vo.getCreateDatetime());
    }
}