package com.gemini.portal.common.config;

import com.gemini.boot.framework.mybatis.entity.CommonStatus;
import com.gemini.portal.MD5Util;
import com.gemini.portal.module.sys.po.SysLoginLogPo;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.service.SysLoginLogService;
import com.gemini.portal.module.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;


/**
 * 自定义shiro
 *
 * @author 小明不读书
 * @date 2017-12-11
 */
public class ShiroRealm extends AuthorizingRealm {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1、
     * 在自定义realm中注入service会导致该service的@cacheable注解无效
     *
     * @Lazy解决同时使用缓存cache和shiro注解无效问题 2、
     * 如果改了User相关路径，则要清除redis缓存，比如原来User路径为 com.gemini.base.User.java
     * 后来改为com.gemini.test.User,但是缓存里面的是core，这个时候就会报错，找不到core，
     * 所以要清除缓存，重新将新路径的test存进去
     */
    @Lazy
    @Autowired
    SysUserService userService;
    @Autowired
    SysLoginLogService loginLogService;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SysLoginLogPo loginLog = new SysLoginLogPo();

        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo();
        try {
            // 1.把AuthenticationToken转换为UsernamePasswordToken
            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            // 2.从UsernamePasswordToken获取account
            String account = upToken.getUsername();
            // 3.调用数据库方法,从数据库中查询account的用户
            SysUserPo user = userService.getByAccount(account);
            loginLog.setUserAccount(account);
            if (user != null) {
                loginLog.setUserName(user.getName());
            }
            // 4.若用户不存在,则抛出UnknownAccountException异常
            if (user == null) {
                throw new UnknownAccountException("账号不存在.");
            }
            // 5.根据用户密码,决定是否抛出其他的AuthenticationException异常
            if (!user.getPassword().equals(MD5Util.encryption(String.valueOf(upToken.getPassword()), user.getAccount()))) {
                throw new AuthenticationException("用户账号或者密码错误.");
            }
            if (user.getStateCode().equals(CommonStatus.STATUS_NULLITY)) {
                throw new LockedAccountException("用户已禁用.");
            }
            loginLog.setId(123L);
            loginLog.setLoginStateId(123213L);
            loginLog.setLoginStateCode(String.valueOf(CommonStatus.STATUS_VALIDITY));
            loginLog.setLoginStateName("启用");
            loginLog.setMessage("登陆成功");

            // 盐值
            ByteSource salt = ByteSource.Util.bytes(account);

            // 6.根据用户构建SimpleAuthenticationInfo
            saInfo = new SimpleAuthenticationInfo(user, user.getPassword(), salt, this.getName());

        } catch (Exception e) {
            //java.lang.ClassCastException: com.gemini.core.module.sys.model.User cannot be cast to com.gemini.base.sys.model.User
            //请看上面第2点注释
            loginLog.setLoginStateId(123213L);
            loginLog.setLoginStateCode(String.valueOf(CommonStatus.STATUS_NULLITY));
            loginLog.setLoginStateName("禁用");
            loginLog.setMessage(e.getMessage());
            logger.error(e.getMessage());
            throw e;
        } finally {
            loginLogService.insert(loginLog);
        }
        return saInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 从 PrincipalCollection 中来获取登录用户的信息
        Object account = principals.getPrimaryPrincipal();

        // 2. 创建 SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 3. 根据account查询用户角色
        Set<String> roles = userService.getRoleById(account.toString());
        info.setRoles(roles);

        // 4. 根据account查询用户权限
        Set<String> permissions = userService.getPermissionsById(account.toString());
        info.setStringPermissions(permissions);

        return info;
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        Object source = "123456";
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        // 随机
        System.out.println(salt2);
        Object salt = ByteSource.Util.bytes("admin");
        System.out.println(salt);
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        // 038bdaf98f2037b31f1e75b5b4c9b26e
        System.out.println(result);
    }

}
