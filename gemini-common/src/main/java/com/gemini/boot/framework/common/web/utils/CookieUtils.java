package com.gemini.boot.framework.common.web.utils;
//
////import org.apache.commons.lang3.StringUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Cookie工具类
// *
// * @author 小明不读书
// */
//public class CookieUtils {
//    /**
//     * 根据部署路径，将cookie保存在根目录。
//     *
//     * @param request
//     * @param response
//     * @param name cookie名称
//     * @param value cookie值
//     * @return
//     */
////    public static Cookie addCookie(
////            HttpServletRequest request,
////            HttpServletResponse response,
////            String name,
////            String value) {
////        Cookie cookie = new Cookie(name, value);
////        String ctx = request.getContextPath();
////        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
////        response.addCookie(cookie);
////        return cookie;
////    }
//
//    /**
//     * 获得cookie
//     *
//     * @param request
//     * @param name    cookie名称
//     * @return
//     */
//    public static Cookie getCookie(HttpServletRequest request, String name) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie c : cookies) {
//                if (c.getName().equals(name)) {
//                    return c;
//                }
//            }
//        }
//        return null;
//    }
//}
