package com.gemini.portal.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.portal.module.sys.po.SysMenuPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单表
 *
 * @author 小明不读书
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuPo> {

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param userId
     * @return
     */
    List<SysMenuPo> getByUserId(Long userId);

    /**
     * 删除菜单权限
     *
     * @param id
     */
    void deleteMenuAut(Long id);
}
