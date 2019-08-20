package com.gemini.portal.module.sys.service;

import com.gemini.portal.common.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysRoleMapper;
import com.gemini.portal.module.sys.mapper.SysRoleMenuMapper;
import com.gemini.portal.module.sys.po.SysRoleMenuPo;
import com.gemini.portal.module.sys.po.SysRolePo;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author 小明不读书
 */
public interface SysRoleService extends BootCrudService<SysRolePo, SysRoleMenuPo, SysRoleMapper, SysRoleMenuMapper> {

    /**
     * 通过角色id查询权限
     *
     * @param id
     */
    List<Map<String, String>> getAut(Long id);

    /**
     * 添加权限
     *
     * @param role
     * @param ids
     */
    void save(SysRolePo role, Long[] ids);

    /**
     * 更新角色
     *
     * @param role
     * @param ids
     */
    void updateById(SysRolePo role, Long[] ids);

    boolean removeById(Long id);
}
