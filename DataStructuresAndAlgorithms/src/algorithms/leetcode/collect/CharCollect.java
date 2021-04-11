package algorithms.leetcode.collect;

import java.util.Arrays;

/**
 * Version 1.0
 * Created by lll on 2019-12-03.
 * Description
 * <pre>
 *   字符串算法相关总结
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class CharCollect {

    /**
     * 若初始序列为gbfcdae，那么至少需要多少次两两交换，才能使该序列变为abcdefg ？
     * 任给一个自由a--g这7个字母组成的排列，最坏的情况下需要至少多少次两两交换，才能使序列变为abcdefg ？
     */
    public static void test1(String sourceStr) {
        char[] sourceChar = sourceStr.toCharArray();
        int num = 0;
        int out, in;


        for (out = sourceChar.length - 1; out > 0; out--) {
            for (in = 0; in < out; in++) {
                if (sourceChar[in] > sourceChar[in + 1]) {
                    char temp = sourceChar[in];
                    sourceChar[in] = sourceChar[in + 1];
                    sourceChar[in + 1] = temp;
                    System.out.println("switch num ===" + (++num) + "sourceChar[" + in + "]====" + sourceChar[in] + "=====sourceChar[" + (in + 1) + "]=" + sourceChar[in + 1] + "===str == " + (new String(sourceChar)));
                }
            }
        }

        System.out.println("the sort string == " + (new String(sourceChar)));
    }

    /**
     * <pre>
     *
     *     给定一组字符，使用原地算法将其压缩。
     *
     * 压缩后的长度必须始终小于或等于原数组长度。
     *
     * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
     *
     * 在完成原地修改输入数组后，返回数组的新长度。
     *   示例：
     *    输入：["a","a","b","b","c","c","c"]
     *    输出6(压缩后的长度)： 压缩后的数组为 ["a","2","b","2","c","3"]
     *
     *  要求：空间复杂度为O(1)
     * </pre>
     *
     * <pre>
     *     分析：
     *     1、数组的压缩，肯定是要需要遍历数组的，所以时间负责度最低应该为O(N)
     *     2、空间复杂度为O(1): 就是不能创建新对象，只能在原有数组上面操作。
     *        原有数组上面操作，就只能用读写指针(读readIndex写writeIndex索引)，
     *
     * </pre>
     */
    public static void leetCode443_compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new RuntimeException("input param not null");
        }

        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        System.out.println("the result ====" + write);
        for (int i = 0; i < write; i++) {//输出前write个字符
            System.out.print(chars[i] + " ");
        }
        System.out.println(" ");
    }


    public static void main(String[] args) {
//    test1("gbfcdae");

//        test1("gfedcba");


        leetCode443_compress("aabbccccaaa".toCharArray());
    }

}
