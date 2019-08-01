package com.gemini.portal.risk.cud.sLoginLog;

import com.uepay.corebusiness.risk.base.service.BaseService;
import com.uepay.corebusiness.risk.cud.facade.dto.SLoginLogDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SLoginLogMapper;
import com.uepay.corebusiness.risk.cud.service.po.SLoginLogPo;

/**
 * 登陆日志表
 * @author wenge.cai
 */
public interface SLoginLogService extends BaseService<SLoginLogDto, SLoginLogPo, SLoginLogMapper> {
}
