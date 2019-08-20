package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysLoginLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLogPo> {
}
