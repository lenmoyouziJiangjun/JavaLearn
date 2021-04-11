package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-01-13.
 * Description
 *
 * <pre>
 *     假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode33 {

    /**
     * 示例
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     *
     *
     * <pre>
     *     时间复杂度为O(logN)的排序算法有：快速排序、堆排序
     *
     *     时间复杂度为0(logN)的查找，只能是二分查找；
     *
     *     数组是内部是两个升序排列的子数组，
     *
     *     采用二分查找的前提是，数组是一个有序数组，因此我们需要找到数组中的分割点。
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int minValue = 0;
        //1、第一步找到最小值索引
        int minValueIndex = getMinInfo(nums, 0, nums.length - 1);
        minValue = nums[minValueIndex];

        if (target == minValue) {
            System.out.println("the result 0===" + minValueIndex);
            return minValueIndex;
        }
        int result = -1;
        if (minValueIndex == 0) {
            result = binarySearch(nums, 0, nums.length - 1, target);
            System.out.println("the minValueIndex 1===" + minValueIndex + "---result=====" + result);
        } else {
            result = binarySearch(nums, 0, minValueIndex - 1, target);
            System.out.println("the minValueIndex 2===" + minValueIndex + "---result===" + result);
            if (result == -1) {//左边没有找到，找右边
                result = binarySearch(nums, minValueIndex + 1, nums.length - 1, target);
            }
        }

        System.out.println("the result ===" + result);
        return result;
    }

    public int getMinInfo(int[] nums, int left, int right) {

        if (nums[left] < nums[right]) { //[left, right]区间是有序的
            return 0;
        }

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) { //交接点
                return mid + 1;
            } else if ((mid - 1) >= 0 && nums[mid - 1] > nums[mid]) {
                return mid;
            } else {
                if (nums[mid] < nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return 0;
    }


    public int binarySearch(int num[], int low, int high, int key) {

        while (low <= high) {
            int mid = (low + high) >> 1;
            long midVal = num[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -1;
    }


    public void test() {
//        [3,4,5,6,1,2]
//        2
        int[] nums = new int[]{3, 4, 5, 6, 1, 2};
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
//        int[] nums = new int[]{3, 1};


        search(nums, 2);

    }
}
