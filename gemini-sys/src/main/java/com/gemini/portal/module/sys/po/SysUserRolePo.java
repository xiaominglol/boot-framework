package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

/**
 * 用户角色表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_role_menu")
public class SysUserRolePo extends BasePo {

    /**
     * 用户id
     */
    private Long user_id;

    /**
     * 角色id
     */
    private Long role_id;

}
