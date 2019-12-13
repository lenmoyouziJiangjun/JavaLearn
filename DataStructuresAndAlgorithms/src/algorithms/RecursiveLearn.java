package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Version 1.0
 * Created by lll on 2019/3/12.
 * Description
 * <pre>
 *     一、递归需要满足三个条件
 *        1、一个问题可以分解为几个子问题的解
 *        2、这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
 *        3、存在递归终止条件
 *     二、递归的核心
 *        1、写出递归的和兴是将大问题分解成小问题的规律，并基于此写递推公式
 *     三、递归要注意栈溢出
 *        1、如果递归的数据规模很大，调用层次很深，就容易出现栈溢出
 *        2、警惕重复计算
 *        3、递归的空间复杂度为O(N)
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class RecursiveLearn {


  /**
   * 一只青蛙可以一次跳 1 级台阶或一次跳 2 级台阶,例如:
   * 跳上第 1 级台阶只有一种跳法：直接跳 1 级即可。跳上第 2 级台阶
   * 有两种跳法：每次跳 1 级，跳两次；或者一次跳 2 级。
   * 问要跳上第 n 级台阶有多少种跳法？
   *
   * 递归方法
   *    1、必须有参数和返回值
   *    2、判断临界条件
   *    3、求解地推公式： 地推公式分析技巧：这一步的结果和前面步骤有什么关系
   *
   */
  public static int test1(int n) {
    if (n == 1) { //第一级台阶 ：临界条件
      return 1;
    } else if (n == 2) { //第二级台阶： 临界条件
      return 2;
    } else {
      int result = test1(n - 1) + test1(n - 2);
      print("test1", n, result);
      return result;
    }
  }

  /**
   * 递归存在的问题就是：重复计算，比如上面的 要计算6级台阶需要多少方法，
   *
   *  就存在重复计算，重复计算的解决方式就是，存储上一次计算结果。空间换时间
   * @param n
   * @return
   */
  static Map<Integer, Integer> resultMaps = new HashMap<>(); //用一个map存储计算结果，或者用一个二维数组计算
  public static int test2(int n) {
    if (n == 1) { //第一级台阶 ：临界条件
      return 1;
    } else if (n == 2) { //第二级台阶： 临界条件
      return 2;
    } else {
      Integer preNum = resultMaps.get(n);
      if (null != preNum) {
        print("test2 preNum", n, preNum);
        return preNum;
      }
      int result = test2(n-1) + test2(n-2);
      resultMaps.put(n,result);
      print("test2 putResult", n, result);
      return result;
    }
  }

  /**
   * 基于test2的优化
   * 分析问题我们需要采用自上而下的思维，而解决问题有时候采用自下而上的方式能让算法性能得到极大提升,思路比结论重要
   * @param n
   * @return
   */
  public static int test3(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;

    int result = 0;
    int pre = 1;
    int next = 2;

    for (int i = 3; i < n + 1; i++) {
      result = pre + next;
      pre = next;
      next = result;
    }
    return result;
  }


  public static class TreeNode{
    int val;
    TreeNode leftNode;
    TreeNode rightNode;
  }

  /**
   * 反转二叉树
   */
  public static TreeNode invertTree(TreeNode node) {
    if (node == null) {
      return null;
    }
    TreeNode left = invertTree(node.leftNode);
    TreeNode right = invertTree(node.rightNode);
    node.leftNode = right;
    node.rightNode = left;
    return node;
  }


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
  public static int  testCell(int initNums, int hour) {
    if (hour == 0) { //1
      return initNums;
    } else if (hour == 1) { // 2
      return initNums * 2;
    } else if (hour == 2) { //3
      return initNums * 4; //  2*f(n-1) 上一个结果的两倍，
    } else if (hour == 3) { // 4
      return initNums * 8 - initNums; // 2*f(n-1) - f(n-4)
    } else {
      return 2 * testCell(initNums, hour - 1) - testCell(initNums, hour - 4);
    }
  }




  public static void print(String methodName, int currentIndex, int result) {
     System.out.println(methodName+"-------index===="+currentIndex + "-----result=="+result);
  }

  public static void main(String[] args) {

    /**
     * test1-------index====3-----result==3
     * test1-------index====4-----result==5
     * test1-------index====3-----result==3
     * test1-------index====5-----result==8
     * test1-------index====3-----result==3
     * test1-------index====4-----result==5
     * test1-------index====6-----result==13
     */
    RecursiveLearn.test1(6);
    System.out.println("-----------------------------");
    /**
     * test2 putResult-------index====3-----result==3
     * test2 putResult-------index====4-----result==5
     * test2 preNum-------index====3-----result==3
     * test2 putResult-------index====5-----result==8
     * test2 preNum-------index====4-----result==5
     * test2 putResult-------index====6-----result==13
     */
    RecursiveLearn.test2(6);
  }
}



