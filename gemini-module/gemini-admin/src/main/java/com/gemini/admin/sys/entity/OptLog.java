package com.gemini.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志    t_sys_opt_log
 *
 * @author 小明不读书
 * @date 2018-10-17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_opt_log")
public class OptLog implements Serializable{

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
     * 操作类型（1=查询，2=添加，3=修改，4=删除）
     */
    private Integer type;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回结果
     */
    private String responseResult;

    /**
     * 操作时间
     */
    private Date optTime = new Date();

}