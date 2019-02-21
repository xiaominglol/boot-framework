package com.gemini.admin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.OptLog;
import com.gemini.admin.sys.entity.Org;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志-Dao
 *
 * @author 小明不读书
 * @date 2018-10-18
 */
@Repository
public interface OptLogDao extends BaseMapper<OptLog> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_opt_log where id = #{id}")
    OptLog getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_opt_log where 1 = 1"
            + "<if test='o.userName !=null and o.userName != \"\"'>"
            + " and (user_account like #{o.userName} or user_name like #{o.userName}) "
            + "</if>"
            + " order by opt_time desc "
            + "</script>")
    List<OptLog> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_opt_log(user_account,user_name,type,url,request_param,response_result)" +
            "values(#{userAccount},#{userName},#{type},#{url},#{requestParam},#{responseResult})")
    void add(OptLog entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_opt_log where id = #{id}")
    void delete(Object id);

}