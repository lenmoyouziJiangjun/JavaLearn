package algorithms.leetcode;

import java.util.Stack;

/**
 * Version 1.0
 * Created by lll on 2019-12-14.
 * Description
 * copyright generalray4239@gmail.com
 */
public class leetcode2 {


  /**
   * 给定一个只包括 '('，')'的字符串，判断字符串是否有效。注：空字符串属于有效字符串
   * 例如 输入"（（））" true
   * 输入 "(()))" false
   *
   * @param s
   * @return
   */
  public static boolean validateStr(String s) {
    if (s == null || s.length() < 1)
      return true;
    int n = s.length();// 字符串长度
    // 用来记录遇到的 "(" 的个数
    int sum = 0;
    // 遍历字符串
    for (int i = 0; i < n; i++) {
      // 获取字符串的第 i 个字符
      char c = s.charAt(i);
      if (c == '(') {
        sum++;
      } else {
        if (sum == 0)
          return false;
        else
          sum--;
      }
    }
    return sum == 0 ? true : false;
  }


  /**
   * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
   *
   * 输入: "(()"
   * 输出: 2
   * 解释: 最长有效括号子串为 "()"
   * 示例 2:
   *
   * 输入: ")()())"
   * 输出: 4
   * 解释: 最长有效括号子串为 "()()"
   *
   * @param str
   * @return
   */
  public static int getMaxValidSubStrLength(String str) {
//    int max = 0;
//    Stack<Integer> stack = new Stack<>();
//    stack.push(-1);
//    for (int i = 0; i < s.length(); i++) {
//      if (s.charAt(i) == '(') {
//        //下标入栈
//        stack.push(i);
//      } else {
//        // 出栈
//        stack.pop();
//        // 看栈顶是否为空，为空的话就不能作差了
//        if (stack.empty()) {
//          stack.push(i);
//        } else {
//          // i - 栈顶，获得档期有效括号长度
//          max = Math.max(max, i - stack.peek());
//        }
//      }
//    }
//    return maxans;


    int left = 0, right = 0, max = 0;
    // 从左到右
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }
      if (left == right) {
        max = Math.max(max, 2 * right);
      } else if( right > left) {
        left = right = 0;
      }
    }
    left = right = 0;
    // 从右到左
    for (int i = str.length() - 1; i >= 0; i--) {
      if (str.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }
      if (left == right) {
        max = Math.max(max, 2 * left);
      } else if (left > right) {
        left = right = 0;
      }
    }
    return max;
  }

  /**
   * 实现方案二：空间负责读为O(N)，时间负责读O(N)
   * @param str
   */
  public static void getMaxValidSubStr(String str) {
    int length = str.length();
    char[] resultsChars = new char[length];
    Stack<Integer> numStack = new Stack<>();
    for (int i = 0; i < length; i++) {
      char ca = str.charAt(i);
      if (ca == '(') {
        numStack.push(i);
      } else if (ca == ')') {
        if (!numStack.isEmpty()) {
          resultsChars[numStack.pop()] = '(';
          resultsChars[i] = ')';
        }
      } else {
        System.out.print("other char");
      }
    }

    System.out.println("the result char === ");
    int num = 0;
    for (int j = 0; j < length; j++) {
      char r = resultsChars[j];
      if(r != 0) {
        ++num;
        System.out.print(r);
      }
    }
    System.out.println();
    System.out.println("the result char num=== " + num);
  }


  public static void main(String[] args) {
    String str = "((())()";
    getMaxValidSubStr(str);
  }




}
