package com.gemini.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class CallableTest implements Callable {

    private static final Logger LOG = LoggerFactory.getLogger(CallableTest.class);

    private String threadName;


    public CallableTest(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Object call() throws Exception {


        long startTime = System.currentTimeMillis();
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建三个有返回值的任务
        CallableTest1 callableTest1 = new CallableTest1(threadName + "-1");
        CallableTest1 callableTest2 = new CallableTest1(threadName + "-2");
        CallableTest1 callableTest3 = new CallableTest1(threadName + "-3");


        Future future1 = pool.submit(callableTest1);
        Future future2 = pool.submit(callableTest2);
        Future future3 = pool.submit(callableTest3);

        try {
            future1.get();
            future2.get();
            future3.get();
            LOG.info(future1.get().toString());
            LOG.info(future2.get().toString());
            LOG.info(future3.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();

        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();

        LOG.info("【" + threadName + "】【" + (endTime - startTime) + "】");


        return threadName + "返回的信息";
    }


}