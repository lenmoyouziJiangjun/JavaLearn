package algorithms;

import java.util.Arrays;

/**
 * Version 1.0
 * Created by lll on 11/30/17.
 * Description 二分查找
 * copyright generalray4239@gmail.com
 */
public class BinarySearch {


    /**
     * 二分查找，从中间分两半查找。
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


}
