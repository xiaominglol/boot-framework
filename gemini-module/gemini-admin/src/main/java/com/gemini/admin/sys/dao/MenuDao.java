package com.gemini.admin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.Menu;
import com.gemini.admin.sys.entity.Org;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Dao层
 *
 * @author 小明
 * @date 2018-02-11
 */
@Repository
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_menu where id = #{id} and status = 1")
    Menu getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_menu where 1 = 1"
            + "<if test='o.id !=null and o.id != \"\"'>"
            + " and (id = #{o.id} or pid = #{o.id}) "
            + "</if>"
            + "<if test='o.name !=null and o.name != \"\"'>"
            + " and name like '%${o.name}%' "
            + "</if>"
            + "</script>")
    List<Menu> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_menu(pid,name,type,url,target,icon,permissions_code,sort,status,opt_id,opt_name)" +
            "values(#{pid},#{name},#{type},#{url},#{target},#{icon},#{permissionsCode},#{sort},#{status},#{optId},#{optName})")
    void add(Menu entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Update("update t_sys_menu set pid = #{pid},name = #{name},type = #{type},url = #{url},target = #{target},icon = #{icon},permissions_code = #{permissionsCode},sort = #{sort},status = #{status},opt_id = #{optId},opt_name = #{optName} where id = #{id}")
    void update(Menu entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_menu where id = #{id}")
    void delete(Object id);

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param account
     * @return
     */
    List<Menu> getByAccount(String account);

    /**
     * 删除菜单权限
     *
     * @param id
     */
    void deleteMenuAut(Integer id);
}