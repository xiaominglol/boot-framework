package com.gemini.portal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
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

        //创建三个有返回值的任务
        List<CompletableFuture<String>> futureList = new LinkedList<>();

        List<Future<String>> futureList2 = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
            CallableTest callableTest1 = new CallableTest("main-" + i);
            Future future1 = threadPoolTaskExecutor.submit(callableTest1);
            futureList2.add(future1);
            CompletableFuture<String> future = new CompletableFuture<>();
            futureList.add(future);
        }

        for (Future<String> complete : futureList2) {
            complete.get();
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        String ret = "";
//        try {
//            all.get(2, TimeUnit.SECONDS);
//            for (CompletableFuture<String> complete : futureList) {
//                ret = complete.get();
//            }
//        } catch (TimeoutException e) {
//            log.warn("2秒截取成功!默认返回成功", e);
//        }

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