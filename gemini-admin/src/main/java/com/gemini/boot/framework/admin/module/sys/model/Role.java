package com.gemini.boot.framework.admin.module.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.common.web.mvc.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色表    t_sys_role
 *
 * @author 小明不读书
 * @date 2018-02-12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_role")
public class Role extends BaseEntity<Role> {

    /**
     * 自增主键：角色ID
     */
    private Integer id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

}