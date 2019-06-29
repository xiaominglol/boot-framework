package com.gemini.boot.framework.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.boot.framework.admin.module.sys.model.ExcpLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 异常日志-Dao
 *
 * @author 小明不读书
 * @date 2018-10-18
 */
@Mapper
public interface ExcpLogMapper extends BaseMapper<ExcpLog> {

}