package com.gemini.admin.module.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.module.sys.utils.UserUtils;
import com.gemini.admin.utils.IPUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
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
public class ExcpLog implements Serializable {

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
     * 操作类型（1=查询，2=添加，3=修改，4=删除,5=用户登陆）
     */
    private Integer type;

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
    private Object params;

    /**
     * 返回结果
     */
    private Object result;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作时间
     */
    private Date excpTime;


    public static ExcpLog saveExcpLog(String methodName, Map<String, Object> params, String errorMsg) {
        ExcpLog excpLog = new ExcpLog();
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            excpLog.setUserAccount(user.getAccount());
            excpLog.setUserName(user.getName());
        } else {
            excpLog.setUserAccount(params.get("account").toString());
        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //请求地址
        excpLog.setUrl(request.getRequestURI());

        //请求方法名称
        excpLog.setMethod(methodName);

        //请求参数
        //excpLog.setParams(Arrays.toString(joinPoint.getArgs()));
//        excpLog.setParams(params == null ? "" : params.toString());
        excpLog.setParams("");
        //设置IP地址
        excpLog.setIp(IPUtils.getIpAddr(request));


        excpLog.setResult(errorMsg);
        excpLog.setExcpTime(new Date());
        return excpLog;
    }

    public static ExcpLog saveExcpLog(String methodName, String errorMsg, Logger logger) {
        ExcpLog excpLog = new ExcpLog();
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            excpLog.setUserAccount(user.getAccount());
            excpLog.setUserName(user.getName());
        } else {
            //excpLog.setUserAccount(params.get("account").toString());
        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //请求地址
        excpLog.setUrl(request.getRequestURI());

        //请求方法名称
        excpLog.setMethod(methodName);

        //请求参数
        Enumeration enu = request.getParameterNames();
        Map<String, Object> map = new HashMap<>();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
        excpLog.setParams(map == null ? "" : map.toString());
        //设置IP地址
        excpLog.setIp(IPUtils.getIpAddr(request));


        excpLog.setResult(errorMsg);
        logger.error(errorMsg);
        excpLog.setExcpTime(new Date());
        return excpLog;
    }
}