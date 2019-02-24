package com.gemini.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.module.sys.model.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色Dao层
 *
 * @author 小明
 * @date 2018-02-12
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_role where id = #{id} and status = 1")
    Role getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_role where 1 = 1"
            + "<if test='o.name !=null and o.name != \"\"'>"
            + " and name like '%${o.name}%' "
            + "</if>"
            + "</script>")
    List<Role> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_role(code,name,sort,status,opt_id,opt_name)" +
            "values(#{code},#{name},#{sort},#{status},#{optId},#{optName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Role entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Update("update t_sys_role set code = #{code},name = #{name},sort = #{sort},status = #{status},opt_id = #{optId},opt_name = #{optName} where id = #{id}")
    void update(Role entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_role where id = #{id}")
    void delete(Object id);

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
    void addAut(@Param("id") Integer id, @Param("ids") Integer[] ids);

    /**
     * 删除权限
     *
     * @param id
     */
    void deleteAut(Integer id);

}