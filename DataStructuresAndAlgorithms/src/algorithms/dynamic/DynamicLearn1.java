package algorithms.dynamic;

import org.omg.CORBA.MARSHAL;

/**
 * Version 1.0
 * Created by lll on 2019-12-22.
 * Description
 * copyright generalray4239@gmail.com
 */
public class DynamicLearn1 {

  /**
   * 动态规划实现背包问题
   * 采用二维数组来实现
   *
   * @param weight 物品重量
   * @param n      物品个数
   * @param w      背包承受重量
   * @return
   */
  public int knapsack(int[] weight, int n, int w) {
    boolean[][] states = new boolean[n][w + 1]; // 默认值false
    states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
    if (weight[0] <= w) {
      states[0][weight[0]] = true;
    }
    for (int i = 1; i < n; ++i) { // 动态规划状态转移
      for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
        if (states[i - 1][j] == true) {
          states[i][j] = states[i - 1][j];
        }
      }
      for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
        if (states[i - 1][j] == true) {
          states[i][j + weight[i]] = true;
        }
      }
    }
    for (int i = w; i >= 0; --i) { // 输出结果
      if (states[n - 1][i] == true) return i;
    }
    return 0;
  }


  /**
   * 背包问题以为数组解法
   * <p>
   * 不超过背包的总重量，最大能装多少
   *
   * @param items
   * @param n
   * @param w
   * @return
   */
  public static int knapsack2(int[] items, int n, int w) {
    boolean[] states = new boolean[w + 1]; // 默认值false
    states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
    if (items[0] <= w) {
      states[items[0]] = true;
    }
    for (int i = 1; i < n; ++i) { // 动态规划
      for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
        if (states[j] == true) {
          states[j + items[i]] = true;
        }
      }
    }
    for (int i = w; i >= 0; --i) { // 输出结果
      if (states[i] == true) {
        System.out.println("  " + i);
        return i;
      }
    }
    return 0;
  }


  /**
   * <pre>
   *  我们刚刚讲的背包问题，只涉及背包重量和物品重量。我们现在引入物品价值这一变量。
   *  对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
   *
   * </pre>
   *
   * @param weight 商量重量数组
   * @param value  商品质量数组
   * @param n      商品数量
   * @param w      重量
   */
  public static void enhanceKnapsack(int[] weight, int[] value, int n, int w) {

    //表示第i个物品，j重量下的钱，为什么W+1？
    int[][] states = new int[n][w + 1];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < w + 1; j++) {
        states[i][j] = -1; //初始状态为-1
      }
    }
    states[0][0] = 0;//定义哨兵
    if (weight[0] <= w) {
      states[0][weight[0]] = value[0];
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= w; j++) {
        if (states[i - 1][j] > 0) {
          states[i][j] = states[i - 1][j];
        }
      }

      for (int j = 0; j <= w - weight[i]; j++) {
        if (states[i - 1][j] >= 0) { //代表已经计算了某个
          int v = states[i - 1][j] + value[i];
          if (v > states[i][j + weight[i]]) {
            states[i][j + weight[i]] = v;
          }
        }
      }
    }

    int maxValue = -1;
    for (int j = 0; j <= w; j++) {
      if (states[n - 1][j] > maxValue) {
        maxValue = states[n - 1][j];
      }
    }
    System.out.println("maxValue==" + maxValue);
  }


  /**
   * <pre>
   *   淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。
   *   假设你女朋友的购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，在凑够满减条件的前提下，让选出来的商品价格总和最大程度地接近满减条件（200 元），这样就可以极大限度地“薅羊毛”。
   *
   * </pre>
   * <p>
   * items商品价格，
   * n商品个数,
   * w表示满减条件，比如200
   */
  public static void double11advance(int[] items, int n, int w) {
    boolean[][] states = new boolean[n][3 * w + 1];//超过3倍就没有薅羊毛的价值了
    states[0][0] = true;  // 第一行的数据要特殊处理
    if (items[0] <= 3 * w) {
      states[0][items[0]] = true;
    }
    for (int i = 1; i < n; ++i) { // 动态规划
      for (int j = 0; j <= 3 * w; ++j) {// 不购买第i个商品
        if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
      }
      for (int j = 0; j <= 3 * w - items[i]; ++j) {//购买第i个商品
        if (states[i - 1][j] == true) states[i][j + items[i]] = true;
      }
    }

    int j;
    for (j = w; j < 3 * w + 1; ++j) {
      if (states[n - 1][j] == true) break; // 输出结果大于等于w的最小值
    }
    if (j == 3 * w + 1) return; // 没有可行解
    for (int i = n - 1; i >= 1; --i) { // i表示二维数组中的行，j表示列
      if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
        System.out.print(items[i] + " "); // 购买这个商品
        j = j - items[i];
      } // else 没有购买这个商品，j不变。
    }
    if (j != 0) {
      System.out.print(items[0]);
    }
  }


  public static void testKnapsack() {
    int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    int[] value = {3, 4, 8, 9, 6}; // 物品的价值
    int n = 5; // 物品个数
    int w = 9; // 背包承受的最大重量

    knapsack2(weight, n, w);
  }


  /**
   * <pre>
   *   假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。
   *   棋子起始位置在左上角，终止位置在右下角。我们将棋子从左上角移动到右下角。
   *   每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。
   *   我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
   *
   * </pre>
   */
  public static void getChessmanMinValue(int[][] nums, int n) {
    int[][] states = new int[n][n]; //states[n][n] 表示，到达n行n列最短路径的值
    for (int j = 0; j < n; j++) { //初始化第0行数据，
      states[0][j] += nums[0][j]; //下一个= 上一个加当前
    }
    for (int i = 0; i < n; i++) {//初始化第0列数据
      states[i][0] += nums[i][0];
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < n; j++) {
        states[i][j] = nums[i][j] + Math.min(nums[i - 1][j], nums[i][j - 1]);
      }
    }
    System.out.println("the result ===" + states[n - 1][n - 1]);
  }

  /**
   * 采用递归来实现
   *
   * @param matrix 表示数组
   * @param nums   表示到达[m][n]的最小值， 用二维数组来存储状态，减少计算量
   * @param m      行
   * @param n      列
   * @return
   */
  public static int getChessmanMinValue2(int[][] matrix, int[][] nums, int m, int n) {
    if (m == 0 && n == 0) { //递归不可再分条件
      return matrix[0][0];
    }

    if (nums[m][n] > 0) { //已经计算过了
      return nums[m][n];
    }
    int minLeft = Integer.MAX_VALUE;
    if (n - 1 > 0) { //递归左边最小值
      minLeft = getChessmanMinValue2(matrix, nums, m, n - 1);
    }
    int minUp = Integer.MAX_VALUE;
    if (m - 1 > 0) { //递归上面最小值
      minUp = getChessmanMinValue2(matrix, nums, m - 1, n);
    }
    //计算当前值
    int currentMinDist = matrix[m][n] + Math.min(minLeft, minUp);
    System.out.print("the currentMin===" + currentMinDist);
    nums[m][n] = currentMinDist;
    return nums[m][n];
  }


  public static void testChessman() {
    int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    int n = 4;

    getChessmanMinValue(matrix, n);
    int[][] result = new int[4][4];
    int nums = getChessmanMinValue2(matrix, result, n - 1, n - 1);
    System.out.println("递归计算出来的值为：" + nums);
  }


  /**
   * <pre>
   *   我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
   *   比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，
   *   它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
   *
   * </pre>
   */
  public static void lengthOfLIS(int[] nums, int n) {
    int[] dp = new int[n]; //用来记录到达i位置的最长子序列的长度
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) { //从0到i的中最长的子序列
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
      }
      max = Math.max(max, dp[i]);
    }
    System.out.println("the max == " + max);
  }

  /**
   * 参考网站：
   * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247486936&idx=1&sn=27ec53c1463384edeeee138db23c1c7d&chksm=fa0e6259cd79eb4f240786ad7c00dcd0ed39ad68fd62b72e15d6a8ee0ecbd26b6250ab3b1171&scene=21#wechat_redirect
   * <pre>
   *   假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
   *
   * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
   *
   * 例如，costs[0][0]表示第 0 号房子粉刷成红色的成本花费；costs[1][2]表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
   *
   * </pre>
   */
  public static void testHouse(int[][] house) {

  }


  public static void main(String[] args) {

//    testKnapsack();
//    testChessman();
    int[] nums = new int[]{0, 1, 2, 9, 3, 8, 7};
    lengthOfLIS(nums, nums.length);
  }

}
