package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2019-12-04.
 * Description
 * copyright generalray4239@gmail.com
 */
public class leetcode1 {

  /**
   * question:
   * 1 个细胞的生命周期是 3 小时，1 小时分裂一次。求 n 小时后，容器内有多少细胞
   *
   * 假设分裂后再死亡
   *
   * @param initNums 容器内初始细胞数量
   * @param hour 时间
   * @return
   */
  public static int  test1(int initNums, int hour) {
    if (hour == 0) { //1
      return initNums;
    } else if (hour == 1) { // 2
      return initNums * 2;
    } else if (hour == 2) { //3
      return initNums * 4; //  2*f(n-1) 上一个结果的两倍，
    } else if (hour == 3) { // 4
      return initNums * 8 - initNums; // 2*f(n-1) - f(n-4)
    } else {
      return 2 * test1(initNums, hour - 1) - test1(initNums, hour - 4);
    }
  }


  public static void main(String[] args) {
    System.out.println("1.3 hours ===" + test1(1, 3));
    System.out.println("1.4 hours ===" + test1(1, 4));
    System.out.println("2.2 hours ===" + test1(2, 2));
    System.out.println("2.4 hours ===" + test1(2, 4));
  }
}
