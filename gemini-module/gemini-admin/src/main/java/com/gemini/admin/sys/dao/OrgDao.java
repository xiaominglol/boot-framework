package com.gemini.admin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.Org;
import com.gemini.admin.sys.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织架构-Dao
 *
 * @author 小明不读书
 * @date 2018-07-29
 */
@Repository
public interface OrgDao extends BaseMapper<Org> {


    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_org where 1 = 1"
            + "<if test='o.id !=null and o.id != \"\"'>"
            + " and (id = #{o.id} or pid = #{o.id}) "
            + "</if>"
            + "<if test='o.name !=null and o.name != \"\"'>"
            + " and name like '%${o.name}%' "
            + "</if>"
            + "</script>")
    List<Org> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_org(pid,name,type,sort,status,opt_id,opt_name)" +
            "values(#{pid},#{name},#{type},#{sort},#{status},#{optId},#{optName})")
    void add(Org entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
//    @Update("update t_sys_org set pid = #{pid},name = #{name},type = #{type},sort = #{sort},status = #{status},opt_id = #{optId},opt_name = #{optName} where id = #{id}")
//    void update(Org entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_org where id = #{id}")
    void delete(Object id);

}