package com.lll.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Version 1.0
 * Created by lll on 27/03/2018.
 * Description
 * <pre>
 *     LinkedHashMapLearn
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LinkedHashMapLearn {

  LinkedHashMap<Integer, String> linked = new LinkedHashMap<Integer, String>();


  LinkedHashMapLearn() {
    for(int i = 0; i < 10; i++) {
      linked.put(i, "String" + i);
    }
  }

  public String addDataTest(int num, String testStr) {
    return linked.put(num, testStr);
  }


  public void testMap() {

    /**
     * hashMap 在1.8时底层采用红黑树，所以他的key 是有序的
     */
    HashMap<Integer, Integer> maps = new HashMap<>();
    maps.put(3,11);
    maps.put(1,12);
    maps.put(5,13);
    maps.put(2,14);

    System.out.println("============map==========");
    for (Map.Entry e : maps.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue() + "   ");
    }

    System.out.println("============linked map==========");
    /**
     *
     * true: 表示按照时间顺序访问存储数据； false 表示插入顺序排列
     */
    HashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>(10, 0.75f, false);
    linkedHashMap.put(3,11);
    linkedHashMap.put(1,12);
    linkedHashMap.put(5,13);
    linkedHashMap.put(2,14);
    for (Map.Entry e : linkedHashMap.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue() + "   ");
    }
    linkedHashMap.put(3, 15);
    linkedHashMap.get(5);
    System.out.println("============linked map used==========");
    for (Map.Entry e : linkedHashMap.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue() + "   ");
    }
  }


  public static void main(String[] args) {
    LinkedHashMapLearn learn = new LinkedHashMapLearn();

    learn.testMap();

//    String testValue = learn.addDataTest(3, "test3");
//    System.out.print("the value ===" + testValue);
  }
}
