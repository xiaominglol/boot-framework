package com.gemini.portal.risk.cud.sErrorLog;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SErrorLogDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SErrorLogMapper;
import com.uepay.corebusiness.risk.cud.service.po.SErrorLogPo;
import com.uepay.corebusiness.risk.cud.service.service.SErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 错误日志表
 * @author wenge.cai
 */
@Service
public class SErrorLogServiceImpl extends BaseServiceImpl<SErrorLogDto, SErrorLogPo, SErrorLogMapper> implements SErrorLogService {

    @Override
    public QueryWrapper<SErrorLogPo> wrapper(SErrorLogDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getUserAccount()), "user_account", dto.getUserAccount())
                .eq(!StringUtils.isEmpty(dto.getUserName()), "user_name", dto.getUserName())
                .eq(!StringUtils.isEmpty(dto.getOptTypeId()), "opt_type_id", dto.getOptTypeId())
                .eq(!StringUtils.isEmpty(dto.getOptTypeCode()), "opt_type_code", dto.getOptTypeCode())
                .eq(!StringUtils.isEmpty(dto.getOptTypeName()), "opt_type_name", dto.getOptTypeName())
                .eq(!StringUtils.isEmpty(dto.getDescription()), "description", dto.getDescription())
                .eq(!StringUtils.isEmpty(dto.getUrl()), "url", dto.getUrl())
                .eq(!StringUtils.isEmpty(dto.getMethod()), "method", dto.getMethod())
                .eq(!StringUtils.isEmpty(dto.getParams()), "params", dto.getParams())
                .eq(!StringUtils.isEmpty(dto.getResult()), "result", dto.getResult())
                .eq(!StringUtils.isEmpty(dto.getIp()), "ip", dto.getIp())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime())
                .eq(!StringUtils.isEmpty(dto.getUserAccount()), "user_account", dto.getUserAccount())
                .eq(!StringUtils.isEmpty(dto.getUserName()), "user_name", dto.getUserName())
                .eq(!StringUtils.isEmpty(dto.getOptTypeId()), "opt_type_id", dto.getOptTypeId())
                .eq(!StringUtils.isEmpty(dto.getOptTypeCode()), "opt_type_code", dto.getOptTypeCode())
                .eq(!StringUtils.isEmpty(dto.getOptTypeName()), "opt_type_name", dto.getOptTypeName())
                .eq(!StringUtils.isEmpty(dto.getDescription()), "description", dto.getDescription())
                .eq(!StringUtils.isEmpty(dto.getUrl()), "url", dto.getUrl())
                .eq(!StringUtils.isEmpty(dto.getMethod()), "method", dto.getMethod())
                .eq(!StringUtils.isEmpty(dto.getParams()), "params", dto.getParams())
                .eq(!StringUtils.isEmpty(dto.getResult()), "result", dto.getResult())
                .eq(!StringUtils.isEmpty(dto.getIp()), "ip", dto.getIp())
                .eq(!StringUtils.isEmpty(dto.getCreateDatetime()), "create_datetime", dto.getCreateDatetime());
    }
}
