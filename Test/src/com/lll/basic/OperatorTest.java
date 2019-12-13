package com.lll.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Version 1.0
 * Created by lll on 17/7/7.
 * Description java 运算符测试
 * copyright generalray4239@gmail.com
 */
public class OperatorTest {


  public static final int MEASURED_SIZE_MASK = 0x00ffffff;

  public static final int MEASURED_STATE_MASK = 0xff000000;


  public static void main(String[] args) throws Exception {
//        binaryOperator();
//        dualOperatorTest();
//        testDataType();
//        testNum(3.8f);
//        testOpe2();
//        testDextroposition();

    testSort();
  }

  /**
   * 逻辑运算符测试
   * java 的逻辑运算符有：
   * &&，&: &&和&都表示与，&&表示第一个条件为false时，后面的条件就不执行，&要对所有的条件都进行判断
   * ||，|: ||和|都表示或，||表示第一个条件为true时，后面的条件都不判断；| 对所有的条件进行判断
   */
  public static void logicalOperators() {

  }

  /**
   * 关系运算符：
   * java的关系运算符：==；  != ； > ； >=；  <；  <=；
   */
  public static void relationalOperators() {

  }


  /**
   * 位运算符(都是二进制运算，先将10进制转为二进制)
   * 与（&）: 两个操作数中位都为1，结果才为1，否则结果为0
   * 非（~）: 两个位只要有一个为1，那么结果就是1，否则就为0
   * 或（|）:  如果位为0，结果是1，如果位为1，结果是0，
   * 异或（^）:两个操作数的位中，相同则结果为0，不同则结果为1
   * <p>
   * 左移：<<  :乘以2的n次方
   * 右移：>>  :除以2的n次方
   * 无符号右移：>>> :忽略符号位，空位都以0补齐
   * <p>
   * 1、10进制转2进制：Integer.toBinaryString()
   * 2、10进制转8进制：toOctalString
   * <p>
   * 3、将一个二进制字符串转为十进制： parseUnsignedInt（）
   */
  public static void binaryOperator() {

    System.out.println("1&2==" + Integer.toBinaryString(1) + "&" + Integer.toBinaryString(2) + "=====" + (1 & 2));
    System.out.println("1|2==" + Integer.toBinaryString(1) + "|" + Integer.toBinaryString(2) + "=======" + (1 | 2));
    System.out.println("~2==" + (~2));
    System.out.println("1^2==" + (1 ^ 2));

    System.out.println("ffffff====" + Integer.parseInt("ffffff", 16) + "---");
    System.out.println("123转2进制====" + Integer.toBinaryString(123) + "---");

    System.out.println("1 & 0x00ffffff===" + (1 & MEASURED_SIZE_MASK));
    System.out.println("1 & 0xff000000===" + (1 & MEASURED_STATE_MASK));

    System.out.println("1 | 0xff000000===" + (1 & MEASURED_STATE_MASK));

    System.out.println("1 ^ 0xff000000===" + (1 & MEASURED_STATE_MASK));


    //交换两个数
    int x = 10, y = 12;
    x = x ^ y;
    y = x ^ y;
    x = x ^ y;
    System.out.println("x===" + x + "; y===" + y);

    //位运算
    System.out.println("1<<2 表示1*2的2次方===" + (1 << 2));
    System.out.println("10>>2 表示10除以2的2次方===" + (10 >> 2) + "------10/8==" + (10 / 4));
  }

  /**
   * ++ 和-- 测试
   */
  public static void dualOperatorTest() {
    int n1 = 0;
    int n2 = 0;
    for (int i = 0; i < 10; ++i) {
      //i====0--n1==0----++n1==1===n1=1
      // ++n1 == n1当前值+1；并将n1当前值+1赋值给n1;
      System.out.println("i====" + i + "--n1==" + n1 + "----++n1==" + (++n1) + "===n1=" + n1);
    }

    for (int j = 0; j < 10; j++) {
      //j====0----n2==0---n2++====0====n2==1
      //n2++ == n2当前值；并将n2当前值+1 赋值给n2;
      System.out.println("j====" + j + "----n2==" + n2 + "---n2++====" + (n2++) + "====n2==" + n2);
    }
  }

  /**
   * 测试技术数据类型精度
   */
  public static void testDataType() {
    System.out.print("long max===" + Long.MAX_VALUE + "-----min====" + Long.MIN_VALUE);
    System.out.print("int max===" + Integer.MAX_VALUE + "-----min====" + Integer.MIN_VALUE);
    // byte
    System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
    System.out.println("包装类：java.lang.Byte");
    System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
    System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
    System.out.println();

    // short
    System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
    System.out.println("包装类：java.lang.Short");
    System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
    System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
    System.out.println();

    // int
    System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
    System.out.println("包装类：java.lang.Integer");
    System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE + "------");
    System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE + "------2的" + Math.log1p(2147483648D) + "次方");
    System.out.println();

    // long
    System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
    System.out.println("包装类：java.lang.Long");
    System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
    System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
    System.out.println();

    // float
    System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
    System.out.println("包装类：java.lang.Float");
    System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
    System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
    System.out.println();

    // double
    System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
    System.out.println("包装类：java.lang.Double");
    System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
    System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
    System.out.println();

    // char
    System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
    System.out.println("包装类：java.lang.Character");
    // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
    System.out.println("最小值：Character.MIN_VALUE="
            + (int) Character.MIN_VALUE);
    // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
    System.out.println("最大值：Character.MAX_VALUE="
            + (int) Character.MAX_VALUE);

  }

  /**
   * 左移右移运算符
   */
  public static void testDextroposition() {
    //左移，放大， 乘以2的N次方
    System.out.println("1<<1======" + (1 << 1));//2
    System.out.println("1<<1======" + (1 << 2));//4

    //右移，缩小，除以2的N次方
    System.out.println("1>>1======" + (1 >> 1));
    ///无符号右移
    System.out.println("3>>>1======" + (3 >>> 1));
    System.out.println("8>>>1======" + (8 >>> 1));

    System.out.println("10>>2======" + (10 >> 2));
    System.out.println("-10>>>2======" + (-10 >>> 2));
  }

  private static <T> void siftUpComparable(int k, T x, Object[] array) {
    Comparable<? super T> key = (Comparable<? super T>) x;
    while (k > 0) {
      int parent = (k - 1) >>> 1;
      Object e = array[parent];
      if (key.compareTo((T) e) >= 0) {
        break;
      }
      array[k] = e;
      k = parent;
    }
    array[k] = key;
  }

  public static void testSort() {
    String array[] = new String[10];
    for (int i = 0; i < array.length; i++) {
      String value = "abc" + new Random().nextInt(10);
      System.out.println("--" + i + "----testSort-----value==" + value);
      siftUpComparable(i, value, array);
    }

    for (int i = 0; i < array.length; i++) {
      System.out.println("--testSort-----array[" + i + "]----==" + array[i]);
    }
  }


  /**
   * 精度问题
   *
   * @param f
   */
  public static void testNum(float f) {
    int i = (int) f;//float 强转为int将舍弃小数部分
    int i2 = (int) (f + .5f);//四舍五入
    System.out.print("i====" + i);
  }

  public static void testOpe2() {
    boolean b = 12 < 24 == false;
    //执行步骤1：12<24 返回true;
    //执行步骤2：true ==false; 返回false;
    //执行步骤3： b = false;
    System.out.println("b====" + b);
  }


}
