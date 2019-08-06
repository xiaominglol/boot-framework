package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysErrorLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 错误日志表
 *
 * @author wenge.cai
 */
@Mapper
public interface SysErrorLogMapper extends BaseMapper<SysErrorLogPo> {
}
