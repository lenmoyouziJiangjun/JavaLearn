package algorithms.chars;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Version 1.0
 * Created by lll on 2019-12-22.
 * Description
 * <pre>
 *   字符串匹配算法汇总
 *   一、BF算法：就是挨个挨个匹配
 *   二、KMP算法：https://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247485979&idx=2&sn=56d4d0dd11951c29c9e6f94803d92e03&scene=21#wechat_redirect
 *
 *   三、RK算法：将字符串分成模式串长度的子字符串数组，每个子字符串求hash，然后和模式匹配hash
 *
 *   参考网站：https://mp.weixin.qq.com/s/g1kGQobDOYcaWCpMrBtx3Q
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class CharsMatchs {

    /**
     * 模拟index of
     *
     * @param source
     * @param mathStr
     * @return
     */
    private static int indexOf(String source, String mathStr) {
        if (mathStr.length() > source.length()) {
            return -1;
        }
        if (mathStr.length() == 0 || source.length() == 0) {
            return -1;
        }
        char first = mathStr.charAt(0);
        int mathLen = mathStr.length();
        int sourceEnd = source.length() - mathLen;
        for (int i = 0; i <= sourceEnd; i++) {
            if (source.charAt(i) == first) {
                int end = (i + mathLen);
                for (int j = i + 1, k = 1; j < end; j++, k++) {
                    if (source.charAt(j) != mathStr.charAt(k)) {//字符串不匹配，从不匹配的下一个字符串开始
                        i = j;
                        break;
                    }
                    if (j == (end - 1)) { //找到了
                        return i;
                    }
                }
            }
        }

        return -1;
    }


    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
     * <p>
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     *
     * @param source
     * @param des
     * @return
     */
    public static int maxPublicSub(String source, String des) {
        int length1 = source.length();
        int length2 = des.length();
        int[][] dp = new int[length1 + 1][length2 + 1]; //定义一个二维数组，表示dp[i][j] 表示 source 的前i个字符 到 des的前j个字符串的公共子序列长度
        char[] sourceChars = source.toCharArray();
        char[] desChars = des.toCharArray();
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (sourceChars[i - 1] == desChars[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; //等于前面的加1；
                    System.out.print("  " + sourceChars[i - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); //如果不相等，就等于左边或者上边两个最大值
                }
            }
        }
        return dp[length1][length2];
    }

    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * <p>
     * 示例：
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     *
     * <pre>
     *     定义一个数组记录当前字符的子串长度，
     *     时间复杂度为O(n*n*n)
     *
     * </pre>
     *
     * @param str
     */
    public static void getMaxSubStr(String str) {
        char[] charArray = str.toCharArray();
        int len = charArray.length;
        int max1 = 0;
        int[] charLen = new int[len]; //存储对应的长度
        for (int i = 0; i < len; i++) {
            char c = charArray[i];
            for (int j = i + 1; j < len; j++) {
                char c1 = charArray[j];
                if (c == c1) {
                    charLen[i] = j - i;
                    if (charLen[i] > max1) {
                        max1 = charLen[i];
                    }
                    break;
                }
            }
        }
        System.out.println("the max1 len ==" + max1);
        int max = charLen[0];
        for (int i = 1; i < len; i++) {
            if (max < charLen[i]) {
                max = charLen[i];
            }
        }

        System.out.println("the max len ==" + max);
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     *
     * <pre>
     *    思路：
     *     1、找中间字符，从中间往两边遍历，如果两边相等，
     *     2、字符串反转，和源字符串 构成一个二维数组，按列遍历
     * </pre>
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.equals(""))
            return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[] arr = new int[length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
        /**************修改的地方***************************/
            for (int j = length - 1; j >= 0; j--) {
                /**************************************************/
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                    /**************修改的地方***************************/
                    //之前二维数组，每次用的是不同的列，所以不用置 0 。
                } else {
                    arr[j] = 0;
                }
                /**************************************************/
                if (arr[j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }

                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 将一个字符串转为整数
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        int result = 0;
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && ch == '-') {
                continue;
            }
            if ((ch - '0') < 0 || (ch - '0') > 9) {
                System.out.println("the string can not convert int");
                return -1;
            }
            if (result != 0) {
                result = (result * 10) + (ch - '0');
            } else {
                result = (ch - '0');
            }
        }

        if (str.charAt(0) == '-') {
            result = -result;
        }
        System.out.println("the result == " + result);
        return result;
    }


    public static boolean isPalindrome(int x) {
        if (x < 10) {
            return false;
        }
        int num = 0;
        while (x / 10 != 0) {
            num = num * 10 + (x % 10);
            x = x / 10;
        }
        if (x / 10 == 0) {
            num = num * 10 + (x % 10);
        }
        System.out.print("the num == " + num + " result=" + (num == x));
        return num == x;
    }

    /**
     * 正则匹配
     * <p>
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     *
     * @param s 源字符串
     * @param p 正则表达是
     * @return
     */
    public static boolean isMatch2(String s, String p) { // 文本串及长度
        int i = 0, j = 0;
        int sLen = s.length(), pLen = p.length();
        for (; i < sLen; i++) {
            for (; j < pLen; j++) {
                char pchar = p.charAt(j);
                if (pchar == '*') {

                }
            }
        }
        return false;
    }

    public static boolean isMatch(String s, String p) { // 文本串及长度
        boolean matched = false;
        rmatch(matched, 0, 0, s.toCharArray(), s.toCharArray(), s.length(), p.length());
        return matched;
    }

    private static void rmatch(boolean matched, int ti, int pj, char[] text, char[] pattern, int textLen, int patternLen) {
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == patternLen) { // 正则表达式到结尾了
            if (ti == textLen) matched = true; // 文本串也到结尾了
            return;
        }
        if (pattern[pj] == '*') { // *匹配任意个字符
            for (int k = 0; k <= textLen - ti; ++k) {
                rmatch(matched, ti + k, pj, text, pattern, textLen, patternLen);
            }
        }
        if ((ti < textLen && pattern[pj] == text[ti]) || pattern[pj] == '.') { // 纯字符匹配才行
            rmatch(matched, ti + 1, pj + 1, text, pattern, textLen, patternLen);
        }
    }




    public static void main(String[] args) {
        int len = indexOf("cabceabcefabcefd", "cefd");
        System.out.println("result === " + len);

        int test = maxPublicSub("abcdefg", "aef");

        System.out.println("最长子串 === " + test);


        getMaxSubStr("bbbbb");


        myAtoi("-1000267");

        isPalindrome(121);
    }

}
