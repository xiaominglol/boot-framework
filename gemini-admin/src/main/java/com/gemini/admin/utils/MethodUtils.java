package com.gemini.admin.utils;

public class MethodUtils {
    public static String getCurrentMethod(){
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }
}
