package com.gemini.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalApplication.class)
public class CallableTest2 {

    private static final Logger LOG = LoggerFactory.getLogger(CallableTest2.class);


    @Test
    public void testSelect() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建三个有返回值的任务
        CallableTest callableTest1 = new CallableTest("main-1");
        CallableTest callableTest2 = new CallableTest("main-2");
        CallableTest callableTest3 = new CallableTest("main-3");


        Future future1 = pool.submit(callableTest1);
        Future future2 = pool.submit(callableTest2);
        Future future3 = pool.submit(callableTest3);

        try {
            future1.get();
            future2.get();
            future3.get();
//            LOG.info(future1.get().toString());
//            LOG.info(future2.get().toString());
//            LOG.info(future3.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        long endTime = System.currentTimeMillis();
        LOG.info("【main】【" + (endTime - startTime) + "】");
    }


    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Test
    public void testSelect2222() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建三个有返回值的任务
        CallableTest callableTest1 = new CallableTest("main-1");
        CallableTest callableTest2 = new CallableTest("main-2");
        CallableTest callableTest3 = new CallableTest("main-3");


        Future future1 = threadPoolTaskExecutor.submit(callableTest1);
        Future future2 = threadPoolTaskExecutor.submit(callableTest2);
        Future future3 = threadPoolTaskExecutor.submit(callableTest3);

        try {
            future1.get();
            future2.get();
            future3.get();
//            LOG.info(future1.get().toString());
//            LOG.info(future2.get().toString());
//            LOG.info(future3.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        long endTime = System.currentTimeMillis();
        LOG.info("【main】【" + (endTime - startTime) + "】");
    }


    @Test
    public void testSelect1() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建三个有返回值的任务
        CallableTest callableTest1 = new CallableTest("main-1");
        CallableTest callableTest2 = new CallableTest("main-2");
        CallableTest callableTest3 = new CallableTest("main-3");


//        Future future1 = pool.submit(callableTest1) ;
//        Future future2 = pool.submit(callableTest2) ;
//        Future future3 = pool.submit(callableTest3) ;

        try {
//            LOG.info(future1.get().toString());
//            LOG.info(future2.get().toString());
//            LOG.info(future3.get().toString());
            callableTest1.call();
            callableTest2.call();
            callableTest3.call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        long endTime = System.currentTimeMillis();
        LOG.info("【main】【" + (endTime - startTime) + "】");
    }


}