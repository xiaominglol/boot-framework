package com.gemini.admin.sys.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.sys.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志    t_sys_excp_log
 *
 * @author 小明不读书
 * @date 2018-10-17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_excp_log")
public class ExcpLog implements Serializable{

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
     * 请求地址
     */
    private String url;

    /**
     * 请求参数
     */
    private Object requestParam;

    /**
     * 返回结果
     */
    private String responseResult;

    /**
     * 操作时间
     */
    private Date excpTime = new Date();


    public static ExcpLog addResponseResult(String url, Map<String, Object> requestParam, String errorMsg) {
        ExcpLog excpLog = new ExcpLog();
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            excpLog.setUserAccount(user.getAccount());
            excpLog.setUserName(user.getName());
        } else {
            excpLog.setUserAccount(requestParam.get("account").toString());
        }
        excpLog.setUrl(url);
        excpLog.setRequestParam(requestParam == null?"":requestParam.toString());
        excpLog.setResponseResult(errorMsg);
        return excpLog;
    }
}