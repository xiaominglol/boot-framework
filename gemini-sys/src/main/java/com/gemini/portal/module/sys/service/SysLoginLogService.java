package com.gemini.portal.module.sys.service;

import com.gemini.boot.framework.mybatis.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysLoginLogMapper;
import com.gemini.portal.module.sys.po.SysLoginLogPo;

/**
 * 登陆日志表
 *
 * @author wenge.cai
 */
public interface SysLoginLogService extends BootCrudService<SysLoginLogPo, SysLoginLogMapper> {
}
