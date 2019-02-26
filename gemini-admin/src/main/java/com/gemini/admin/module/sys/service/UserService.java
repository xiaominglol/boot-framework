package com.gemini.admin.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gemini.admin.module.sys.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface UserService extends IService<User> {

    /**
     * 根据account查询用户
     *
     * @param account
     * @return
     */
    User getByAccount(String account);

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
     * @return
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
