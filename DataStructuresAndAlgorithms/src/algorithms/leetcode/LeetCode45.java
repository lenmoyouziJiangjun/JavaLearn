package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-03-06.
 * Description
 * <pre>
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例：
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode45 {

    /**
     * <pre>
     *   分析： 就是一个变形的最短路径算法
     *
     * </pre>
     *
     * @param array
     * @param startIndex
     * @return
     */
    public int jump(int[] array, int startIndex) {
        int minStep = 0;
        for (int i = 0; i < array.length; i++) {
            int step = array[i];
            for (int j = 0; j < step; j++) {

            }
        }

        return minStep;
    }

    public void test() {

    }

}
