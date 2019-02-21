package com.gemini.admin.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gemini.admin.common.service.CrudService;
import com.gemini.admin.sys.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小明
 * @date 2018-02-11
 */
public interface UserService extends CrudService<User> {

    List<User> selectList(Wrapper<User> queryWrapper);
    /**
     * 根据account查询用户角色
     *
     * @param account
     * @return
     */
    Set<String> getRoleById(String account);

    /**
     * 根据account查询用户权限
     *
     * @param account
     * @return
     */
    Set<String> getPermissionsById(String account);

    /**
     * 通过用户账号查询用户角色
     *
     * @param account
     */
    List<Map<String, String>> getUserRole(String account);

    /**
     * 添加用户角色
     *
     * @param account
     * @param ids
     */
    void addUserRole(String account, String[] ids);

    /**
     * 删除权用户角色
     *
     * @param account
     */
    void deleteUserRole(String account);

}
