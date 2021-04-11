package algorithms.leetcode;

import java.util.Stack;

/**
 * Version 1.0
 * Created by lll on 2020-01-13.
 * Description
 * <pre>
 *     给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
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
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode32 {

    /**
     * error code
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        int lastMax = 0;
        Stack<Character> characterStack = new Stack<>();
        for (int i = 0, n = s.length(); i < n; i++) {
            char charAt = s.charAt(i);
            switch (charAt) {
                case '(':
                    characterStack.push(charAt);
                    break;
                case ')':
                    if (!characterStack.isEmpty()) {
                        char charLastPush = characterStack.pop();
                        if (charLastPush != '(') {
                            characterStack.clear();
                            if (lastMax < max) {
                                lastMax = max;
                                max = 0;
                            }
                        } else { // length +=2
                            max += 2;
                        }
                    }
                    break;
            }
        }

        if (lastMax < max) {
            lastMax = max;
        }

        System.out.println("the max ===" + lastMax);
        return lastMax;
    }

    /**
     * 参考答案：
     * <pre>
     *     思路：定义一个dp数组，dp[i]表示第i个字符串的最长有效括号。
     *
     *     1、对于字符串形如"……()"的字符串， dp[i] = dp[i-2] + 2;
     *     2、对于字符串形如"……))"的字符串   dp[i] = dp[i-1] + dp[i-dp[i-1]-2] +2
     *        //dp[i-dp[i-1]-2] 就是找到有效括号的起始位置
     *     示例：
     *        0 1 2 3 4 5 6 7   索引
     *        ( ) ) ( ( ( ) )   字符串
     *        0 2 0 0 0 0 2 4
     * </pre>
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len < 2) {
            return 0;
        }
        int maxans = 0;
        int dp[] = new int[len];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        System.out.println("the test2 maxans == " + maxans);
        return maxans;
    }

    /**
     * 参考答案二：
     *    核心就是找到上一个有效括号的起始位置
     * <pre>
     *     利用栈的思想
     *     我们可以用栈在遍历给定字符串的过程中去判断到目前为止扫描的子字符串的有效性，同时能的都最长有效字符串的长度。我们首先将 -1−1 放入栈顶。
     *
     *     1、对于遇到的每个 '(’ ，我们将它的下标放入栈中。
     *     2、对于遇到的每个 ‘)’ ，我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度。通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。
     *
     *    0     1       2       3       4       5       6       7   索引
     *    (     )       )       (       (       (       )       )   字符串
     * -1 u    pop     pop     push     push    push   pop     pop   栈操作
     *    0    0       -1       3       4        5      5       4    栈操作元素
     *  [0,-1] [-1]    [2]    [2,3]   [2,3,4]  [2,3,4,5] [2,3,4], [2,3] 栈中元素
     *        1-(-1)                                   6-4      7-3     最大元素
     * * </pre>
     */
    public int longestValidParentheses3(String str) {
        int maxans = 0;
        Stack<Integer> characterStack = new Stack<>();
        characterStack.push(-1); //解决0，pop异常的问题
        for (int i = 0, len = str.length(); i < len; i++) {
            char ca = str.charAt(i);
            switch (ca) {
                case '(':
                    characterStack.push(i);
                    break;
                case ')':
                    characterStack.pop();
                    if (characterStack.isEmpty()) {
                        characterStack.push(i);
                    } else {
                        maxans = Math.max(maxans, i - characterStack.peek());
                    }
                    break;
            }
        }
        System.out.println("the max values==" + maxans);
        return maxans;
    }


    public void testLeetCode32() {
        String testStr1 = "(()";
        longestValidParentheses2(testStr1);
        String testStr2 = ")()())";
        longestValidParentheses2(testStr2);
        String testStr3 = "()(()()";
        longestValidParentheses2(testStr3);
    }
}
