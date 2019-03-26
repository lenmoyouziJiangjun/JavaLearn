package algorithms.sort;

/**
 * Version 1.0
 * Created by lll on 2019/3/13.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SortUtils {

    /**
     * 冒泡排序：每一次循环，把最大一个找出来放到后面
     *
     * @param a
     * @param n 数组长度
     */
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true; //有数据交换
                }
            }
            if (!flag) {
                break; //没有数据交换，退出
            }
        }
    }

    /**
     * 插入排序:将元素插入到有序数组中
     *
     * @param a
     * @param n
     */
    public static void insertSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; ++i) {
            int value = a[i]; //获取到当前元素
            int j = i - 1;
            for (; j >= 0; --j) { //遍历之前有序数组，找到插入位置
                if (a[j] > value) {
                    a[j + 1] = a[j];//找到插入数据的索引，索引后面的元素后移
                } else {
                    break;
                }
            }
            a[j + 1] = value; //将当前元素插入到指定位置。
        }
    }

    /**
     * 选择排序
     * 选择排序和插入排序类似，分为已排序区间和未排序区间，
     * 但是选择排序每次会从未排序区间中找到最小元素，放在已排序区间的末尾
     *
     * @param a
     * @param n
     */
    public static void selectionSort(int[] a, int n) {
        int out, in, min;

        for (out = 0; out < n - 1; out++) {
            min = out;
            for (in = out + 1; in < n; in++) { // inner loop 从未排序中找到最小元素
                if (a[in] < a[min]) {
                    min = in;
                }
            }
            int temp = a[out];
            a[out] = a[min];
            a[min] = temp;
        }
    }


    public static void printArray(int[] a) {
        for (int i : a) {
            System.out.print(i + "  ");
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{9, 7, 8, 4, 6, 5, 3, 2, 0, 1, -1};
        insertSort(a, a.length);
        printArray(a);
    }

}
