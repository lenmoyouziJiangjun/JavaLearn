package algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

import static algorithms.leetcode.LeetCodeTest.printList;

/**
 * Version 1.0
 * Created by lll on 2020-02-10.
 * Description
 *
 * <pre>
 *
 *     给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * </pre>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class LeetCode39 {

    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;
    int target;

    /**
     * <pre>
     *     解题思路： 动态规划钱币找零的变形
     *
     *     1、暴力解法
     *     2、回溯算法
     *     3、递归
     *
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            throw new RuntimeException("the candidates must is not empty or target can not equals 0");
        }
        candidates = nums;

        getNum2(target, 0, new ArrayList());
        return result;
    }

    /**
     * 暴力回溯解法： 出现重复数据
     * 回溯算法的优化： 剪枝，去掉重复的
     *
     * @param totalReward 剩下的数量
     * @param resultList
     */
    private void get(int totalReward, ArrayList<Integer> resultList) {
        if (totalReward == 0) { //如果剩下的数量为0，表示已经找到一组了
            printList(resultList);
            result.add(resultList);
            return;
        } else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0, len = candidates.length; i < len; i++) {
                ArrayList<Integer> newResult = (ArrayList<Integer>) resultList.clone();
                newResult.add(candidates[i]);
                get(totalReward - candidates[i], newResult);
            }
        }
    }

    /**
     * @param totalReward 剩余结果
     * @param start       迭代开始位置，这样解决了重复计算
     * @param resultList  临时数组
     */
    private void getNum2(int totalReward, int start, ArrayList<Integer> resultList) {
        if (totalReward == 0) { //如果剩下的数量为0，表示已经找到一组了
            printList(resultList);
            result.add(resultList);
            return;
        } else if (totalReward < 0) {
            return;
        } else {
            for (int i = start, len = candidates.length; i < len && totalReward - candidates[i] >= 0; i++) {
                ArrayList<Integer> newResult = (ArrayList<Integer>) resultList.clone();
                newResult.add(candidates[i]);
                getNum2(totalReward - candidates[i], i, newResult);
            }
        }
    }


    public void test() {
        int[] nums = new int[]{2, 3, 5}; //8
//        int[] nums = new int[]{2, 3, 6, 7}; //target 7
        combinationSum(nums, 8);
    }
}
