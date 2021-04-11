package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 17/8/9.
 * Description Math工具类测试
 * copyright generalray4239@gmail.com
 */
public class MathTest {

    public static void main(String[] args) {

        double log = Math.log10(1 + (Math.abs(200) / 30));

        System.out.println("the log10 === " + log);

        //ceil 取大于当前数的最近一个整数
        System.out.println("ceil(3.8)======" + Math.ceil(3.8) + "=======ceil(-3.8)====" + Math.ceil(-3.8));

        //和ceil相反：取小于当前数的最近一个整数
        System.out.println("floor(3.8)======" + Math.floor(3.8) + "=======floor(-3.8)====" + Math.floor(-3.8));
    }


}
