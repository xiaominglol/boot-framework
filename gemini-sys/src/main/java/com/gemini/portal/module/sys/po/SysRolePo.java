package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 角色表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_role")
public class SysRolePo extends BasePo {

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

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 修改人id
     */
    private Long modifyUserId;

    /**
     * 修改人名称
     */
    private String modifyUserName;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private Timestamp modifyTime;

    @TableField(exist = false)
    private List<SysRoleMenuPo> detailList;
}
