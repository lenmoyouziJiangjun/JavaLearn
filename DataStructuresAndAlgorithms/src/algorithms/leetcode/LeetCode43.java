package algorithms.leetcode;

import java.math.BigInteger;

/**
 * Version 1.0
 * Created by lll on 2020-02-29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class LeetCode43 {


    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * <p>
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * <p>
     * <p>
     * 要求：
     * <p>
     * 1、num1 和 num2 的长度小于110。
     * 2、num1 和 num2 只包含数字 0-9。
     * 3、num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 4、不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     *
     * <pre>
     *     思路: 需要处理类型溢出。 处理bigIntenger
     *
     * </pre>
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
//        Integer.parseInt()
        return stringToInt(num1) * stringToInt(num2) + ""; //
    }


    private long stringToInt(String num) {
        int len = num.length();
        int i = 0;
        long result = 0;
        if (len > 110 || len < 1 || (len > 2 && num.charAt(i) == '0')) {
            throw new RuntimeException("the input error");
        }
        while (i < len) {
            result = result * 10;
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                throw new RuntimeException("the input error");
            }
            int n = (c - '0');
            System.out.println("the char===" + c + " n===" + n);
            result += n;
            i++;
        }
        System.out.println("the num===" + result);
        return result;
    }

    /**
     * 计算两个字符串相乘
     * <p>
     * 正确解法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int l1 = num1.length(), l2 = num2.length(), l = l1 + l2;
        char[] ans = new char[l];
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();

        for (int i = l1 - 1; i >= 0; --i) {
            int c = c1[i] - '0';
            for (int j = l2 - 1; j >= 0; --j) {
                int cc = c2[j] - '0';
                ans[i + j + 1] += c * cc;   //模拟数学乘法
            }
        }
        for (int i = l - 1; i > 0; --i) {
            if (ans[i] > 9) {
                ans[i - 1] += ans[i] / 10;   //大于10了就要进位
                ans[i] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; ; ++i) if (ans[i] != 0) break;
        for (; i < ans.length; ++i) sb.append((char) (ans[i] + '0'));
        return sb.toString();
    }

    /**
     * 计算一个大数(用字符串表示) 和一个一位数相乘
     *
     * @param num1 String as big num
     * @param num2
     * @return
     */
    public static String multiply3(String num1, int num2) {
        if (num1.equals("0") || num2 == 0 || Math.abs(num2) > 10) {
            System.out.println("0");
        }
        int l1 = num1.length(), l = l1 + 1;
        int[] ans = new int[l]; //
        char[] c1 = num1.toCharArray();
        boolean isminus = false;
        for (int i = l1 - 1; i >= 0; --i) { // revert get String char
            if (i == 0 && c1[i] == '-') { //last char is minus symbol
                isminus = true;
                break;
            } else {
                int c = c1[i] - '0';
                if (c > 9 || c < 0) {
                    throw new RuntimeException("input params errors");
                }
                int result = ans[i + 1] + (Math.abs(num2) * c); //get the item result

                ans[i + 1] = result % 10; //
                ans[i] += result / 10; // add num in next position
                System.out.println("the index==" + i + " == result==" + result + "----" + ans[i + 1] + "---" + ans[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        if ((isminus && num2 > 0) || (num2 < 0 && !isminus)) {
            sb.append("-");
        }
        for (int i = 0; i < ans.length; i++) {
            if (i == 0 && ans[i] == 0) {
                continue;
            }
            sb.append(ans[i]);
        }
        System.out.println("the result ==" + sb.toString());
        return sb.toString();
    }

    public void test() {
        String result = multiply2("12",
                "12");

        String result2 = multiply3("12", -2);
        System.out.println("the result == " + result);
    }

}
