package com.lll.java8;

/**
 * Version 1.0
 * Created by lll on 10/26/17.
 * Description
 * java 8 default Method
 * copyright generalray4239@gmail.com
 */
public class DefaultMethodLearn {


  /**
   *
   */
  interface DefaultInteface {

    default void syOut() {
      System.out.println("我是默认方法");
    }
  }
}
