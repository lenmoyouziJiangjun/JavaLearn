package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-01-16.
 * Description
 * <pre>
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode35 {


    /**
     * <pre>
     *     思路：
     *      1、采用折半查找，如果找到就返回索引，如果找不到就返回插入位置
     *      2、采用HashMap的思路，构造hash，
     *
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            result = 0;
        }
        result = getIndex(nums, 0, nums.length - 1, target);
        System.out.println("the result ===" + result);
        return result;
    }

    public int getIndex(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (mid == right || nums[mid + 1] > target) {
                    return mid + 1;
                }
                left = mid + 1;
            } else if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return 0;
    }

    /**
     * 示例 1:
     * <p>
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * <p>
     * 示例 4:
     * <p>
     * 输入: [1,3,5,6], 0
     * 输出: 0
     */
    public void test() {
        int[] nums = new int[]{1, 3, 5, 6};

        searchInsert(nums, 7);
    }

}
