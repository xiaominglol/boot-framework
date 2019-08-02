package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志表
 * @author wenge.cai
 */
@Data
@TableName("f_sys_opt_log")
public class SysOptLogPo extends BasePo {

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
     * 
     */
    private Long optTypeId;

    /**
     * 操作类型（1=查询，2=添加，3=修改，4=删除，5=用户登陆）
     */
    private String optTypeCode;

    /**
     * 
     */
    private String optTypeName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法名称
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回结果
     */
    private String result;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 耗时（ms）
     */
    private Long time;

    /**
     * 操作时间
     */
    private Date createDatetime;
}
