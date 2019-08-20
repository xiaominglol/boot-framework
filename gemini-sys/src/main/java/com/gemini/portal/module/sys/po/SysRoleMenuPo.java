package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

/**
 * 角色菜单表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_role_menu")
public class SysRoleMenuPo extends BasePo {

    /**
     * 角色id
     */
    private Long role_id;

    /**
     * 菜单id
     */
    private Long menu_id;

}
