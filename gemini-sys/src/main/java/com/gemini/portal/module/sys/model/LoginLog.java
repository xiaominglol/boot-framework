package com.gemini.portal.module.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 登陆日志    t_sys_login_log
 *
 * @author 小明不读书
 * @date 2018-10-17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_login_log")
public class LoginLog implements Serializable {

    /**
     * 自增主键：菜单ID
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登陆状态（0：失败，1：成功）
     */
    private Integer status;

    /**
     * 登陆消息
     */
    private String message = "登陆成功.";

    /**
     * 登陆时间
     */
    private Date loginTime = new Date();

}