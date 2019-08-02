package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysRolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 角色表
 * @author wenge.cai
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRolePo> {

    /**
     * 通过角色id查询权限
     *
     * @param id 主键id
     * @return
     */
    List<Map<String, String>> getAut(Long id);

    /**
     * 添加权限
     *
     * @param id
     * @param ids
     */
    void addAut(@Param("id") Long id, @Param("ids") Long[] ids);

    /**
     * 删除权限
     *
     * @param id
     */
    void deleteAut(Serializable id);
}
