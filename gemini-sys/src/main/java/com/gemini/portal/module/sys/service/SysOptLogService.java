package com.gemini.portal.module.sys.service;

import com.gemini.boot.framework.mybatis.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysOptLogMapper;
import com.gemini.portal.module.sys.po.SysOptLogPo;

/**
 * 操作日志表
 *
 * @author wenge.cai
 */
public interface SysOptLogService extends BootCrudService<SysOptLogPo, SysOptLogMapper> {
}
