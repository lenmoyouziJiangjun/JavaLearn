package algorithms.leetcode.interview;

import java.util.Scanner;

/**
 * Version 1.0
 * Created by lll on 2020-05-13.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ChinaMobileInterview {

    /**
     * <pre>
     *
     *     天又变黑了，但是这次，史蒂夫有了另一个计划。
     *     与其躲在自己的房子里（现在已经满是鹅卵石），他将把火把放在他周围，以防止怪物产生。
     *
     *     要制造火炬，他需要煤和棍棒。将1根棍子和1块煤结合后，他将得到4个火炬。
     *
     *     要制作木棍，他可以使用木板。他可以从2个木板中得到4条木棍。
     *
     *     但是，他不能从一块木板上得到任何木棍。
     *
     *     他可以使用木原木来制作木板。他可以从1个木原木中得到4个木板。
     *
     *     如果您知道史蒂夫（Steve）有多少原木、木板、木棍和煤炭，请计算可以创建的火炬数量。
     * </pre>
     * <p>
     * num1: 原木数量
     * num2：木板数量
     * num3: 木棍数量
     * num4: 煤炭数量
     * <p>
     * return ： 火炬数量
     */
    private static int getFireNum(int num1, int num2, int num3, int num4) {
        if (num4 < 1) { //没有煤炭
            return 0;
        }
        if (num3 < num4) { //木棍数量< 煤炭数量
            if (num1 > 0) {
                num2 += (num1 * 4);
            }
            if (num2 >= 2) { //木板数量>0
                num3 = (num2 / 2) * 4; //计算木棍数量
            }
            if (num3 > 0) { //有木棍
                if (num3 < num4) {
                    return num3 * 4;
                } else {
                    return num4 * 4;
                }
            } else { //没有木棍
                return 0;
            }
        } else { //木棍 = || > 煤炭数量
            return num4 * 4;
        }
    }


    /**
     * 现在社会信息泄露越来越严重，某大学生设计了一种新的加密技术，
     * 该技术通过以巧妙的方式在消息的字符之间插入随机生成的字符串来对消息进行编码。
     * 为了验证方法，
     * 请你编写一个程序来检查消息是否真正编码在最终字符串中。给定两个字符串s和t，
     * 您必须确定s是否为t的子序列，即是否可以从t中删除字符，使得其余字符的串联为s。
     *
     * <pre>
     *     简单的说，就是一个正则表达式的匹配，一个字符只能匹配一次
     *
     * </pre>
     *
     * @param str1 (原始字符串)正则表达式
     * @param str2 (加密字符串)字符串
     */
    private static String matchStr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "NO";
        }

        if (str1.equals(str2)) {
            return "YES";
        }

        if (str1.length() > str2.length()) {
            return "NO";
        }

        char[] charStr1 = str1.toCharArray();
        char[] charStr2 = str2.toCharArray();
        int k = 0;
        boolean[] result = new boolean[str1.length()];

        for (int i = 0, n = charStr1.length; i < n; i++) {
            char s1 = charStr1[i];
            for (int j = k, n2 = charStr2.length; j < n2; j++) {
                char s2 = charStr2[j];
                if (s1 == s2) {
                    k = j;
                    result[i] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (!result[i]) {
                return "NO";
            }
        }
        return "YES";
    }


    public static void main(String[] args) {
        String result = matchStr("VERDI", "vivaVittorioEmanueleReDiItalia");
        System.out.println(result);
    }

}
