package com.gemini.boot.framework.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.boot.framework.admin.module.sys.model.Org;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 组织架构-Dao
 *
 * @author 小明不读书
 * @date 2018-07-29
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

}