package com.gemini.portal.risk.console.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.console.po.SUserPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * @author wenge.cai
 */
@Mapper
public interface SUserMapper extends BaseMapper<SUserPo> {
}