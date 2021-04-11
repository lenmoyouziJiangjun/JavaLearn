package com.lll.concurent;

import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Version 1.0
 * Created by lll on 12/4/17.
 * Description
 * 1、Synchronized和锁的区别
 * 2、对象锁和类锁的区别？同一个类不同方法都有synchronized锁，是否互斥
 * 3、Synchronized的优化
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class SynchronizedLearn {

  public int i = 0;
  public static int j = 10;


  /**
   * 对象方法synchronized的锁为this;即对象锁，同一个锁是互斥的
   */
  public synchronized void addI() {
    i += 1;
  }

  public synchronized void subtractionI() {
    i -= 1;
  }

  /**
   * 类方法synchronized的锁为class,即类锁，
   */
  public synchronized static void addJ() {
    j += 1;
  }

  public synchronized static void subtractionJ() {
    j -= 1;
  }


  public static void main(String[] args) {
    SynchronizedLearn learn = new SynchronizedLearn();
    SynchronizedLearn learn2 = new SynchronizedLearn();
    new Thread("加线程") {
      @Override
      public void run() {
        for (int m = 0; m < 100; m++) {
          learn.addI();
          learn.addJ();
          SynchronizedLearn.addJ();
          System.out.print(Thread.currentThread().getName() + "i===" + learn.i + "-------static.j=====" + SynchronizedLearn.j + "--------obj.j==" + learn.j + "\n");
        }

      }
    }.start();

    new Thread("减线程") {
      @Override
      public void run() {
        for (int m = 0; m < 100; m++) {
          learn.subtractionI();
          learn.subtractionJ();
          SynchronizedLearn.subtractionJ();
          System.out.print(Thread.currentThread().getName() + "i===" + learn.i + "-------static.j=====" + SynchronizedLearn.j + "--------obj.j==" + learn.j + "\n");
        }
      }
    }.start();
    try {
      Thread.currentThread().sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.print("i===" + learn.i + "-------j=====" + SynchronizedLearn.j);
  }


}
