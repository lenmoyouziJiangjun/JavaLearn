package com.lll.basic;

import java.io.FileNotFoundException;

/**
 * Version 1.0
 * Created by lll on 2020-03-27.
 * Description
 * copyright generalray4239@gmail.com
 */
public class TryCatchTest {


    public int testTry() {
        try {
            System.out.println("try out");
            int b = 10/0;
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception out");
            return 1;
        } finally {
//            System.out.println("finally try out");
            try {
                System.out.println("finally try out");
//                int b = 10/0;
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("finally exception out");
//                return 3;
            }
        }

    }



    public static void main(String[] args) {
        TryCatchTest test = new TryCatchTest();
        System.out.println(test.testTry());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
