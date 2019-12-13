package com.lll.concurent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liaoxueyan on 17/5/8.
 * <p>
 * CountDownLatch:一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 主要方法
 * countDown方法，当前线程调用此方法，则计数减一
 * awaint方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
 */
public class CountDownLatchTest {
  static int num;

  public static void main(String[] args) throws Exception {
    System.out.println("Hello World!");
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch doneSignal = new CountDownLatch(5);
    for (int i = 0; i < 50; i++) {
      Thread thread = new Thread(new Worker(startSignal, doneSignal));
      thread.setName("test_" + i);
      thread.start();
    }
    doSomeThings();
    System.out.println("-----after--call1----");
    startSignal.countDown();
    doSomeThings();
    System.out.println("-----after--call2----");
    doneSignal.await();//注意不是wait()，当前线程等待，执行其他线程
    doSomeThings();
    System.out.println("-----after--call3----");
  }

  public static void doSomeThings() {
    System.out.println("----" + Thread.currentThread().getName() + "-------doSomeThings--" + num);
  }

  static class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
      this.startSignal = startSignal;
      this.doneSignal = doneSignal;
    }

    public void run() {
      try {
        startSignal.await();//线程等待
        doWork();
        doneSignal.countDown();//完成一个
        Thread.sleep(new Random().nextInt(99) + 1);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      } // return;
    }

    void doWork() {
      //业务逻辑
      System.out.println("----" + Thread.currentThread().getName() + "---doWork------" + (num++));
    }
  }
}
