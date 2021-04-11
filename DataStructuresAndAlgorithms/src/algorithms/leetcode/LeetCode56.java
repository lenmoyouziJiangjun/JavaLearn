package algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-04-29.
 * Description
 * <pre>
 *     给出一个区间的集合，请合并所有重叠的区间。
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode56 {

    /**
     * <pre>
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     *
     * </pre>
     *
     * <pre>
     *     1、子元素是无序的，
     *
     *
     * </pre>
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int row = intervals.length;
        int cMax = -1;
        int cMin = -1;
        List<Integer> nums = new ArrayList<>(row); //记录已经操作过的数据
        for (int i = 0; i < row; i++) {
            if (nums.contains(i)) {
                continue;
            }
            if (cMin > intervals[i][0] || cMax < intervals[i][1]) { //当前区间和上一个区间不相交
                cMax = intervals[i][1];
                cMin = intervals[i][0];
                for (int j = i + 1; j < row; j++) {
                    if (nums.contains(j)) {
                        continue;
                    }
                    int nMax = intervals[j][1];
                    int nMin = intervals[j][0];
                    if (cMin > nMax || cMax < nMin) {
                        //不想交
                        continue;
                    } else {//相交，生成一个新区间
                        int[] newArr = new int[2];
                        cMin = newArr[0] = Math.min(cMin, nMin);
                        cMax = newArr[1] = Math.max(cMax, nMax);
                        nums.add(j);
                    }
                }
                int[] newArr = new int[2];
                newArr[0] = cMin;
                newArr[1] = cMax;
                System.out.println("[ " + cMin + " , " + cMax + " ]");
            }
        }
        return null;
    }

    /**
     * 实现方案二：
     * 1、
     *
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);

    }

    private void test() {
        int[][] intervals = new int[8][];
        intervals[0] = new int[]{1, 9};
        intervals[1] = new int[]{2, 5};
        intervals[2] = new int[]{19, 20};
        intervals[3] = new int[]{10, 11};
        intervals[4] = new int[]{12, 20};
        intervals[5] = new int[]{0, 3};
        intervals[6] = new int[]{0, 1};
        intervals[7] = new int[]{0, 2};
        merge(intervals);
    }


    public static void main(String[] args) {
        LeetCode56 code56 = new LeetCode56();
        code56.test();
    }

}
