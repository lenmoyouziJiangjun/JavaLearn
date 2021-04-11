package algorithms.leetcode;

import java.util.Arrays;

/**
 * Version 1.0
 * Created by lll on 2020-02-17.
 * Description
 * <pre>
 *
 *     给定一个未排序的整数数组，找出其中没有出现的最小的正整数
 *
 *     示例：输入[1,2,0] 输出3
 *             [3,4,-1,1] 输出2
 *             [7,8,9] 输出1
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode41 {


    int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 1;
        }
        if (nums.length == 1) {
            if (nums[0] != 1) {
                return 1;
            } else {
                return 2;
            }
        }

        Arrays.sort(nums);
        if (nums[0] > 1) {
            return 1;
        }
        int result = -1;
        for (int i = 0, n = nums.length - 1; i < n; i++) {
            if (nums[i + 1] - nums[i] > 1) {
                if (nums[i + 1] > 1 && nums[i] < 0) {
                    result = 1;
                    break;
                }
                if (nums[i + 1] < 0) {
                    continue;
                }
                if (nums[i] >= 0) {
                    result = nums[i] + 1;
                    break;
                }
            }
        }
        if (result == -1 && nums[nums.length - 1] > 0) {
            result = nums[nums.length - 1] + 1;
        } else if (result == -1 && nums[nums.length - 1] <= 0) {
            result = 1;
        }
        return result;
    }

    int firstMissingPositive2(int nums[]) {
        int n = nums.length;

        // 基本情况
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        if (contains == 0)
            return 1;

        // nums = [1]
        if (n == 1)
            return 2;

        // 用 1 替换负数，0，
        // 和大于 n 的数
        // 在转换以后，nums 只会包含
        // 正数
        for (int i = 0; i < n; i++) {
            if ((nums[i] <= 0) || (nums[i] > n)) {
                nums[i] = 1;
            }
        }
        // 使用索引和数字符号作为检查器
        // 例如，如果 nums[1] 是负数表示在数组中出现了数字 `1`
        // 如果 nums[2] 是正数 表示数字 2 没有出现
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // 如果发现了一个数字 a - 改变第 a 个元素的符号
            // 注意重复元素只需操作一次
            if (a == n)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[a] = -Math.abs(nums[a]);
        }

        // 现在第一个正数的下标
        // 就是第一个缺失的数
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }


    public void test() {
//        int[] nums = new int[]{1, 2, 0};
//        int[] nums = new int[]{3,4,-1,1};
//        int[] nums = new int[]{7, 8, 9, 11, 12};
        int[] nums = new int[]{-1, 4, 2, 1, 9, 10};

        long beforeTime = System.currentTimeMillis();
        int result = firstMissingPositive2(nums);

        System.out.println("run time == " + (System.currentTimeMillis() - beforeTime) + "result == " + result);
    }
}
