package com.gemini.boot.framework.admin.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gemini.boot.framework.admin.module.sys.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色service层
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过角色id查询权限
     *
     * @param id
     */
    List<Map<String, String>> getAut(Integer id);

    /**
     * 添加权限
     *
     * @param role
     * @param ids
     */
    void save(Role role, Integer[] ids);

    /**
     * 更新角色
     *
     * @param role
     * @param ids
     */
    void updateById(Role role, Integer[] ids);

}
