package com.lll.concurent;

/**
 * Version 1.0
 * Created by lll on 2019-12-15.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SingletonTest {

  /**
   * 使用 volatile 防止重排序
   */
  public volatile static SingletonTest sInstance;

  public static SingletonTest getInstance() {
    if (sInstance == null) {
      synchronized (SingletonTest.class) {

        if (sInstance == null) {
           sInstance = new SingletonTest();
        }
      }
    }
    return sInstance;
  }

  private SingletonTest() {}

}
