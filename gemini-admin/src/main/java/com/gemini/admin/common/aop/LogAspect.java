package com.gemini.admin.common.aop;

import com.gemini.admin.module.sys.model.OptLog;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.OptLogService;
import com.gemini.admin.module.sys.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;


/**
 * 日志切面
 *
 * @author gemini
 * @date 2018-10-17
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    OptLogService optLogService;
    OptLog optLog = new OptLog();

    /**
     * 1) execution(): 表达式主体
     * 2) 第一个public *号：表示返回类型， *号表示所有的类型。
     * 3) 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.gemini.base包、子孙包下所有类的方法。
     * 4) 第二个*号：表示类名，*号表示所有的类。
     * 5) *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(public * com.gemini.admin.module.*.controller.*.*(..))")
    public void log() {
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     *
     * @param pjp
     * @return
     */
    @Around("log()")
    public Object arround(ProceedingJoinPoint pjp) {
//        System.out.println("方法环绕start.....");
        try {
            Object o = pjp.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 前置
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("log()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        System.out.println("请求地址URL : " + request.getRequestURL().toString());
//        System.out.println("请求方式HTTP_METHOD : " + request.getMethod());
//        System.out.println("请求类方法:"+joinPoint.getSignature());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("请求类方法参数ARGS : " + Arrays.toString(joinPoint.getArgs()));
        User user = UserUtils.getCurrentUser();
        optLog.setUserAccount(user.getAccount());
        optLog.setUserName(user.getName());
        optLog.setUrl(request.getRequestURI());
        optLog.setRequestParam(Arrays.toString(joinPoint.getArgs()));
        Integer type = null;
        if ("GET".equals(request.getMethod())) {
            type = 1;
        } else if ("POST".equals(request.getMethod())) {
            type = 2;
        } else if ("PUT".equals(request.getMethod())) {
            type = 3;
        } else if ("DELETE".equals(request.getMethod())) {
            type = 4;
        }
        optLog.setType(type);
        optLog.setOptTime(new Date());
        //OptLog optLog = new OptLog(null, UserUtils.getCurrentUser().getAccount(), UserUtils.getCurrentUser().getName(), request.getMethod() == "GET" ? 1 : 2, request.getRequestURL().toString(), Arrays.toString(joinPoint.getArgs()), "123", new Date());
        //optLogService.save(optLog);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("log()")
    public void throwss(JoinPoint jp) {
//        System.out.println("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("log()")
    public void after(JoinPoint jp) {
//        System.out.println("方法最后执行.....");
        optLogService.save(optLog);
        //System.out.println(optLog.toString());
    }

    /**
     * 方法退出时执行
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret.toString());
        optLog.setResponseResult(ret == null ? "" : ret.toString());
    }
}  