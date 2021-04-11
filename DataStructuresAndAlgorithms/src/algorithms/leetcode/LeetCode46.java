package algorithms.leetcode;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static algorithms.leetcode.LeetCodeTest.*;

/**
 * Version 1.0
 * Created by lll on 2020-03-06.
 * Description
 * <pre>
 *
 *   给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode46 {


    /**
     * 输入[1,2,3]
     *
     *
     * <pre>
     *
     *     采用for嵌套 暴力解法，再优化
     *
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            throw new RuntimeException("param error");
        }
        if (nums.length == 1) {
            List<Integer> item = new ArrayList<>();
            item.add(nums[0]);
            result.add(item);
            return result;

        }

        List<Integer> items;
        for (int i = 0, n = nums.length; i < n; i++) {
            items = new ArrayList<>();
            items.add(nums[i]);
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.println("the second  " + j + " num ==" + nums[j]);
                    items.add(nums[j]);
                    for (int k = 0; k < n; k++) {
                        if (k != j && k != i) {
                            System.out.println("the third " + k + "  num ==" + nums[k]);
                            items.add(nums[k]);
                            for (int x = 0; x < n; x++) {
                                if (x != i && x != j && x != k) {
                                    items.add(nums[x]);
                                    printList(items);
                                    result.add(items);
                                    items = new ArrayList<>();
                                    items.add(nums[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 采用递归实现
     */
    private void lean(int index, int[] array) {
        System.out.println("index === " + index);
        if (index >= array.length) {
            return;
        }
//        printArray(array);
        for (int i = index; i < array.length; i++) {
            if (i != index) {
                int[] newArray = Arrays.copyOfRange(array, 0, array.length);
                int num = newArray[index];
                newArray[index] = newArray[i];
                newArray[i] = num;
                List<Integer> subList = arrayToList(newArray);
                printList(subList);
                lean(index + 1, array);
            }
        }
    }


    //***************************正解************************************************

    /**
     * @param nums 是一个素组元素不重复的全排列
     * @return
     */
    public List<List<Integer>> permute(int nums[]) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> numList = arrayToList(nums);

        //没有重复元素的排列
//        backtrackNoRepeatElement(numList.size(), numList, result, 0);

        backtrackRepeatElement(numList.size(), numList, result, 0);
        return result;
    }

    /**
     * <pre>
     *
     *     分析：排列组合的思路都是，后面位置元素和当前位置元素进行交换
     *
     *
     *     思路： 定义一个标记，标记后面的元素一次和他交换位置。 然后迭代标记到数组末尾
     *
     *
     *
     *
     * </pre>
     *
     * @param len
     * @param numList
     * @param result
     * @param index   标记
     */
    private void backtrackNoRepeatElement(int len, List<Integer> numList, List<List<Integer>> result, int index) {
        if (index == len) {
            printList(numList);
            result.add(numList);
            System.out.println("the result size == " + result.size());
        }
        for (int i = index; i < len; i++) {//让index 后面所有的数据和他进行交换
            Collections.swap(numList, index, i); //交换后生成一个新数组，

            backtrackNoRepeatElement(len, numList, result, index + 1); //新数组也递归

            Collections.swap(numList, index, i); //满足条件后，将数组回复，迭代下一个索引
        }
    }

    /**
     * 有重复元素的组合
     *
     * <pre>
     *     分析：
     *       基于交换原理，如果交换元素相等，就跳过
     *
     * </pre>
     *
     * @param len
     * @param numList
     * @param result
     * @param index
     */

    private void backtrackRepeatElement(int len, List<Integer> numList, List<List<Integer>> result, int index) {
        if (index == len) {
            printList(numList);
            result.add(new ArrayList<>(numList));
            System.out.println("the result size == " + result.size());
        }


        for (int i = index; i < len; i++) {//让index 后面所有的数据和他进行交换
            if (index == i || numList.get(index) != numList.get(i)) { //元素不相同才交换  如果i==index 表示第一个，输出

                Collections.swap(numList, index, i); //交换后生成一个新数组，

                backtrackRepeatElement(len, numList, result, index + 1); //新数组也递归

                Collections.swap(numList, index, i); //满足条件后，将数组回复，迭代下一个索引
            } else {
                continue;
            }
        }

    }


    public void test() {

        int[] nums = new int[]{ 1, 1, 2, 2};
        permute(nums);
//        lean(0, nums);
    }

}
