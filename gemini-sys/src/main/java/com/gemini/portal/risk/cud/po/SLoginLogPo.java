package com.gemini.portal.risk.cud.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uepay.corebusiness.risk.base.po.BasePo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 登陆日志表
 * @author wenge.cai
 */
@Data
@TableName("f_sys_login_log")
public class SLoginLogPo extends BasePo {

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
