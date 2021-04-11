package algorithms;

import algorithms.utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Version 1.0
 * Created by lll on 11/30/17.
 * Description 二分查找
 * copyright generalray4239@gmail.com
 */
public class BinarySearch {


    /**
     * 二分查找，从中间分两半查找。
     *
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private static int binarySearchLong(long[] a, int fromIndex, int toIndex,
                                        long key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }


    /**
     * 将一个元素添加到一个有序集合中
     *
     * @param k
     * @param x
     * @param array
     * @param cmp
     * @param <T>
     */
    public static <T> void siftUpUsingComparator(int k, T x, Object[] array, Comparator<? super T> cmp) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (cmp.compare(x, (T) e) >= 0) {
                break;
            }
            array[k] = e;
            k = parent;
        }
        array[k] = x;
    }

    private void test() {
        Integer[] testNums = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            System.out.println("num===" + num + "------i==" + i);
            siftUpUsingComparator(i, num, testNums, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 != null && null != o2) {
                        return o1.compareTo(o2);
                    } else {
                        return 0;
                    }
                }
            });
            PrintUtils.printArray(testNums, i);
        }
    }

    private void testPriorityQueue() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            System.out.println("num===" + num + "------i==" + i);
            queue.put(num);
        }
    }


    // 二分查找的递归实现
    public int bsearch(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    /**
     * @param a     数组
     * @param low
     * @param high
     * @param value 要查找的值
     * @return
     */
    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }


    /**
     * 二分查找的变形一：查找第一个等于给定元素的索引
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //a[mid] == value 的时候我们需要做判断，
                //如果mid ==0了，数组第一个元素，那么肯定是的
                //如果a[mid-1]!= value 根据数组是有序数组，所以mid也是我们需要的
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找变形二
     * 查找最后一个值等于给定值的元素
     * 同上面的第一个元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //a[mid] == value 的时候我们需要做判断，
                //如果mid ==0了，数组第一个元素，那么肯定是的
                //如果a[mid+1]!= value 根据数组是有序数组，所以mid也是我们需要的
                if ((mid == high) || (a[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找变形三：查找第一个大于等于给定值的元素
     * 我们先找到第一个元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */

    public int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch5(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 有序数组，从某个点旋转后，分成两个递增子序列组成的数组，查找数组中最小值的索引
     *
     * @param nums
     * @param n    sh
     * @return
     */
    public int bsearch5(int[] nums, int n) {

        int left = 0;
        int right = n - 1;
        if (nums[left] < nums[right]) {
            return 0;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) { //交接点
                return mid + 1;
            } else if (nums[mid - 1] > nums[mid]) {
                return mid;
            } else {
                if (nums[mid] > nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return 0;
    }

    public void testBsearch5() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int index = bsearch5(nums, nums.length);
        System.out.println("the min index" + index + " min value ===" + nums[index]);
    }

    public static void main(String args[]) {
        BinarySearch bs = new BinarySearch();
        bs.testBsearch5();
    }
}
