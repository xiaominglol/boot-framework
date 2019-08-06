package com.gemini.portal.module.sys.dto;

import com.gemini.boot.framework.mybatis.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * 登陆日志表
 *
 * @author wenge.cai
 */
@Data
public class SysLoginLogDto extends BaseDto {

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private String userName;

    /**
     *
     */
    private Long loginStateId;

    /**
     * 登陆状态（0：失败，1：成功）
     */
    private String loginStateCode;

    /**
     *
     */
    private String loginStateName;

    /**
     * 登陆消息
     */
    private String message;

    /**
     * 登陆时间
     */
    private Date createDatetime;
}
