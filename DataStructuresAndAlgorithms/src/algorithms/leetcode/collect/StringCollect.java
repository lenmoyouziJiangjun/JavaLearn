package algorithms.leetcode.collect;

/**
 * Version 1.0
 * Created by lll on 2020-05-06.
 * Description
 * <pre>
 *     字符串相关算法汇总
 *        1、是否包含某个子串
 *        2、字符串编辑距离
 *        3、字符串的正则匹配
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class StringCollect {

    /**
     * <pre>
     *     给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     *
     *     如果不存在最后一个单词，请返回 0 。
     *
     *     说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     *
     * </pre>
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.trim() == "") {
            return 0;
        }
        String[] words = s.trim().split(" ");
        if (words.length == 0) {
            return 0;
        }

        int len = words.length;
        int result = words[len - 1].toCharArray().length;
        System.out.println("the length of last word ====" + result);

        return result;
    }

    /**
     * 最后一个单词的长度
     * <p>
     * 实现思路：采用 trim 的逻辑，迭代找到最后一个空格到字符串的length
     *
     * @param s
     * @return
     */
    public int lengthOfLastWordEnhance(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        int end = len - 1;
        while ((end >= 0) && s.charAt(end) <= ' ') { //取出末尾的空格
            end--;
        }
        if (end <= 0) {
            System.out.println("the result ==0");
            return 0;
        }

        int start = end;
        while ((start >= 0) && s.charAt(start) != ' ') { //取出最后的单词的索引
            start--;
        }


        int result = end - start;
        String lastWord = s.substring(start + 1, end + 1);

        System.out.println("the end===" + end + " start===" + start + ", last word ==" + lastWord + ", result ===" + result + "");
        return result;
    }


    /**
     * LeetCode 第 242 题：给定两个字符串 s 和 t，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 字母异位词，也就是两个字符串中的相同字符的数量要对应相等
     *
     * <pre>
     *     分析： 1、两个字符串必须有相同字符,
     *           2、字符出现的次数必须相等，
     *           3、字符出现的位置可以不同
     *     暴力解法，采用map结构，记录每个字符出现的次数，然后在对比str2，没匹配一次，数字-1，判断后面是否等于0
     *
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean isEctopicString(String str1, String str2) {
        if (str1 == null || str2 == null || (str1.length() != str2.length())) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] charNums = new int[25]; //定义一个数组长度为26的数组，每个位置对应存储字符的数量，
        for (int i = 0, n = chars1.length; i < n; i++) {
            int index = indexChar(chars1[i]); //数组1用来加值，
            charNums[index] += 1;

            int index2 = indexChar(chars2[i]);//数组2用来减1；
            charNums[index2] -= 1;
        }

        for (int k = 0; k < 25; k++) {
           if(charNums[k] != 0){
               return false;
           }
        }
        return true;
    }

    private int indexChar(char ch) {
        if (ch < 'a' || ch > 'z') {
            throw new RuntimeException("the char mast between a and z");
        }
        return ch - 'a';
    }


    public static void main(String[] args) {

        StringCollect collect = new StringCollect();
        collect.lengthOfLastWordEnhance(" Hello World     ");
        char a = 'a';
        char z = 'z';
        System.out.println(" a== " + ((int) a) + " ,z===" + ((int) z));

    }
}
