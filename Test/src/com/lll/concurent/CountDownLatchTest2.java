package com.lll.concurent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by liaoxueyan on 17/5/8.
 * <p>
 * CountDownLatch:一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 主要方法
 * countDown方法，当前线程调用此方法，则计数减一
 * await方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
 */
public class CountDownLatchTest2 {

    //等待
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    private final Object mWritingToDiskLock = new Object();

    final Runnable awaitCommit = new Runnable() {
        public void run() {
            try {
                System.out.println("do CountDownLatch await");
                mCountDownLatch.await();
            } catch (InterruptedException ignored) {
                ignored.printStackTrace();
            }
        }
    };

    Runnable postWriteRunnable = new Runnable() {
        public void run() {
            awaitCommit.run();
        }
    };

    final Runnable writeToDiskRunnable = new Runnable() {
        public void run() {
            synchronized (mWritingToDiskLock) {
                writeToFile(); //写文件
            }
            synchronized (CountDownLatchTest2.this) {
                System.out.println("do synchronized business");
            }
            postWriteRunnable.run(); //阻塞等着，等文件写完后，通知
        }
    };

    private void writeToFile() {
        try {
            System.out.println("i am saving file now");
            Thread.sleep(3000);
            mCountDownLatch.countDown(); //通知下一步继续执行
        } catch (Exception e) {

        }
    }

    private void apply() {
        System.out.println("before runnable");
        Executors.newSingleThreadExecutor().execute(writeToDiskRunnable);
        System.out.println("after runnable");

    }


    public static void main(String[] args) throws Exception {
        CountDownLatchTest2 test2 = new CountDownLatchTest2();
        test2.apply();
    }

}
