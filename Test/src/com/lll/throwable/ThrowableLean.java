package com.lll.throwable;

/**
 * Version 1.0
 * Created by lll on 2019-12-18.
 * Description
 * <pre>
 *   java异常
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ThrowableLean {


  public void testException() {
    try {
      test(2);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("我是catch before");
      test(3);
      System.out.println("我是catch after");
    } finally {
      System.out.println("我是finally");
//      return; //有return ，
    }
  }

  public void test(int num) {
    if (num == 2 || num == 3) {
      System.out.println("我是要抛异常了啦"+num);
      throw new IllegalArgumentException("参数异常" + num);
    }
  }

  public static void main(String[] args) {
    ThrowableLean lean = new ThrowableLean();
    lean.testException();
  }

}
