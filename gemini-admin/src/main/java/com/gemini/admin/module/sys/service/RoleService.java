package com.gemini.admin.module.sys.service;

import com.gemini.admin.common.mvc.service.CrudService;
import com.gemini.admin.module.sys.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色service层
 *
 * @author 小明
 * @date 2018-02-11
 */
public interface RoleService extends CrudService<Role> {

    /**
     * 通过角色id查询权限
     *
     * @param id
     */
    List<Map<String, String>> getAut(Integer id);

    /**
     * 添加权限
     *
     * @param id
     * @param ids
     */
    void addAut(Integer id, Integer[] ids);

    /**
     * 删除权限
     *
     * @param id
     */
    void deleteAut(Integer id);

}
