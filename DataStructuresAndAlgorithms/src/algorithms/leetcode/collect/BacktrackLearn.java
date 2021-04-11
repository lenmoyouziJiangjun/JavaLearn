package algorithms.leetcode.collect;

/**
 * Version 1.0
 * Created by lll on 2019-12-14.
 * Description
 * <pre>
 *   回溯算法：
 *   回溯的处理思想，有点类似枚举搜索。我们枚举所有的解，找到满足期望的解。
 *   为了有规律地枚举所有可能的解，避免遗漏和重复，我们把问题求解的过程分为多个阶段。每个阶段，我们都会面对一个岔路口，我们先随意选一条路走，当发现这条路走不通的时候（不符合期望的解），就回退到上一个岔路口，另选一种走法继续走。
 *
 *   回溯算法常用的场景：
 *    深度优先搜索、八皇后、0-1 背包问题、图的着色、旅行商问题、数独、全排列、正则表达式匹配
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class BacktrackLearn {


  //全局或成员变量,下标表示行,值表示queen存储在哪一列
  //巧用数组下标
  static int[] result = new int[8];

  /**
   * 8皇后问题
   * 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
   * 八皇后问题就是期望找到所有满足这种要求的放棋子方式。
   *
   * @param row 表示那一行 一次为0，1，2，3，5，6，7，8(第八个时候表示已经处理完了)
   */
  public static void cal8Queens(int row) {
    if (row == 8) { // 8个位置都放好了，
      printQueens(result);
      return;
    }
    for (int column = 0; column < 8; ++column) { //每一行中有8种放法
      if (isOk(row, column)) {
        result[row] = column; //放置元素
        cal8Queens(row + 1); //继续下一行
      }
    }
  }

  /**
   * 判断row行column列放置是否合适。
   * <p>
   * 只需要判断，
   * 1、已经放过的行，行列对角线是否有值，
   * 2、已经放过的行、在该列上面是有值
   *
   * @param row
   * @param column
   * @return
   */
  private static boolean isOk(int row, int column) {
    int leftup = column - 1, rightup = column + 1; //对角线位置
    for (int i = row - 1; i >= 0; --i) { // 逐行往上考察每一行
      if (result[i] == column) { // 第i行的column列有棋子吗？
        return false;
      }
      if (leftup >= 0) {//考察左上对角线：第i行leftup列有棋子吗？
        if (result[i] == leftup) return false;
      }
      if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
        if (result[i] == rightup) {
          return false;
        }
      }
      --leftup; //上一个左对角线
      ++rightup; //上一个有对角线
    }
    return true;
  }

  private static void printQueens(int[] result) { //打印出一个二维矩阵
    for (int row = 0; row < 8; ++row) {
      for (int column = 0; column < 8; ++column) {
        if (result[row] == column) System.out.print("Q ");
        else System.out.print("* ");
      }
      System.out.println();
    }
    System.out.println();
  }


  /**
   * question 2
   * 0-1背包问题 （动态规划求解是最优的）
   * <p>
   * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
   * 我们现在期望选择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
   *
   * @param args
   */

  public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

  /**
   * @param i     i表示考察到哪个物品了
   * @param cw    cw表示当前已经装进去的物品的重量和
   * @param items 表示每个物品的重量
   * @param n     表示物品个数
   * @param w     背包重量
   */
  public void f(int i, int cw, int[] items, int n, int w) {
    if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
      if (cw > maxW) {
        maxW = cw;
      }
      return;
    }
    f(i + 1, cw, items, n, w);
    if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
      f(i + 1, cw + items[i], items, n, w);
    }
  }

  /**
   * 假设正则表达式中只包含“*”和“?”这两种通配符，并且对这两个通配符的语义稍微做些改变，其中，“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符
   */

  public static class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public Pattern(char[] pattern, int plen) {
      this.pattern = pattern;
      this.plen = plen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
      matched = false;
      rmatch(0, 0, text, tlen);
      return matched;
    }

    /**
     * @param ti   文本索引
     * @param pj   正则的索引
     * @param text 文本
     * @param tlen 文本长度
     */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
      if (matched) return; // 如果已经匹配了，就不要继续递归了
      if (pj == plen) { // 正则表达式到结尾了
        if (ti == tlen) matched = true; // 文本串也到结尾了
        return;
      }
      if (pattern[pj] == '*') { // *匹配任意个字符
        for (int k = 0; k <= tlen - ti; ++k) {
          rmatch(ti + k, pj + 1, text, tlen);
        }
      } else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
        rmatch(ti, pj + 1, text, tlen);
        rmatch(ti + 1, pj + 1, text, tlen);
      } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
        rmatch(ti + 1, pj + 1, text, tlen);
      }
    }
  }

  public static void main(String[] args) {
    cal8Queens(0);
  }

}
