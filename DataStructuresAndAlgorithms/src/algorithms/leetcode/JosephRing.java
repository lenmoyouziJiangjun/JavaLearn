package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2019-07-15.
 * Description 约瑟夫环
 * <p>
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class JosephRing {

  /**
   * 问题描述：编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3…这样依次报），
   * 数到 m 的 士兵会被杀死出列，之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。
   */
  public static int demo1(int n, int m) {
    if (n == 1) return n;
    return (demo1(n - 1, m) + m - 1) % n + 1;

  }

}
