package com.gemini.admin.common.aop;

import com.gemini.admin.common.annotation.SysLog;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.OptLog;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.ExcpLogService;
import com.gemini.admin.module.sys.service.OptLogService;
import com.gemini.admin.module.sys.utils.UserUtils;
import com.gemini.admin.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 操作日志记录
 *
 * @author 小明不读书
 * @date 2019-02-25
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private OptLogService optLogService;
    @Autowired
    private ExcpLogService excpLogService;
    OptLog optLog = new OptLog();
    ExcpLog excpLog = new ExcpLog();

    @Pointcut("@annotation(com.gemini.admin.common.annotation.SysLog)")
    public void sysLog() {

    }

    @Around("sysLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();

        //保存日志
        saveOptLog(point, beginTime);

        return result;
    }

    /**
     * 方法退出时执行
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        optLog.setResult(ret == null ? "" : ret.toString());
        //保存操作日志
        optLogService.save(optLog);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing(throwing = "e", value = "sysLog()")
    public void afterThrowing(JoinPoint jp, Throwable e) {
        //保存日志
        saveExcpLog(jp);
        excpLog.setResult(e.toString());
        excpLogService.save(excpLog);
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("sysLog()")
    public void after(JoinPoint jp) {
    }

    private void saveOptLog(ProceedingJoinPoint joinPoint, long beginTime) {
        //用户名
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            optLog.setUserAccount(user.getAccount());
            optLog.setUserName(user.getName());
        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            //注解上的操作描述
            optLog.setDescription(sysLog.value());


            Integer type = getType(request, sysLog);
            optLog.setType(type);
        }

        //请求地址
        optLog.setUrl(request.getRequestURI());

        //请求方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        optLog.setMethod(className + "." + methodName + "()");

        //请求参数
        optLog.setParams(Arrays.toString(joinPoint.getArgs()));

        //设置IP地址
        optLog.setIp(IPUtils.getIpAddr(request));

        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        optLog.setTime(time);

        optLog.setOptTime(new Date());
    }

    private void saveExcpLog(JoinPoint joinPoint) {


        //用户名
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            excpLog.setUserAccount(user.getAccount());
            excpLog.setUserName(user.getName());
        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            //注解上的操作描述
            excpLog.setDescription(sysLog.value());

            Integer type = getType(request, sysLog);
            excpLog.setType(type);
        }

        //请求地址
        excpLog.setUrl(request.getRequestURI());

        //请求方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        excpLog.setMethod(className + "." + methodName + "()");

        //请求参数
        excpLog.setParams(Arrays.toString(joinPoint.getArgs()));

        //设置IP地址
        excpLog.setIp(IPUtils.getIpAddr(request));

        excpLog.setExcpTime(new Date());
    }

    private Integer getType(HttpServletRequest request, SysLog sysLog) {
        Integer type = null;
        if ("GET".equals(request.getMethod())) {
            type = 1;
        } else if ("POST".equals(request.getMethod())) {
            if (sysLog.value().equals("用户登陆")) {
                type = 5;
            } else {
                type = 2;
            }
        } else if ("PUT".equals(request.getMethod())) {
            type = 3;
        } else if ("DELETE".equals(request.getMethod())) {
            type = 4;
        }
        return type;
    }
}
