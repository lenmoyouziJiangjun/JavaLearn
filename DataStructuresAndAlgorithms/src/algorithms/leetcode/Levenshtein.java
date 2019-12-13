package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 11/30/17.
 * Description 编辑距离
 * <p>
 * 我们来看一个实际应用。现代搜索技术的发展很多以提供优质、高效的服务作为目标。比如说：baidu、google、sousou等知名全文搜索系统。当我们输入一个错误的query="Jave" 的时候，返回中有大量包含正确的拼写 "Java"的网页。当然这里面用到的技术绝对不会是我们今天讲的怎么简单。但我想说的是：字符串的相似度计算也是做到这一点的方法之一。
 * <p>
 * <p>
 * 字符串编辑距离： 是一种字符串之间相似度计算的方法。给定两个字符串S、T，将S转换成T所需要的删除，插入，替换操作的数量就叫做S到T的编辑路径。而最短的编辑路径就叫做字符串S和T的编辑距离。
 * <p>
 * 举个例子：S=“eeba”   T="abac"   我们可以按照这样的步骤转变：(1) 将S中的第一个e变成a;(2) 删除S中的第二个e;(3)在S中最后添加一个c; 那么S到T的编辑路径就等于3。当然，这种变换并不是唯一的，但如果3是所有变换中最小值的话。那么我们就可以说S和T的编辑距离等于3了。
 * <p>
 * <p>
 * 动态规划解决编辑距离
 * 动态规划(dynamic programming)是一种解决复杂问题最优解的策略。它的基本思路就是：将一个复杂的最优解问题分解成一系列较为简单的最优解问题，再将较为简单的的最优解问题进一步分解，直到可以一眼看出最优解为止。
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class Levenshtein {

  public static void getResult(String A, String B) {
    if (A.equals(B)) {
      System.out.println(0);
      return;
    }
    //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
    int[][] dp = new int[A.length() + 1][B.length() + 1];
    for (int i = 1; i <= A.length(); i++) {
      dp[i][0] = i;
    }
    for (int j = 1; j <= B.length(); j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= A.length(); i++) {
      for (int j = 1; j <= B.length(); j++) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j] + 1,
                  Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
        }
      }
    }
    System.out.println(dp[A.length()][B.length()]);
    return;
  }

  /**
   * 编辑距离的状态转移方程为：d[i,j] = min(d[i-1,j]+1,d[i,j+1]+1,d[i-1,j-1]+r[i,j])
   *
   * @param a
   * @param b
   */
  public static void getStrDistance(String a, String b) {
    if (a == null || b == null) {
      return;
    }
    //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
    int[][] dp = new int[a.length() + 1][b.length() + 1];
    for (int i = 1; i <= a.length(); i++) {
      dp[i][0] = i;
    }
    for (int j = 1; j <= b.length(); j++) {
      dp[0][j] = j;
    }

    for (int i = 0; i < a.length(); i++) {
      for (int j = 0; j < b.length(); j++) {
        int r = 0;
        if (a.charAt(i) != b.charAt(j)) {
          r = 1;
        }
        int first_append = dp[i][j + 1] + 1;
        int second_append = dp[i + 1][j] + 1;
        int replace = dp[i][j] + r;
        int min = Math.min(first_append, second_append);
        min = Math.min(min, replace);
        dp[i + 1][j + 1] = min;
      }
    }
    System.out.println(dp[a.length()][b.length()]);
  }


  public static void main(String[] args) {
    Levenshtein.getResult("mouse", "moaaase");
    System.out.println("--------------------------");
    Levenshtein.getStrDistance("mous", "oaasemu");
  }

}
