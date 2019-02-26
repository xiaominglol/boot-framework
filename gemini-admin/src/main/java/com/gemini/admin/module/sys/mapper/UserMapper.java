package com.gemini.admin.module.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.module.sys.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小明不读书
 * @date 2017-11-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

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
    void addUserRole(@Param(value = "account") String account, @Param(value = "ids") String[] ids);

    /**
     * 删除用户角色
     *
     * @param account
     */
    void deleteUserRole(String account);
}
