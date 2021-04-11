package algorithms;

import java.util.Scanner;

/**
 * Version 1.0
 * Created by lll on 2020-08-10.
 * Description
 * copyright generalray4239@gmail.com
 */
public class LoveInterview {


    private static String formatString(char[] str) {
        if (str == null || str.length == 0) {
            throw new RuntimeException("str can not null");
        }
        char lastChar = str[0];
        int charNums = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1, n = str.length; i < n; i++) {
            char currentChar = str[i];
            if (currentChar < 'a' || currentChar > 'z') {
                throw new RuntimeException("str error");
            }
            if (currentChar == lastChar) {
                charNums += 1;
            } else {
                sb.append(lastChar);
                if (charNums != 1) {
                    sb.append(charNums);
                }
                lastChar = currentChar;
                charNums = 1;
            }
        }
        sb.append(lastChar);
        if (charNums != 1) {
            sb.append(charNums);
        }
        String result = sb.toString();
        System.out.println("the result ==" + result);
        char[] resultChar = result.toCharArray();
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String testChar = sc.next();
        formatString(testChar.toCharArray());
    }
}
