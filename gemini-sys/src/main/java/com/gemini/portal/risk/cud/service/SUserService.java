package com.gemini.portal.risk.cud.service;

import com.uepay.corebusiness.risk.base.service.BaseService;
import com.uepay.corebusiness.risk.cud.facade.dto.SUserDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SUserMapper;
import com.uepay.corebusiness.risk.cud.service.po.SUserPo;

/**
 * 用户表
 * @author wenge.cai
 */
public interface SUserService extends BaseService<SUserDto, SUserPo, SUserMapper> {
}
