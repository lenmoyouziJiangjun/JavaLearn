package com.lll.concurent;

/**
 * Version 1.0
 * Created by lll on 2019-12-15.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SynchronizedTest {


  // 关键字在实例方法上，锁为当前实例
  public synchronized void method1() {
    // code
  }

  // 关键字在代码块上，锁为括号里面的对象
  public void method2() {
    Object o = new Object();
    synchronized (o) {
      // code
    }
  }

  // 关键字在类方法，锁为类.class
  public synchronized static void method3() {

  }
}
