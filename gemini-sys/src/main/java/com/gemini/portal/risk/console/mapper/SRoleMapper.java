package com.gemini.portal.risk.console.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.console.po.SRolePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * @author wenge.cai
 */
@Mapper
public interface SRoleMapper extends BaseMapper<SRolePo> {
}