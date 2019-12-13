package com.lll.obj;

/**
 * Version 1.0
 * Created by lll on 17/9/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObjTest {

  public static void main(String[] args) {
    P p1 = new P();
    P p2 = p1;//引用类型，指向同一份内存地址。
    p1.name = "p1";
    p1.age = 11;
    System.out.println(p2.name + "---------" + p2.age);
  }


  public static class P {
    String name;
    int age;
  }


}
