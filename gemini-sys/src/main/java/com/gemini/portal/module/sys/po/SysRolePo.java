package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;

/**
 * 角色表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_role")
public class SysRolePo extends BaseDetailPo<SysRoleMenuPo> {

    /**
     * 主鍵ID
     */
    private Long id;

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
    private String sort;
}
