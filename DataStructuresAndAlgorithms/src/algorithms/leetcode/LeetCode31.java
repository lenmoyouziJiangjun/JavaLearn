package algorithms.leetcode;

import java.util.Arrays;

/**
 * Version 1.0
 * Created by lll on 2020-01-08.
 * Description
 *
 * <pre>
 *     实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 *     如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 *     必须原地修改，只允许使用额外常数空间。
 *
 *     以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 *     1,2,3 → 1,3,2
 *     3,2,1 → 1,2,3
 *     1,1,5 → 1,5,1
 *
 *
 * </pre>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class LeetCode31 {

    /**
     * <pre>
     *    思路： 计算所有序列，按照顺序存储到map中？不行，需要原地修改，空间复杂度为O(1)
     *
     *     冒泡排序？ 倒序，计算下一个序列
     *
     *
     *
     * </pre>
     *
     * @param nums
     */
    private void nextPermutation(int[] nums) {
        boolean isFind = false;
        for (int i = nums.length - 1; i > 1; i--) {
            for (int j = nums.length - 2; j > 0; j--) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    LeetCodeTest.printArray(nums);
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }

        if (!isFind) {
            //没有找到
            System.out.println("==========");
            LeetCodeTest.printArray(nums);
            //倒序遍历，数组交换顺序
            int i = 0, j = nums.length - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
            LeetCodeTest.printArray(nums);
        }
    }


    /**
     *
     *
     *
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public void test() {
//        int[] array = new int[]{1, 2, 3};
        int[] array2 = new int[]{3, 2, 1};
//        int[] array3 = new int[]{4, 1, 3, 2};
//        nextPermutation(array);
        nextPermutation(array2);
//        nextPermutation(array3);
    }
}
