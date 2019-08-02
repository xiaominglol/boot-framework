package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysDictPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * @author wenge.cai
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDictPo> {
}
