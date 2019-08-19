package com.gemini.portal.module.sys.service;

import com.gemini.portal.common.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysErrorLogMapper;
import com.gemini.portal.module.sys.po.SysErrorLogPo;

/**
 * 错误日志表
 *
 * @author wenge.cai
 */
public interface SysErrorLogService extends BootCrudService<SysErrorLogPo, SysErrorLogMapper> {
}
