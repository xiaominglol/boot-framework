package com.gemini.portal.risk.console.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uepay.corebusiness.risk.console.po.SOptLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志表
 * @author wenge.cai
 */
@Mapper
public interface SOptLogMapper extends BaseMapper<SOptLogPo> {
}