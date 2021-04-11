package javapattern.flyweight;

/**
 * Version 1.0
 * Created by lll on 2020-05-06.
 * Description
 * copyright generalray4239@gmail.com
 */
public class FlyWeight {

    public static void main(String[] args) {
        Integer a_12 = Integer.valueOf(12);
        Integer b_12 = Integer.valueOf(12);
        System.out.println("a_12 == b_12" + (a_12 == b_12));
        String ab = "ab";
        String ab2 = "ab";
        String ab3 = String.valueOf("ab");

        System.out.println("ab == ab2" + (ab == ab2));
        System.out.println("ab3 == ab2" + (ab3 == ab2));
    }


}
