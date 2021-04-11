package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2019-12-13.
 * Description
 * copyright generalray4239@gmail.com
 */
public class leetcode458 {


  /**
   *有 1000 只水桶，其中有且只有一桶装的含有毒药，其余装的都是水。它们从外观看起来都一样。如果小猪喝了毒药，它会在 15 分钟内死去。
   *
   * 问题来了，如果需要你在一小时内，弄清楚哪只水桶含有毒药，你最少需要多少只猪？
   *
   * 回答这个问题，并为下列的进阶问题编写一个通用算法。
   *
   * 进阶：假设有n只水桶，猪饮水中毒后会在m分钟内死亡，你需要多少猪（x）就能在p分钟内找出 “有毒” 水桶？这n只水桶里有且仅有一只有毒的桶。
   *
   * 提示：
   *
   * 1、可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
   * 2、小猪喝完水后，必须有 m 分钟的冷却时间。在这段时间里，只允许观察，而不允许继续饮水。
   * 3、任何给定的桶都可以无限次采样（无限数量的猪）。
   *
   *
   */
  /**
   *
    * @param buckets  桶 1000
   * @param minutesToDie 喝毒后多少分钟挂 15
   * @param minutesToTest 多少时间内 60
   * @return
   */
  public static int poorPigs(int buckets, int minutesToDie, int minutesToTest){
    //buckets为桶数，例子中是1000，minutesToDie是15，minutesToTest是60
    int base=minutesToTest/minutesToDie+1;
    double temp=Math.log(buckets)/Math.log(base);//log是以e为底的对数
    return (int)Math.ceil(temp);//ceil向上取整
  }

  public static void main(String[] args) {
    int types = poorPigs(1000, 15, 60);
    System.out.println("the result == " + types);
  }
}
