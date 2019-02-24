package com.gemini.admin.module.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.common.mvc.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 菜单表    t_sys_menu
 *
 * @author 小明不读书
 * @date 2017-11-02
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_menu")
public class Menu extends BaseEntity<Menu> {

    /**
     * 自增主键：菜单ID
     */
    private Integer id;

    /**
     * 上级菜单，如果为null，则为顶级菜单
     */
    private Integer pid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型（1=一级菜单，2=二级菜单，3=三级菜单，4=按钮）
     */
    private String type;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单打开方式
     */
    private String target;

    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 权限编码
     */
    private String permissionsCode;

    /**
     * 排序
     */
    private Integer sort;


}