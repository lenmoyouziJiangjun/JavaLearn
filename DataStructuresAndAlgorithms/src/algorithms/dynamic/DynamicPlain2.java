package algorithms.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 12/1/17.
 * Description
 * 动态规划：
 *   动态规划比较适合用来求解最优问题，比如求最大值、最小值等等
 *   动态规划，无非就是利用历史记录，来避免我们的重复计算。而这些历史记录，我们得需要一些变量来保存，一般是用一维数组或者二维数组来保存
 *
 *   动态规范常见的范围在：
 *    1、矩阵
 *    2、序列化
 *    3、字符串
 *      你可以先假设状态 dp[i][j] 就是子问题 str1(0...i) str2(0...j)  的状态。拆解问题主要思考 dp[i][j] 和子问题的状态 dp[i - 1][j]，dp[i - 1][j] 以及 dp[i - 1][j - 1] 的联系，因为字符串会存在空串的情况，所以动态规划状态数组往往会多开一格。
 *      1、常见的最长公共子序列
 *
 *
 * <p>
 * copyright generalray4239@gmail.com
 */
public class DynamicPlain2 {

  /**
   * question 1
   *  给定数组arr，arr中的所有的值都为正数且不重复。
   *  每个值代表一种面值的货币，每种面值的货币，可以使用任意张。
   *  再给一个整数aim代表要找的钱数，求还钱的方法
   */


  /**
   * 暴力搜索方法：
   * 1、示例arr={5，10，25，1}，arr=1000;
   * <p>
   * 1、用0张5元的货币，{10，25，1}组成的剩下的1000， 最终的方法数记为     res1；
   * 2、用1张5元的货币，{10，25，1}组成的剩下的995， 最终的方法数记为     res2；
   * 3、。。。。。。。。
   * 201、用200张5元的货币，{10，25，1}组成的剩下的1000， 最终的方法数记为     res201；
   * <p>
   * <p>
   * <p>
   * 如果用arr[index....n-1]这些面值的钱组成aim，返回总的方法数
   *
   * @param arr
   * @param index
   * @param aim
   * @return
   */
  public int question1(int[] arr, int index, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    return process1(arr, index, aim);
  }

  public int process1(int[] arr, int index, int aim) {
    int res = 0;
    if (index == arr.length) {
      res = 1;
    } else {
      for (int i = 0; arr[index] * i < aim; i++) {
        res += process1(arr, index + 1, aim - arr[index] * i);
      }
    }

    return res;
  }

  /**
   * 记忆搜索方法
   * <p>
   * 暴力搜索方法导致了很多重复计算。
   * 重复计算的原因是，结算结果没有保存下来。记忆搜索方法，就是将计算结果用一个数组保存下来
   * <p>
   * 时间复杂度：O(N*aim的评分)
   */

  public int coins2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    //定义一个二维数组，存储每次计算的数据结果，
    int[][] map = new int[arr.length + 1][aim + 1];
    return process2(arr, 0, aim, map);
  }

  public int process2(int[] arr, int index, int aim, int[][] map) {
    int res = 0;
    if (index == arr.length) {
      res = aim == 0 ? 1 : 0;
    } else {
      int mapValue = 0;
      for (int i = 0; arr[index] * i < aim; i++) {
        mapValue = map[index + 1][aim - arr[index] * i];
        if (mapValue != 0) {//已经计算过了
          res += mapValue == -1 ? 0 : mapValue;
        } else {
          res += process2(arr, index + 1, aim - arr[index] * i, map);
        }
      }
    }

    map[index][aim] = res == 0 ? -1 : res;
    return res;
  }


  /**
   * 动态规划方法
   *   1、其本质就是利用申请的空间来记录每一个暴力搜索的计算结果，
   *     下次要用到结果的时候直接使用，而不再重复的递归过程
   *   2、动态规划规定每一种递归状态的计算顺序，依次进行计算
   *
   *
   *
   *
   *  如果arr 长度为N，生成行数为N，列数为aim+1的矩阵dp；
   *  dp[i][j]的含义是在使用arr[0..i]货币的情况下，组成钱数J有多少种方法。
   *
   *
   *  dp[i][j] 就等于一下情况的累加
   *   1、完全不使用arr[i]货币，只使用arr[0..i-1]货币时，方法数为dp[i-1][j]
   *   2、使用1张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-1*arr[i]];
   *   3、使用2张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-2*arr[i]];
   *   4、使用3张arr[i]货币，剩下的钱用arr[0..i-1]货币时，方法数为dp[i-1][j-3*arr[i]];
   *
   *   ...
   *   将这些情况全部累加起来就是dp[i][j];
   *
   *
   */


  /**
   * question 2
   * 有n级台阶，一个人每次上一级或者两级，问有多少中走完N级台阶的方法？
   * <p>
   * 分析：
   * 到N级台阶，只有n-1级台阶(+1)  或者n-2(+2).所以到N级台阶的方法=n-1的方法+n-2的方法
   * <p>
   * n=1  只有1种
   * n=2  两种
   */

  public int question2(int n) {
    if (n < 1) {
      return 0;
    }
    if (n == 1 || n == 2) {
      return n;
    }
    return question2(n - 1) + question2(n - 2);
  }


  /**
   * question 3
   *
   * 给定一个矩阵M，从左上角开始每次只能向右或者向下走，左右到达右下角的位置。
   * 路径上所有的数字累加起来就是路径和，返回所有路径和中的最小路径。
   *
   *
   * 分析：
   *  假设矩阵M的大小为M*N，行数为M，列数为N，生成大小和M一样的矩阵dp,行数为M，列数为N。
   *  dp[i][j]的值表示从左上角，也就数(0,0)位置走到(i,j)位置的最小路径和
   *
   *
   * 所以计算公式为：
   *  dp[i][j] = m[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);  表示本身的值，加上从上或者从从右到达的最小值
   *
   */


  /**
   * question4
   * 给定数组arr,返回arr的最长递增子序列长度.比如arr=[2,1,5,3,6,4,8,9,7],
   * 最长递增子序列为[1,3,4,8,9];所以返回这个子序列的长度为5，
   * <p>
   * <p>
   * 分析：
   * dp[i] 表示在必须以arr[i]这个数结尾的情况下，arr[0..i]中的最大递增子序列长度
   * <p>
   * arr[i] 的倒数第二个数的求法：0..i中谁的递增子序列最大，谁就是倒数第二个
   * <p>
   * 计算公式为：
   * dp[i] = max{dp[i]+1(0<=j<i,arr[j]<arr[i])}
   */

  public int question4(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int length = arr.length;
    int[] list = new int[length];//存储第i个元素之前的最长递增序列值
    List result = new ArrayList();//存储最长递增序列
    for (int i = 0; i < length; i++) {
      list[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && list[j] + 1 > list[i]) {
          list[i] = list[j] + 1;//修改递增序列值
          if (result.isEmpty()) {
            result.add(list[j]);
          }
          if (!result.contains(list[i])) {
            result.add(list[i]);
          }
        }
      }
    }

    int max = list[0];
    for (int i = 0; i < length; i++) {
      if (list[i] > max) {
        max = list[i];
      }
    }
    return max;
  }


  /**
   * question5 最长子序列
   * <p>
   * 给定两个字符串str1和str2,返回两个字符串的最长公共子序列
   * <p>
   * <p>
   * 分析：
   * 假设str1的长度为M，str2的长度为N，生成大小为M*N的矩阵dp.
   * dp[i][j]的含义是str1[0..i]与str2[0..j]的最长公共子序列的长度
   * <p>
   * <p>
   * dp求法如下，
   * 1、矩阵dp第一列，即dp[i][0],代表str1[0..i]与str2[0]的最长公共子序列长度，str2[0]只有一个字符，所以dp[i][0]最大为1
   * 如果str1[i] == str2[0], 则令dp[i][0]为1；一旦dp[i][0]被设置为1，则令dp[i+1..m][0]全部为1；
   * 2、矩阵第一行：即dp[0][j],与步骤一同理，如果str1[0] == str2[j],则令dp[0][j] 为1.
   * 一旦dp[0][j] 为1。则令dp[0][j+i...m] 全部为1；
   * <p>
   * 3、其它位置dp[i][j]的值只可能来自以下三种情况；
   * 1、可能是dp[i-1][j]的值，代表str1[0..i-1]与str2[0..j]的最长子公共序列长度
   * 2、同理可知dp[i][j-1]的值
   * 3、如果str1[i] == str2[j],还有可能是dp[i-1][j-1]+1的值
   * <p>
   * 最后三种情况求最大值，作为dp[i][j]的值
   */
  public static int LCS(String str1, String str2) {
    //定义一个矩阵
    int[][] c = new int[str1.length() + 1][str2.length() + 1];
    //初始化矩阵
    for (int row = 0; row <= str1.length(); row++) {
      c[row][0] = 0;
    }
    for (int column = 0; column <= str2.length(); column++) {
      c[0][column] = 0;
    }

    for (int i = 1; i <= str1.length(); i++)
      for (int j = 1; j <= str2.length(); j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {//第三种情况，字符相等
          c[i][j] = c[i - 1][j - 1] + 1;
        } else if (c[i][j - 1] > c[i - 1][j]) {
          c[i][j] = c[i][j - 1];
        } else {
          c[i][j] = c[i - 1][j];
        }
      }
    return c[str1.length()][str2.length()];
  }

  /**
   * 公共子串，暴力
   * @param str1
   * @param str2
   * @return
   */
  public static int getCommonStrLength(String str1, String str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();
    int len1 = str1.length();
    int len2 = str2.length();
    String min = null;
    String max = null;
    String target = null;
    min = len1 <= len2 ? str1 : str2;
    max = len1 > len2 ? str1 : str2;
    //最外层：min子串的长度，从最大长度开始
    for (int i = min.length(); i >= 1; i--) {
      //遍历长度为i的min子串，从0开始
      for (int j = 0; j <= min.length() - i; j++) {
        target = min.substring(j, j + i);
        //遍历长度为i的max子串，判断是否与target子串相同，从0开始
        for (int k = 0; k <= max.length() - i; k++) {
          if (max.substring(k, k + i).equals(target)) {
            System.out.print(target);
            return i;
          }
        }
      }
    }
    return 0;
  }


  public static void main(String[] args) {
    String str1 = "abcdefg";
    String str2 = "cde";
//    int result = LCS(str1, str2);
//    System.out.println(result);

    getCommonStrLength(str1, str2);
  }
}
