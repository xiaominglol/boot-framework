package com.gemini.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    /**
     * 初始化密码
     */
    public static final String INIT_PASSWORD = "123456";

    public static String encryption(String source, String salt) {
        Object salt1 = ByteSource.Util.bytes(salt);
        String result = String.valueOf(new SimpleHash("MD5", source, salt1, 1024));
        return result;
    }
}
