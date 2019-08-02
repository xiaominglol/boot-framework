package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysOptLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志表
 * @author wenge.cai
 */
@Mapper
public interface SysOptLogMapper extends BaseMapper<SysOptLogPo> {
}
