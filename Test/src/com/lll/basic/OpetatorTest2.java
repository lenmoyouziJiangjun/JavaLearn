package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 2020-01-07.
 * Description
 * copyright generalray4239@gmail.com
 */
public class OpetatorTest2 {

    public static void main(String[] args) {
        int i = 0;
        System.out.println(i++ + ++i);
        System.out.println("i===" + i);

        int k = 0;
        int j = 0;
        System.out.println("k++ ===" + k++ + " k==" + k); //k++ ===0 k==1
        System.out.println("++j ===" + ++j + " j==" + j); //++j ===1 k==1
    }
}
