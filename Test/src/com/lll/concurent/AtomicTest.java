package com.lll.concurent;/**
 * Created by liaoxueyan on 17/5/12.
 */

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Version 1.0
 * Created by lll on 17/5/12.
 * Description 原子操作测试
 * <pre>
 *     1、java 提供的原子操作类：AtomicBoolean | AtomicInteger 等只能对单个共享变量有效，
 *     2、要想多个对象实现原子操作 使用 AtomicReference<> ,通过它实现对象的原子操作
 *     3、CAS 操作的三个问题
 *        ABA: 数据中途修改过。API 提供AtomicStampedReference解析
 *        循环时间长开销大 ：
 *        只能保证一个共享变量： api 提供AtomicReference 解决 。 参考google  guava 提供的localCache工具类 和 rxJava 的 FutureObderver类
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class AtomicTest {
  private static AtomicBoolean atomicBoolean = new AtomicBoolean();

  /**
   * 测试atomicBoolean
   * atomicBoolean 的原理：里面有个volatile修饰的成员变量  value。
   */
  public static boolean testAtomicBoolean() {
    return atomicBoolean.compareAndSet(false, true);
  }

  public static boolean getBoolean() {
    return atomicBoolean.get();
  }


  public static void main(String args[]) {
    System.out.println("atomicBoolean==== " + getBoolean() + "--------");
    System.out.println("atomicBoolean==== " + testAtomicBoolean() + "--------");
    System.out.println("atomicBoolean==== " + atomicBoolean.getAndSet(false) + "--------");
    System.out.println("atomicBoolean==== " + atomicBoolean.weakCompareAndSet(true, false) + "--------");
  }
}
