package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-03-06.
 * Description
 *
 * <pre>
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 *   '?' 可以匹配任何单个字符。
 *   '*' 可以匹配任意字符串（包括空字符串）。
 * <p>
 * copyright generalray4239@gmail.com
 */
public class LeetCode44 {


    boolean isMatched = false;

    /**
     * @param s 字符串 可能为空，且字包含从a-z的小写字母
     * @param p 模式串 可能为空，且字包含从a-z的小写字母，以及字符"？"和"*"
     *          "？" 匹配 任何单个字符， "*"匹配任意字符包括空字符串
     *          <p>
     *          <p>
     *          分析：
     * @return
     */
    public boolean isMatch(String s, String p) {

        if (p != null && p == "*") {
            return true;
        }

        if (s != null && s.equals(p)) {
            return true;
        }
        if (s == null && p == null) {
            return true;
        }
        int lenS = s.length();
        int lenP = p.length();

        rematch(0, 0, s, p, lenS, lenP);
        return isMatched;
    }

    /**
     * @param sIndex     文本索引
     * @param pIndex     匹配字符串索引
     * @param text       : 字符串
     * @param sLen       字符串长度
     * @param pLen       模式串长度
     * @param patternStr 模式串
     */
    private void rematch(int sIndex, int pIndex, String text, String patternStr, int sLen, int pLen) {

        if (isMatched) {
            return;
        }

        if (pIndex == pLen) { //匹配到末尾了
            if (sIndex == sLen) { //文本刚好匹配完
                isMatched = true;
            }
            return; //模式串已经匹配完了，返回
        }
        if (sIndex == sLen) {
            return;
        }


        char pChar = patternStr.charAt(pIndex);

        char sChar = text.charAt(sIndex);

        System.out.println("the pIndex==" + pIndex + "--pChar==" + pChar + " --sIndex==" + sIndex + "----sChar==" + sChar);
        if (pChar == '?') { //继续下一个字符匹配
            rematch(sIndex + 1, pIndex + 1, text, patternStr, sLen, pLen);
        } else if (pChar == '*') { //匹配任意长度字符,
            //从sindex 开始，看后面数据是否和pIndex+1匹配，如果.因为pIndex 匹配任意字符串，只有后面的能够后pIndex+1匹配上，就行
            for (int i = 0; i <= sLen - sIndex; i++) {
                rematch(sIndex + i, pIndex + 1, text, patternStr, sLen, pLen);
            }
        } else if (sIndex < sLen && pChar == sChar) {
            rematch(sIndex + 1, pIndex + 1, text, patternStr, sLen, pLen);
        }
    }

    public void test() {
        String s = "acdcb";//"ab";//"adceb";
        String p = "a*c?b";//"?a";//"*a*b";


        boolean result = isMatch(s, p);
        System.out.println("the match result == " + result);
    }
}
