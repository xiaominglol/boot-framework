package com.gemini.admin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.entity.Org;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织架构-Dao
 *
 * @author 小明不读书
 * @date 2018-10-24
 */
@Repository
public interface DictDao extends BaseMapper<Dict> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_dict where id = #{id} and status = 1")
    Dict getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_dict where 1 = 1"
            + "<if test='o.id !=null and o.id != \"\"'>"
            + " and (id = #{o.id} or pid = #{o.id}) "
            + "</if>"
            + "<if test='o.name !=null and o.name != \"\"'>"
            + " and name like '%${o.name}%' "
            + "</if>"
            + "<if test='o.code !=null and o.code != \"\"'>"
            + " and (code = #{o.code} or parent_code = #{o.code}) "
            + "</if>"
            + "<if test='o.parentCode !=null and o.parentCode != \"\"'>"
            + " and parent_code = #{o.parentCode} "
            + "</if>"
            + "<if test='o.status !=null and o.status != \"\"'>"
            + " and status = #{o.status} "
            + "</if>"
            + "order by sort"
            + "</script>")
    List<Dict> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_dict(code,parent_code,name,value,type,is_default,remark,sort,status,opt_id,opt_name,opt_time)" +
            "values(#{code},#{parentCode},#{name},#{value},#{type},#{isDefault},#{remark},#{sort},#{status},#{optId},#{optName},#{optTime})")
    void add(Dict entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Update("update t_sys_dict set code = #{code},parent_code = #{parentCode},name = #{name},value = #{value},type = #{type},is_default = #{isDefault},remark = #{remark},sort = #{sort},status = #{status},opt_id = #{optId},opt_name = #{optName} where id = #{id}")
    void update(Dict entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_dict where id = #{id}")
    void delete(Object id);

}