package algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-01-08.
 * Description
 * copyright generalray4239@gmail.com
 */
public class LeetCodeTest {




    /**
     * 泛型方法T 必须是包装类型，不是是int等基础类型
     *
     * @param tArray
     * @param <T>
     */
    public static <T> void printArray(T[] tArray) {
        System.out.println("-------------printArray--------");
        for (T t : tArray) {
            System.out.print("  " + t);
        }
        System.out.println();
    }

    public static void printArray(int[] tArray) {
        System.out.println("-------------printArray--------");
        for (int t : tArray) {
            System.out.print("  " + t);
        }
        System.out.println();
    }

    public static <T> void printList(List<T> list) {
        System.out.println("-------------printList--------");
        for (T t : list) {
            System.out.print("  " + t);
        }
        System.out.println();
    }

    public static List<Integer> arrayToList(int[] nums) {
        List<Integer> numList = new ArrayList<>();
        for (int i : nums) {
            numList.add(i);
        }
        return numList;
    }

    public static void testLeetCode31() {
        LeetCode31 code31 = new LeetCode31();
        code31.test();
    }

    public static void testLeetCode32() {
        LeetCode32 code32 = new LeetCode32();
        code32.testLeetCode32();
    }

    public static void testLeetCode33() {
        LeetCode33 code33 = new LeetCode33();
        code33.test();
    }


    public static void testLeetCode34() {
        LeetCode34 code34 = new LeetCode34();
        code34.test();
    }

    public static void testLeetCode35() {
        LeetCode35 code35 = new LeetCode35();
        code35.test();
    }

    public static void testLeetCode39() {
        LeetCode39 code39 = new LeetCode39();
        code39.test();
    }

    public static void testLeetCode40() {
        LeetCode40 code40 = new LeetCode40();
        code40.test();
    }

    public static void testLeetCode41() {
        LeetCode41 code41 = new LeetCode41();
        code41.test();
    }


    public static void testLeetCode43() {
        LeetCode43 code43 = new LeetCode43();
        code43.test();
    }

    public static void testLeetCode44() {
        LeetCode44 code44 = new LeetCode44();
        code44.test();
    }

    public static void testLeetCode45() {
        LeetCode45 code45 = new LeetCode45();
        code45.test();
    }


    public static void testLeetCode46() {
        LeetCode46 code46 = new LeetCode46();
        code46.test();
    }



    /**
     * @param args
     */
    public static void main(String[] args) {
//        testLeetCode31();
//        testLeetCode32();
//        testLeetCode33();
//        testLeetCode34();
//        testLeetCode35();
//        testLeetCode39();
//        testLeetCode40();
//        testLeetCode41();
//        testLeetCode44();
        testLeetCode46();
    }
}
