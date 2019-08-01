package com.gemini.portal.risk.console.service;

import com.uepay.corebusiness.risk.base.service.BaseService;
import com.uepay.corebusiness.risk.console.vo.SErrorLogVo;
import com.uepay.corebusiness.risk.console.mapper.SErrorLogMapper;
import com.uepay.corebusiness.risk.console.po.SErrorLogPo;

/**
 * 错误日志表
 * @author wenge.cai
 */
public interface SErrorLogService extends BaseService<SErrorLogVo, SErrorLogPo, SErrorLogMapper> {
}