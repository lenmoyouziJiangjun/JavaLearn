package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-03-30.
 * Description
 *
 * <pre>
 *     给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * </pre>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class LeetCode47 {


    /**
     * 将二维矩阵顺时针旋转90度
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     *
     * <pre>
     *
     *     分析： A[m][n]
     *     1、原地： 意味着只能在现有数组上操作，不能创建temp数组。 那么数组一定是n*n的矩阵。
     *             否则不能原地
     *
     *
     *     2、顺时针旋转： 第0行   ->  第N列
     *                  第1行   ->  第N-1列
     *                  ……
     *                  第m行   -> 第0列
     *
     *                  第0列 -> 第0行
     *                  第1列 -> 第1行
     *                  ……
     *                  第n列 -> 第N行
     *
     *
     * </pre>
     *
     * @param matrix
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            throw new RuntimeException("the params is null");
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows]; //定义了一个数组，不符合原地转换的要求
        System.out.println("the rows == " + rows + "  cols===" + cols);
        for (int i = 0; i < rows; i++) { //row

            for (int j = 0; j < cols; j++) {//cols
                int temp = matrix[i][j];
                matrix[i][j] = result[j][rows - 1 - i];
                result[j][rows - 1 - i] = temp;

                //交换的终止条件？
//                result[j][rows - 1 - i] = matrix[i][j];
            }
        }
        System.out.println("=============switch location================");
        printArray(result);
    }

    /**
     * 正解：
     * 矩阵的旋转步骤=> 第一步，矩阵转置。基于中线翻转
     * => 第二步： 每一行的数据交换
     *
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        if (matrix == null) {
            throw new RuntimeException("the params is null");
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int centerCols = cols >> 1;
        System.out.println("the rows == " + rows + "  cols===" + cols);

        //step1： 转置矩阵
        for (int i = 0; i < rows; i++) { //row
            for (int j = 0; j < i; j++) {//switch low and high depart
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        System.out.println("=============switch location================");
        printArray(matrix);

        //step2：
        for (int i = 0; i < rows; i++) { //row
            for (int j = 0; j < centerCols; j++) {//switch columns data
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols - 1 - j];
                matrix[i][cols - 1 - j] = temp;
            }
        }


        System.out.println("=============switch location================");
        printArray(matrix);
    }

    private static void printArray(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        System.out.println(" { ");
        for (int i = 0; i < rows; i++) {
            System.out.print(" { ");
            for (int j = 0; j < cols; j++) {
                if (j == cols - 1) {
                    System.out.print(array[i][j]);
                } else {
                    System.out.print(array[i][j] + " , ");
                }

            }
            System.out.print(" }, ");
            System.out.println("");
        }
        System.out.println(" } ");
    }


    public static void main(String[] args) {
        int testArray[][] = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},

        };

        int testArray2[][] = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 0},

        };

        int testArray3[][] = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},

        };

        printArray(testArray3);
        rotate2(testArray3);

//        System.out.println("=============switch location================");
//        printArray(testArray3);
    }
}
