package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 04/01/2018.
 * Description
 * 基础循环测试
 * copyright generalray4239@gmail.com
 */
public class ForTest {


  public static void test() {
    int n1 = 0;
    int n2 = 0;
    for (int i = 0; i < 10; ++i) {
      //i====0--n1==0----++n1==1===n1=1
      System.out.println("i====" + i + "--n1==" + n1 + "----++n1==" + (++n1) + "===n1=" + n1);
    }

    for (int j = 0; j < 10; j++) {
      //j====0----n2==0---n2++====0====n2==1
      System.out.println("j====" + j + "----n2==" + n2 + "---n2++====" + (n2++) + "====n2==" + n2);
    }
  }

  public static void testSwitch(int num) {
    switch (num) {
      case 1: {
        System.out.println("num===" + num);
        break;
      }
      case 2: {
        System.out.println("num===" + num);
        break;
      }
      case 3: {
        System.out.println("num===" + num);
        break;
      }
    }

  }

  public static void testSwitch2(int num) {
    switch (num) {
      case 3: {
        System.out.println("----no---break--num===" + num);
        break;
      }
      case 2: {
        System.out.println("----no---break--num===" + num);
      }
      case 1: {
        System.out.println("----no---break--num===" + num);
      }

    }
  }

  public static void testFor() {
    int num = 0;
    for (; ; ) {
      System.out.println("----for num===" + num );
      num++;
      if (num == 5) {
        System.out.println("----num===" + num + " the for return");
        return ; //退出循环
      }
    }
  }

  public static void testWhile() {
    int num = 0;
    while (true) {
      if(num == 0) {
        System.out.println("----while num ==0");
      } else {
        System.out.println("----while num !=0");
      }
      ++num;
      break;
    }

  }

  public static void main(String[] args) {
    testWhile();
//        test();
//    testFor();
//    testSwitch2(1);
//    testSwitch(3);
//    testSwitch2(3);
  }
}
