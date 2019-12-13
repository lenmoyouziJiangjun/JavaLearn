package com.lll.basic;

import java.util.StringTokenizer;

/**
 * Version 1.0
 * Created by lll on 17/8/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StringTest {

  public static void main(String[] args) {
    test();
    testStringTokenizer();
    testString2();
  }

  public static void test() {
    String s = "{jumeipushkey=jumeimall://page/web?url=http://i.jumeicd.com/h/wishdeal/onsale&uniqid=send_msg_to_all_users::59a54dceefed1}";
    String[] ss = s.split("\\{jumeipushkey=");
    for (String s1 : ss) {
      System.out.println("ssss===" + s1);
    }
  }

  public static void testStringTokenizer() {
    String test = "I/LOVE/YOU";
    StringTokenizer st = new StringTokenizer(test, "/");
    while (st.hasMoreElements()) {
      System.out.println(st.nextElement());
    }

    System.out.println("-----------------");

    while (st.hasMoreTokens()) {
      System.out.println(st.nextToken());
    }
  }

  public static void testString2() {
    String a = null;
    System.out.print("ajhdakjsdhfaksd----------" + a);
  }

}
