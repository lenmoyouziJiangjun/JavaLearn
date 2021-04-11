package com.lll.basic;

import java.util.StringTokenizer;

/**
 * Version 1.0
 * Created by lll on 17/8/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StringTest {

    int a = 10;
    String name = "王五";

    public void updateA(int num, String tes) {
        num = 111;
        tes = "找事";
//        name = tes;
//        a = num;

        System.out.println("double value ==" + Double.parseDouble("0795150"));
    }

    public static void main(String[] args) {
//        test();
//        testStringTokenizer();
//        testString2();

        StringTest test = new StringTest();
        test.updateA(test.a, test.name);
        System.out.println("a===" + test.a + "-----name===" + test.name); //

        String num = "001";
        if (num.matches("^[+]{0,1}(\\d+)$")) {
            int in = Integer.parseInt(num);
            System.out.println("num === " + in);
        }

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
