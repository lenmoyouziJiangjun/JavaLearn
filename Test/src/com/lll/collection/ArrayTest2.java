package com.lll.collection;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Version 1.0
 * Created by lll on 11/12/2017.
 * Description
 * <p>
 * 集合学习
 * <p>
 * copyright generalray4239@gmail.com
 */
public class ArrayTest2 {


  public static void remove11(List<String> list, String target) {
    for (int i = 0; i < list.size(); i++) {
      String item = list.get(i);
      if (target.contains(item)) {
        list.remove(item);
      }
    }
  }

  public static void remove13(List<String> list, String target) {
    for (String item : list) {
      if (target.equals(item)) {
        list.remove(item);
      }
    }
  }


  public static void main(String[] args) {
    System.out.println("value ==" + ((16 - 1) & 14));
    System.out.println("value ==" + ((16 - 1) & 15));
    System.out.println("value ==" + ((16 - 1) & 16));
    System.out.println("value2 == " + ((16 - 1) & 17));
    System.out.println("value2 == " + ((16 - 1) & 18));
    System.out.println("value2 == " + ((16 - 1) & 19));

    int n =  - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;

    System.out.println("value3 == " +n);
  }


}
