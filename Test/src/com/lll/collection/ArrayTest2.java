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


  public static void testArraySubList() {
    List<String> names = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      names.add("haha----" + i);
    }

    printList(names);

    List subName = names.subList(9, 10);
    System.out.println("--------------------------");
    printList(subName);
  }

  private static void printList(List<String> list) {
    for (String str : list) {
      System.out.print(str + "    ");
    }
  }


  public static void main(String[] args) {
    testArraySubList();
  }


}
