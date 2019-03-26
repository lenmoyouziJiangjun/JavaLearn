package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 04/01/2018.
 * Description
 * 基础数学
 * copyright generalray4239@gmail.com
 */
public class MathLearn {


    public static void main(String[] args){
        //取整 -> 有理无理入
        MathLearn.print("Math.ceil(1.2)",Math.ceil(1.2));
        MathLearn.print("Math.ceil(-1.2)",Math.ceil(-1.2));
        MathLearn.print("Math.ceil(1.7)",Math.ceil(1.7));
        MathLearn.print("Math.ceil(-1.7)",Math.ceil(-1.7));

        //四舍五入
        MathLearn.print("Math.round(1.2)",Math.round(1.2));
        MathLearn.print("Math.round(-1.2)",Math.round(-1.2));
        MathLearn.print("Math.round(1.7)",Math.round(1.7));
        MathLearn.print("Math.round(-1.7)",Math.round(-1.7));

        //取整，->有理无理都舍
        MathLearn.print("Math.floor(1.2)",Math.floor(1.2));
        MathLearn.print("Math.floor(-1.2)",Math.floor(-1.2));
        MathLearn.print("Math.floor(1.7)",Math.floor(1.7));
        MathLearn.print("Math.floor(-1.7)",Math.floor(-1.7));//Math.floor(-1.7)====-2.0


        int a=0;
        int b = ~a;//对0取反，
        int c = ~b;

        MathLearn.print("~0=====",b);
        MathLearn.print("~0=====",c);

        MathLearn.print("90转16进制",Integer.toHexString(((int)(255*0.9))));
        MathLearn.print("60转16进制",Integer.toHexString(((int)(255*0.6))));
        MathLearn.print("60转16进制",Integer.toHexString(((int)(255*0.2))));
        MathLearn.print("FF转10进制",Integer.parseInt("FF",16));
    }




    private static<T> void print(String str,T t){
        System.out.println(str+"===="+t);
    }

}
