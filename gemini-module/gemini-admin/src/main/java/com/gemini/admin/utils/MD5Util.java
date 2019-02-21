package com.gemini.admin.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    public static String encryption(String source, String salt) {
        Object salt1 = ByteSource.Util.bytes(salt);
        String result = String.valueOf(new SimpleHash("MD5", source, salt1, 1024));
        return result;
    }
}
