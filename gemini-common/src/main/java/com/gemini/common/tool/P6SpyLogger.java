package com.gemini.common.tool;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参考https://blog.csdn.net/kisscatforever/article/details/78784254
 * 在开发的过程中，总希望方法执行完了可以看到完整是sql语句，从而判断执行的是否正确，所以就希望有一个可以打印sql语句的插件。
 * p6spy就是一款针对数据库访问操作的动态监控框架，他可以和数据库无缝截取和操纵，而不必对现有应该用程序的代码做任何修改。
 * 通过p6spy可以直接打印数据库执行的语句，下面向大家介绍一下p6spy。
 * @author 小明不读书
 * @date 2019-02-21
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public P6SpyLogger() {
    }

//    @Override
//    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String s4) {
//        return !"".equals(sql.trim())?this.format.format(new Date()) + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "\n " + sql + ";":"";
//    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed,
                                String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? "| took "
                + elapsed + "ms | " + category + " | connection " + connectionId + "\n "
                + P6Util.singleLine(sql) + ";" : "";
    }
}