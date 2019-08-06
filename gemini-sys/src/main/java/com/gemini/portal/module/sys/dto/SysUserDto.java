package com.gemini.portal.module.sys.dto;

import com.gemini.boot.framework.mybatis.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author wenge.cai
 */
@Data
public class SysUserDto extends BaseDto {

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
