package algorithms.leetcode;

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
public class CharLearn {

  /**
   *
   *若初始序列为gbfcdae，那么至少需要多少次两两交换，才能使该序列变为abcdefg ？
   *任给一个自由a--g这7个字母组成的排列，最坏的情况下需要至少多少次两两交换，才能使序列变为abcdefg ？
   *
   */
  public static void test1(String sourceStr) {
    char[] sourceChar = sourceStr.toCharArray();
    int num = 0;
    int out , in;


    for (out = sourceChar.length - 1; out > 0; out--) {
      for (in = 0; in < out; in++) {
        if (sourceChar[in] > sourceChar[in + 1]) {
          char temp = sourceChar[in];
          sourceChar[in] = sourceChar[in+1];
          sourceChar[in+1] = temp;
          System.out.println("switch num ===" + (++num) +"sourceChar["+in+"]===="+ sourceChar[in]+ "=====sourceChar["+(in+1)+"]="+sourceChar[in+1]+"===str == " +(new String(sourceChar)));
        }
      }
    }

    System.out.println("the sort string == " + (new String(sourceChar)));
  }

  /**
   *
   *
   *
   *
   */
  public static void test2() {

  }


  public static void main(String[] args) {
//    test1("gbfcdae");

    test1("gfedcba");
  }

}
