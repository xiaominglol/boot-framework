package com.gemini.admin.module.sys.utils;

import com.gemini.admin.module.sys.model.User;
import org.apache.shiro.SecurityUtils;

/**
 * 用户工具类
 * 在静态方法调用Spring注入的非静态方法
 * 参考网址：https://blog.csdn.net/xinpz/article/details/81061515
 *
 * @author 小明不读书
 * @date 2018-02-09
 */
public class UserUtils {
    /**
     * 获取当前用户
     *
     * @return user
     */
    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
