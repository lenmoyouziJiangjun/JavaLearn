package algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-04-10.
 * Description
 * copyright generalray4239@gmail.com
 */
public class LeetCode54 {

    /**
     * 将一个二维数组顺时针输出
     *
     * @param matrix
     * @return
     */
    private static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        List<Integer> result = new ArrayList<>();

        int row = matrix.length - 1;
        int col = matrix[0].length - 1;

        int rowIndex = 0, colIndex = 0;

        System.out.print("[  ");
        while (rowIndex <= row && colIndex <= col) {
            //输出，上右"-|"部分
            for (int i = colIndex; i <= col; i++) { //遍历行上边部分
                System.out.print(matrix[rowIndex][i] + "  ");
                result.add(matrix[rowIndex][i]);
            }
            for (int j = rowIndex + 1; j <= row; j++) { //遍历列 右边部分
                System.out.print(matrix[j][col] + "  ");
                result.add(matrix[j][col]);
            }
            //输出底左部分"|-"
            if (colIndex < col && rowIndex < row) { //旋转
                for (int i = col - 1; i >= colIndex; i--) { //遍历行，底部行
                    System.out.print(matrix[row][i] + "  ");
                    result.add(matrix[row][i]);
                }

                for (int j = row - 1; j > rowIndex; j--) { //遍历列，左边
                    System.out.print(matrix[j][colIndex] + "  ");
                    result.add(matrix[j][colIndex]);
                }

            }

            //遍历完，行列数减1；索引+1。表示进入下一圈
            rowIndex++;
            row--;

            colIndex++;
            col--;
        }

        System.out.print("]");
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 0, 10, 11},
                {12, 13, 14, 15}

        };

        int[][] matrix2 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        spiralOrder(matrix);
    }
}
