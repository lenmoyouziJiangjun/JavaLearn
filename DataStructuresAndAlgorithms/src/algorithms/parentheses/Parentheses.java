package algorithms.parentheses;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-01-05.
 * Description
 * <pre>
 *     括号相关算法
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class Parentheses {

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * <p>
     * 例如，给出 n = 3，生成结果为：
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * <pre>
     *
     *   分析： 每个字符串的长度为2n；总共有2的2n次方个字符串生成，那些字符串是有效的？？？？
     *
     * </pre>
     */
    public static void generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
    }

    public static void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            System.out.println("----满足条件-------" + cur);
            return;
        }

        if (open < max) {
            backtrack(ans, cur + " (", open + 1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + " )", open, close + 1, max);
        }
    }


    public static void main(String args[]) {
        generateParenthesis(3);
    }
}
