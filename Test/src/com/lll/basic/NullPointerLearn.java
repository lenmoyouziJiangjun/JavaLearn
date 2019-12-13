package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 2019/1/28.
 * Description
 * <pre>
 *     空指针判断
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class NullPointerLearn {


  public static void test() {
    String a = null;
    String b = "aaa";
    try {
      if (b.equalsIgnoreCase(a)) {   //字符串放前面，null 放后面，就可以正常执行
        System.out.println(" b equals a");
      } else {
        System.out.println(" b not equals a");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      if (a.equalsIgnoreCase(b)) {//这个是调用null的方法，所以报错
        System.out.println(" a equals b");
      } else {
        System.out.println(" a not equals b");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    test();
  }

}
