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
            int mid = (low + high) >>> 1;
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

    private void test(){
        Integer[] testNums = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            System.out.println("num===" + num+"------i=="+i);
            siftUpUsingComparator(i, num, testNums, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 != null && null != o2) {
                        return o1.compareTo(o2);
                    }else{
                        return 0;
                    }
                }
            });
            PrintUtils.printArray(testNums,i);
        }
    }

    private void testPriorityQueue(){
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for(int i=0;i<10;i++){
            int num = random.nextInt(10);
            System.out.println("num===" + num+"------i=="+i);
            queue.put(num);
        }
    }


    public static void main(String args[]) {

    }


}
