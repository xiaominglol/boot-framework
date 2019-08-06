package com.gemini.portal.module.sys.service;

import com.gemini.boot.framework.mybatis.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysOrgMapper;
import com.gemini.portal.module.sys.po.SysOrgPo;

/**
 * 组织架构表
 *
 * @author wenge.cai
 */
public interface SysOrgService extends BootCrudService<SysOrgPo, SysOrgMapper> {
}
