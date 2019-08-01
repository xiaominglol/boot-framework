package com.gemini.portal.risk.cud.sUser;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uepay.corebusiness.risk.base.po.BasePo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户表
 * @author wenge.cai
 */
@Data
@TableName("f_sys_user")
public class SUserPo extends BasePo {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 组织架构id
     */
    private Long orgId;

    /**
     * 组织架构名称
     */
    private String orgName;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private Date createDatetime;

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码（Enable：启用，Disable：禁用）
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 修改人id
     */
    private Long modifyId;

    /**
     * 修改人名称
     */
    private String modifyName;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private String modifyTime;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 组织架构id
     */
    private Long orgId;

    /**
     * 组织架构名称
     */
    private String orgName;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private Date createDatetime;

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码（Enable：启用，Disable：禁用）
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 修改人id
     */
    private Long modifyId;

    /**
     * 修改人名称
     */
    private String modifyName;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private String modifyTime;
}
