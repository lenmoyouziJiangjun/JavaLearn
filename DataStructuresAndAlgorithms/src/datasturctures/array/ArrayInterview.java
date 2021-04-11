package datasturctures.array;

/**
 * Version 1.0
 * Created by lll on 2019-12-16.
 * Description
 * <pre>
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ArrayInterview {


  /**
   * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
   * <p>
   * 例如，数组为[4,3,1,5,4,3,7,5]，窗口大小为5时:
   * <p>
   * [4 3 1 5 4] 3 7 5 　max = 5
   * <p>
   * 4 [3 1 5 4 3] 7 5 　max = 5
   * <p>
   * 4 3 [1 5 4 3 7] 5 　max = 7
   * <p>
   * 4 3 1 [5 4 3 7 5]  　max = 7
   * <p>
   * 即窗口最大值数组为 result = {5, 5,7,7}
   */
  public static void test1(int[] array, int windowLength) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int len2 = i + windowLength;
      if (len2 > len) {
        len2 = len;
      }
      int max = array[i];
      for (int j = 0; j < len2; j++) {
        if (max < array[j]) {
          max = array[j];
        }
      }
      System.out.println("  " + max);
    }
  }


  public static void main(String[] args) {

  }
}
