package com.gemini.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.module.sys.model.LoginLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登陆日志-Dao
 *
 * @author 小明不读书
 * @date 2018-10-18
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}