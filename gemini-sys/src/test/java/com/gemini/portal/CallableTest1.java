package com.gemini.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class CallableTest1 implements Callable {

    private static final Logger LOG = LoggerFactory.getLogger(CallableTest1.class);

    private String threadName;

    public CallableTest1(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Object call() throws Exception {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        LOG.info("【" + threadName + "】【" + (endTime - startTime) + "】");
        return threadName + "返回的信息";
    }


}