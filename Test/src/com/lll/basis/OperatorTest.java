package com.lll.basis;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/7/7.
 * Description java 运算符测试
 * copyright generalray4239@gmail.com
 */
public class OperatorTest {

    public static void main(String[] args) throws Exception {
        binaryOperator();
        dualOperatorTest();
        testDataType();
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
     */
    public static void binaryOperator() {
        System.out.println("1&2==" + (1 & 2));
        System.out.println("1|2==" + (1 | 2));
        System.out.println("~2==" + (~2));
        System.out.println("1^2==" + (1 ^ 2));

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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
            System.out.println("-i==" + i + "----list.size()==" + list.size());
        }
        for (int i = 0, size = list.size(); i < size; i++) {
            System.out.println("i===" + i + "-----" + list.get(i));
        }
    }

    public static void testDataType(){
        System.out.print("long max==="+Long.MAX_VALUE+"-----min===="+Long.MIN_VALUE);
        System.out.print("int max==="+Integer.MAX_VALUE+"-----min===="+Integer.MIN_VALUE);
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
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
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


}
