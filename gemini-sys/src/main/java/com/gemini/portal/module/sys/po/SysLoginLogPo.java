package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * 登陆日志表
 *
 * @author wenge.cai
 */
@Data
@TableName("f_sys_login_log")
public class SysLoginLogPo extends BasePo {

    /**
     * 主鍵ID
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
     * 登陆状态id
     */
    private Long loginStateId;

    /**
     * 登陆状态编码
     */
    private String loginStateCode;

    /**
     * 登陆状态名称
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
