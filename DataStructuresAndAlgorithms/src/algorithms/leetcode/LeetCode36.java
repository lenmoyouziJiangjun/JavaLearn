package algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 2020-01-20.
 * Description
 *
 * <pre>
 *
 *     判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode36 {

    /**
     * <pre>
     *   思路： 只是判断一个数独是否有效，
     *        我们可以行遍历，列遍历，如果行列有重复数字就不满足
     *
     *
     * </pre>
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) { //计算行
            Set<Character> lineSub = new HashSet<>();
            Set<Character> coluSub = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                //a[i][j] 和a[j][i]  中有相同元素 return；
                if (isNum(board[i][j]) && lineSub.contains(board[i][j])) {
                    return false;
                }
                if (isNum(board[i][j]) && coluSub.contains(board[j][i])) {
                    return false;
                }
                lineSub.add(board[i][j]);
                coluSub.add(board[j][i]);
            }
        }
        return true;
    }

    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }


    public void test() {

    }
}
