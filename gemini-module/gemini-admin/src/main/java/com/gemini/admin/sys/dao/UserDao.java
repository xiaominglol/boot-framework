package com.gemini.admin.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小明
 * @date 2017-11-10
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_user where account = #{id} and status = 1")
    User getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_user where 1 = 1"
            + "<if test='o.id !=null and o.id != \"\"'>"
            + " and id = #{o.id} "
            + "</if>"
            + "<if test='o.name !=null and o.name != \"\"'>"
            + " and name like '%${o.name}%' "
            + "</if>"
            + "</script>")
    List<User> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_user(account,name,password,picture,org_id,opt_id,opt_name)" +
            "values(#{account},#{name},#{password},#{picture},#{orgId},#{optId},#{optName})")
    void add(User entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Update("update t_sys_user set account = #{account},name = #{name},password = #{password},picture = #{picture},org_id = #{orgId},status = #{status},opt_id = #{optId},opt_name = #{optName} where id = #{id}")
    void update(User entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_user where account = #{id}")
    void delete(Object id);

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
    void addUserRole(@Param(value = "account") String account, @Param(value = "ids") String[] ids);

    /**
     * 删除用户角色
     *
     * @param account
     */
    void deleteUserRole(String account);
}
