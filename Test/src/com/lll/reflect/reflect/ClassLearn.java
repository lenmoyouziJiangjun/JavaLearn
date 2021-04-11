package com.lll.reflect.reflect;

/**
 * Version 1.0
 * Created by lll on 2019-12-18.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ClassLearn {


  public ClassLearn() {
    System.out.println("------init ---------");
  }

  public static void main(String[] args) {
    try {
      Class clazz = Class.forName("com.lll.reflect.reflect.ClassLearn");
      Class clazz2 = Class.forName("com.lll.reflect.reflect.ClassLearn");

      System.out.println("the CanonicalName === "+clazz.getCanonicalName());
      clazz.newInstance();
      clazz2.newInstance();
    }catch (Exception e) {
      e.printStackTrace();
    }

  }
}
