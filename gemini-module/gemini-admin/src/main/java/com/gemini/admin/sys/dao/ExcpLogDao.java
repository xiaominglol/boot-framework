package com.gemini.admin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.Org;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 异常日志-Dao
 *
 * @author 小明不读书
 * @date 2018-10-18
 */
@Repository
public interface ExcpLogDao extends BaseMapper<ExcpLog> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    @Select("select * from t_sys_excp_log where id = #{id}")
    ExcpLog getById(Object id);

    /**
     * 获取所有数据
     *
     * @return
     */
    @Select("<script>"
            + "select * from t_sys_excp_log where 1 = 1"
            + "<if test='o.userName !=null and o.userName != \"\"'>"
            + " and (user_account like #{o.userName} or user_name like #{o.userName}) "
            + "</if>"
            + " order by excp_time desc "
            + "</script>")
    List<ExcpLog> getList(@Param("o") Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Insert("insert into t_sys_excp_log(user_account,user_name,url,request_param,response_result)" +
            "values(#{userAccount},#{userName},#{url},#{requestParam},#{responseResult})")
    void add(ExcpLog entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    @Delete("delete from t_sys_excp_log where id = #{id}")
    void delete(Object id);

}