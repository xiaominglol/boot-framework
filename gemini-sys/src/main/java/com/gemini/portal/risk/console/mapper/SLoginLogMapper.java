package com.gemini.portal.risk.console.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.console.po.SLoginLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Mapper
public interface SLoginLogMapper extends BaseMapper<SLoginLogPo> {
}