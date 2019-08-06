package com.gemini.portal.module.sys.vo;

import com.gemini.boot.framework.mybatis.vo.BaseVo;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author wenge.cai
 */
@Data
public class SysUserVo extends BaseVo {

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

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
}