package com.gemini.portal.risk.console.sErrorLog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.console.po.SErrorLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 错误日志表
 * @author wenge.cai
 */
@Mapper
public interface SErrorLogMapper extends BaseMapper<SErrorLogPo> {
}