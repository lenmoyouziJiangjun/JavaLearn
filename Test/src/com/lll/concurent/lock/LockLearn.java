package com.lll.concurent.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Version 1.0
 * Created by lll on 2019-12-18.
 * Description
 * <pre>
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LockLearn {

  //重入锁
  ReentrantLock lock =  new ReentrantLock();

  //读写锁
  ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();


  public static void main() {

  }

}
