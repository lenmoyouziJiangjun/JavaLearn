package algorithms.leetcode;

import static algorithms.leetcode.LeetCodeTest.printArray;

/**
 * Version 1.0
 * Created by lll on 2020-01-15.
 * Description
 * <pre>
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode34 {


    /**
     * <pre>
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     *
     *
     * 分析： 时间复杂度为logN, 就是一个二分查找的变种.找到第一个和最后一个元素
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums != null && nums.length > 0) {
            result[0] = getLeftIndex(nums, 0, nums.length - 1, target);
            result[1] = getRightIndex(nums, 0, nums.length - 1, target);
        }
        printArray(result);
        return result;
    }


    public int getLeftIndex(int[] nums, int left, int right, int target) {
        int leftIndex = -1;
        while (left <= right) {
            int mid = ((left + right) >> 1);
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    leftIndex = mid;
                    return leftIndex;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return leftIndex;
    }


    public int getRightIndex(int[] nums, int left, int right, int target) {
        int rightIndex = -1;
        while (left <= right) {
            int mid = ((left + right) >> 1);
            if (nums[mid] == target) {
                if (mid == right || nums[mid + 1] != target) {
                    rightIndex = mid;
                    return rightIndex;
                } else {
                    left = mid + 1;
                }

            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rightIndex;

    }


    public void test() {
        int[] nums = new int[0];
        int[] nums2 = new int[]{5, 7, 7, 8, 8, 10};
        int[] nums3 = new int[]{2, 2};

        searchRange(nums, 1);
//
        searchRange(nums2, 8);

        searchRange(nums3, 2);
    }


}
