package com.lll.concurent;

import java.util.concurrent.*;

/**
 * Version 1.0
 * Created by lll on 16/01/2018.
 * Description
 * <pre>
 *     线程池学习
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class TheadPoolLearn {

    ExecutorService mSingleService;
    ScheduledExecutorService mSchedykedService;
    ThreadPoolExecutor mThreadPoolExecutor;


    public TheadPoolLearn() {
        mSingleService = Executors.newSingleThreadExecutor(new MyThreadFactory());

        mSchedykedService = Executors.newScheduledThreadPool(1, new MyThreadFactory());

        mThreadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(4));

    }

    public class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "--------" + r.toString());
        }
    }


    public class SingleTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName() + "---------single ---task";
        }
    }

    public class ScheduledTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName() + "---------scheduled ---task";
        }
    }

    private static class MyRunn implements Runnable, Future {

        @Override
        public void run() {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(10000));
                System.out.println(Thread.currentThread().getName() + "---------MyRunn");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public Object get() throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }


    public static void main(String args[]) {
        TheadPoolLearn learn = new TheadPoolLearn();
        for (int i = 0; i < 1000; i++) {
            learn.mThreadPoolExecutor.execute(new MyRunn());
        }
    }
}
