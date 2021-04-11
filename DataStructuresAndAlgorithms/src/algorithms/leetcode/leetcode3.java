package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2019-12-14.
 * Description
 * <pre>
 *   分治算法
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class leetcode3 {


  /**
   * 时间复杂度为O(n平方)
   *
   * @param intArray
   */
  public static void getSortedSubArray(int[] intArray) {
    int testNum = 0;
    int n = intArray.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (intArray[i] > intArray[j]) {
          System.out.print("[" + intArray[i] + " , " + intArray[j] + "]");
          ++testNum;
        }
      }
    }
    System.out.println("double for result num ===="+testNum);
  }

  private static int num = 0;

  public static void getSortedSubArrayTest2(int[] intArray) {
     num = 0;
    mergeSortCounting(intArray, 0, intArray.length -1);

    System.out.println("the num ==" +num);
  }

  /**
   * @param a
   * @param p
   * @param r
   * @return
   */
  public static void mergeSortCounting(int[] a, int p, int r) {
    if (p >= r) return;
    int q = (p + r) >> 1;
    mergeSortCounting(a, p, q);
    mergeSortCounting(a, q + 1, r);
    merge(a, p, q, r);
  }

  /**
   * 思想就是归并排序，交换一次记录一次
   * @param a 数组
   * @param p 起点
   * @param q 中点
   * @param r 终点
   */
  public static void merge(int[] a, int p, int q, int r) {
    int i = p, j = q + 1, k = 0;
    int[] tmp = new int[r - p + 1];
    while (i <= q && j <= r) {
      if (a[i] <= a[j]) {
        tmp[k++] = a[i++];
      } else {
        num += (q - i + 1); //统计p-q之间，比a[j]大的元素个数
        tmp[k++] = a[j++];
      }
    }
    while (i <= q) {
      tmp[k++] = a[i++];
    }
    while (j <= r) {
      tmp[k++] = a[j++];
    }
    for (i = 0; i <= r - p; ++i) { // 从tmp拷贝回a
      a[p + i] = tmp[i];
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("the result ==" + ((2 + 4) >> 1));
    int[] array = new int[]{2,4,3,1,5,6};
    getSortedSubArray(array);
    getSortedSubArrayTest2(array);

  }

}
