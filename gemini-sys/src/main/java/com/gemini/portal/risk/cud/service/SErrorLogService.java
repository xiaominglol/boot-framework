package com.gemini.portal.risk.cud.service;

import com.uepay.corebusiness.risk.base.service.BaseService;
import com.uepay.corebusiness.risk.cud.facade.dto.SErrorLogDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SErrorLogMapper;
import com.uepay.corebusiness.risk.cud.service.po.SErrorLogPo;

/**
 * 错误日志表
 * @author wenge.cai
 */
public interface SErrorLogService extends BaseService<SErrorLogDto, SErrorLogPo, SErrorLogMapper> {
}
