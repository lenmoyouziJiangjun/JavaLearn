package algorithms.leetcode;

import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-04-03.
 * Description
 * <pre>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode51 {

    /**
     * analyse：
     * <p>
     * 皇后问题：是一个经典的回溯算法
     * <p>
     * 依次跟每一行分配皇后
     *
     * @param n
     * @return
     */
    int[] queens;

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            throw new RuntimeException("param error");
        }

        //定义一个一维数组，索引表示每行，值表示那一列放置queue
        //我们也可以定义一个二维数组来存储，通过一维数组索引来优化
        queens = new int[n];

        calQueues(0, n);
        return null;
    }


    public void calQueues(int row, int n) {
        if (row == n) {
            //放置成功
            //打印数据
            printQueue(n);
            return;
        }

        for (int col = 0; col < n; col++) {//每一行有N种放法
            if (isOk(row, col, n)) {//(row,col) 这个位置可以放置
                queens[row] = col;//存储数据
                calQueues(row + 1, n);//继续下一行
            }
        }
    }

    /**
     * 判断四个方向上面是有已经有数据了
     */
    private boolean isOk(int row, int column, int len) {
        int leftup = column - 1, rightUp = column + 1;
        for (int i = row - 1; i >= 0; --i) { //逐个考察上一行
            if (queens[i] == column) { //上一行的值为column，表示上一行的column列已经放置了 已经放置了
                return false;
            }
            if (leftup >= 0) { //左对角线，
                if (queens[i] == leftup) {//做对角上面有值了，放置了
                    return false;
                }

            }

            if (rightUp < len) { //判断右对角线
                if (queens[i] == rightUp) {//右对角线上面已经有值了
                    return false;
                }
            }

            --leftup;
            ++rightUp;
        }
        return true;
    }

    private void printQueue(int n) {
        System.out.println("-----------------------");
        System.out.print("{ ");
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.print("{ ");
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }

            }
            System.out.print("  }");
            System.out.println();
        }
        System.out.print("}");
        System.out.println();
    }


    public static void main(String[] args) {
        LeetCode51 code51 = new LeetCode51();
        code51.solveNQueens(8);
    }
}
