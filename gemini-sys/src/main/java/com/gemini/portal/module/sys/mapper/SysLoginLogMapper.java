package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysLoginLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLogPo> {
}
