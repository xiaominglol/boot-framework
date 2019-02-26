package com.gemini.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.module.sys.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色Dao层
 *
 * @author 小明不读书
 * @date 2018-02-12
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

//    /**
//     * 插入数据
//     *
//     * @param entity
//     * @return
//     */
//    @Insert("insert into t_sys_role(code,name,sort,status,opt_id,opt_name)" +
//            "values(#{code},#{name},#{sort},#{status},#{optId},#{optName})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    void add(Role entity);

    /**
     * 通过角色id查询权限
     *
     * @param id 主键id
     * @return
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