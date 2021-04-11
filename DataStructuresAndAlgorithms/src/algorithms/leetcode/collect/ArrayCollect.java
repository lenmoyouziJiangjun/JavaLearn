package algorithms.leetcode.collect;

import java.util.*;

/**
 * Version 1.0
 * Created by lll on 2020-05-06.
 * Description
 * <pre>
 *     数组相关算法汇总
 *
 *     1、数组是内存连续的
 *
 *
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ArrayCollect {

    /**
     * 给一个数组，数组包含正数和负数，从中任取一个连续子数组的和最大
     * 例如{1,2,-1,5,7}
     * 要求：时间复杂度为O(n)
     * <p>
     * 解题思路：如果前面子数组的和小于0了，就不要前面的子数组
     *
     * @param array
     */
    public static void getSubArrayMaxSum(int[] array, int len) {
        int subArrayMax = 0;
        int totalMax = 0;
        for (int i = 0; i < len; i++) { //
            subArrayMax += array[i];
            if (subArrayMax > totalMax) {
                totalMax = subArrayMax;
            } else {
                if (subArrayMax < 0) {
                    subArrayMax = 0;
                }
            }
        }

        System.out.print("the max result ===" + totalMax);
    }


    /**
     * 给定一个数组和值，要求，从数组中找找出两个数，使其和等于值
     * 示例数组{1,3,5,7} 值 4
     * 要求：时间复杂度不能超过O(N*N*N)
     * <p>
     * 解题思路：
     * 对数组进行排序，
     * 采用双指针的形式
     *
     * @param array
     * @param sum
     */
    public static void getNum(int[] array, int sum) {
        Arrays.sort(array);
        int len = array.length;
        int i = 0, j = len - 1;
        while (i < j) { //找到中间就停止
            int temp = array[i] + array[j];
            if (temp < sum) { //如果前后两个数之和小于给定的数，那么我们i++，前面加1
                i++;
            } else if (temp > sum) {//如果前后两个数之和小于给定的数，那么我们j--，后面减一
                j--;
            } else {
                System.out.println("找到满足元素" + array[i] + "   " + array[j]); //找到一组满足元素，
                i++; //继续下一步
                j--;
            }
        }
    }

    /**
     * 给定一个包含 n 个整数的数组 array 和num，判断 array 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 通上面的两数之和一样，我们先指定一个元素，然后变量剩下的有序数组
     *
     * @param array
     * @param num
     */
    public static void getNum3(int[] array, int num) {
        Arrays.sort(array);
        int j = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            int temp = num - array[i]; //临时元素
            int k = i + 1;
            while (k < j) {
                int t = array[k] + array[j];
                if (t < temp) {
                    k++;
                } else if (t > temp) {
                    j--;
                } else {
                    System.out.println("找到满足元素[ " + array[i] + " ,  " + array[k] + " , " + array[j] + "]"); //找到一组满足元素，
                    //不包含重复元素，就需要判断后面元素中是否包含这两个元素
                    while (i < j && array[i] == array[i + 1]) {
                        i++;
                    }
                    while (i < j && array[j] == array[j - 1]) {
                        j--;
                    }
                    i++; //继续下一步
                    j--;
                }
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int temp = 0 - nums[i];
            int k = i + 1, j = nums.length - 1;

            while (k < j) {
                int subT = nums[i] + nums[j];
                if (subT < temp) {
                    k++;
                } else if (subT > temp) {
                    j--;
                } else {
                    List<Integer> subStr = new ArrayList();
                    subStr.add(nums[i]);
                    subStr.add(nums[k]);
                    subStr.add(nums[j]);
                    result.add(subStr);
                    while (k < j && nums[k++] == nums[k]) {
                        k++;
                    }
                    while (k < j && nums[j--] == nums[j]) {
                        j--;
                    }
                    k++;
                    i--;
                }
            }
        }
        return result;
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
     * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。你可以假设 nums1 和 nums2 均不为空。
     * <p>
     * 示例
     * nums1 = [1, 3]
     * nums2 = [2]
     * 中位数是 2.0
     * <p>
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 中位数是 (2 + 3)/2 = 2.5
     *
     * <pre>
     *
     *    思路： 将两个有序数组合并成一个有序数组，然后根据数组长度，获取中间数字。
     *    关键点就在于：如何将两个有序数组合并成一个有序数组，时间复杂度为 O(log (m+n))
     *     通过两个for循环的话，就是O(m*n)
     *     //定义一个m+n数组，
     *     //将一个数组排序，时间复杂度为O(logN)有哪些算法？
     *
     * </pre>
     *
     * @param nums1
     * @param nums2
     */
    public static void getMidleNum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] totalArray = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                totalArray[k++] = nums1[i++];
            } else {
                totalArray[k++] = nums2[j++];
            }
        }
        while (i < m) {
            totalArray[k++] = nums1[i++];
        }

        while (j < n) {
            totalArray[k++] = nums1[j++];
        }
    }


    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * <pre>
     *    分析： 在遍历的时候，记录住上次最小值和最大收益，然后比较当前最小值和最大收益
     *
     * </pre>
     *
     * @param nums
     */
    public static void maxProfit(int nums[]) {
        int minPrice = nums[0];
        int maxRes = 0;
        for (int i = 1; i < nums.length; i++) {
            maxRes = Math.max(maxRes, nums[i] - minPrice);
            minPrice = Math.min(nums[i], minPrice);
        }

        System.out.println("the max resource == " + maxRes);
    }


    /**
     * 取出数组的重复元素，
     * <p>
     * 实现思路
     * 参考set的方式，定义一个Map，每个次从数组中添加元素的时候，判断元素的hash索引，是否已经添加
     *
     * @param array
     */
    public static void deleteRepeatNums(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        //实现方案一：
        Map maps = new TreeMap<Integer, Integer>();
        for (int i = 0, n = array.length; i < n; i++) {
            maps.put(array[i], i);
        }
        maps.keySet().stream().forEach(it -> System.out.println(it));
    }

    /**
     * 删除重复元素
     * 采用快慢指针，先对数组进行排序，如果数组的下一个元素和当前元素相等就跳过
     *
     * @param array
     */
    public static void deleteRepeatNums2(int[] array) {
        Arrays.sort(array);
        int j = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[j] != array[i]) {
                //数据添加到后面
//                System.out.println("j==="+j);
                j++;
                array[j] = array[i];
//                j++;
            }
        }
        int[] newArray = new int[j+1];
        System.arraycopy(array,0, newArray, 0, newArray.length);


        System.out.print("去重后的数组为:  ");
        for (int i = 0; i < newArray.length; i++) {
            System.out.print(newArray[i] + "  ");
        }
    }

    /**
     * 获取盛水最多的容器
     *
     * @param array
     */
    public static void getMaxWaterNums(int[] array) {

    }


    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, -4, 5, 7};
//        getSubArrayMaxSum(array, 5);

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        getNum3(nums, 0);

        int[] price = new int[]{7, 1, 5, 3, 6, 4};
        maxProfit(price);

        int[] nums2 = new int[]{1, 2, 3, 1, 2, 6};
        deleteRepeatNums2(nums2);
    }
}
