package com.gemini.portal.risk.cud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.cud.service.po.SRolePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * @author wenge.cai
 */
@Mapper
public interface SRoleMapper extends BaseMapper<SRolePo> {
}
