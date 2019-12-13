package com.lll.czt;

import java.util.Date;
import java.util.Random;

/**
 * Version 1.0
 * Created by lll on 2019-09-01.
 * Description
 * copyright generalray4239@gmail.com
 */
public class EarningsTest {

  private static final double ONE_MINER = 500;
  private static final double TWO_MINER = 1000;
  private static final double THREE_MINER = 2000;
  private static final double FOUR_MINER = 4000;

  /**
   * 矿机数组
   */
  private static final double[] MINER_ARRAY = {ONE_MINER, TWO_MINER, THREE_MINER, FOUR_MINER};


  /**
   * 每天的静态收益
   */
  private static final double ONE = 4;
  private static final double TWO = 8.5;
  private static final double THREE = 18;
  private static final double FOUR = 40;

  /**
   * 动态收益比例
   */
  private static final double ONE_GENERATION = 0.3;
  private static final double TWO_GENERATION = 0.2;
  private static final double THREE_GENERATION = 0.1;
  private static final double FOUR_GENERATION = 0.05;
  private static final double FIVE_GENERATION = 0.02;


  /**
   * 管理奖比例
   */
  private static final double ONE_OWNER = 0.03;
  private static final double TWO_OWNER = 0.05;
  private static final double THREE_OWNER = 0.07;
  private static final double FOUR_OWNER = 0.1;
  private static final double FIVE_OWNER = 0.12;


  public static void getTopEarnings() {
    double totalEarn = 0.0;
    for (int i = 0; i < 15; i++) {
      totalEarn += FOUR * 0.02;
      for (int i1 = 0; i1 < 15; i1++) {
        totalEarn += FOUR * 0.02;
        for (int i2 = 0; i2 < 15; i2++) {
          totalEarn += FOUR * 0.02;
          for (int i3 = 0; i3 < 15; i3++) {
            totalEarn += FOUR * 0.02;
            for (int i4 = 0; i4 < 15; i4++) {
              totalEarn += FOUR * 0.02;
              for (int i5 = 0; i5 < 15; i5++) {
                totalEarn += FOUR * 0.02;
                for (int i6 = 0; i6 < 15; i6++) {
                  totalEarn += FOUR * 0.02;
                  for (int i7 = 0; i7 < 15; i7++) {
                    totalEarn += FOUR * 0.02;
                    for (int i8 = 0; i8 < 15; i8++) {
                      totalEarn += FOUR * 0.02;
                      for (int i9 = 0; i9 < 15; i9++) {
                        totalEarn += FOUR * 0.02;
                        for (int i10 = 0; i10 < 15; i10++) {
                          totalEarn += FOUR * 0.02;
                          for (int i11 = 0; i11 < 15; i11++) {
                            totalEarn += FOUR * 0.02;
                            for (int i12 = 0; i12 < 15; i12++) {
                              totalEarn += FOUR * 0.02;
                              for (int i13 = 0; i13 < 15; i13++) {
                                totalEarn += FOUR * 0.02;
                                for (int i14 = 0; i14 < 15; i14++) {
                                  totalEarn += FOUR * 0.02;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }

            }

          }

        }
      }
    }
    print("推荐15代,满级", totalEarn);
  }

  /**
   * 总共4代人，
   */
  public static void getThreeDarn() {
    print("三代计算开始时间：", (new Date().getTime()));
    double firthEarn = 0;
    double total = 0;
    double secondEarn = 0;
    double secondStatic = 0;
    double thirdEarn = 0;
    double thirdStatic = 0;
    double fourthStatic = 0;
    for (int i = 0; i < 3; i++) {
      firthEarn += ONE * 0.1;
      total += 500;
      secondStatic += 4;
      for (int i1 = 0; i1 < 3; i1++) {
        firthEarn += ONE * 0.1;
        total += 500;
        secondEarn += ONE * 0.2;
        thirdStatic += 4;
        for (int i2 = 0; i2 < 3; i2++) {
          firthEarn += ONE * 0.1;
          total += 500;
          secondEarn += ONE * 0.2;
          thirdEarn += ONE * 0.3;
          fourthStatic += 4;
        }
      }
    }
    print("三代计算结束时间：", (new Date().getTime()));

    print("四代：祖宗的动态收益", firthEarn);
    print("四代：祖宗的动态收益+静态收益", firthEarn + 4);
    print("四代中儿子的收益，儿子代的动态总收益", secondEarn);
    print("四代中儿子的收益，儿子代的动态总收益+静态收益", secondEarn + secondStatic);
    print("四代中的孙子收益，孙子代的动态总收益", thirdEarn);
    print("四代中的孙子收益，孙子代的动态总收益+静态收益", thirdEarn + thirdStatic);
    print("四代中的最后一代曾孙只有静态收益，静态收益为", fourthStatic);


    print("一天总的动态收益：", firthEarn + secondEarn + thirdEarn);
    print("一天总的动态收益+静态收益", firthEarn + 4 + secondEarn + thirdEarn + secondStatic + thirdStatic + fourthStatic);

    print("四代系统中总收入:", total + 500);
    print("四代系统中（每天动态收益+静态收益）占总收入的百分百:", (firthEarn + 4 + secondEarn + thirdEarn + secondStatic + thirdStatic + fourthStatic) / total * 100);
  }


  /**
   * 模拟有至尊矿主一天需要产生的收益
   */
  public static void getFirstMinerOwner() {
    double total = 0;
    for (int i = 0; i < 10; i++) {
      if (i == 0 || i == 1) { //两个儿子是
        total += FOUR_MINER;
      } else {
        total += getRandomMiner();
      }
    }
  }


  public static double getRandomMiner() {
    Random random = new Random();
    double miner = MINER_ARRAY[random.nextInt(4)];
    return miner;
  }


  public static int getRandomNum() {
    return 0;
  }


  private static void print(String param, double earn) {
    System.out.println(param + "：：：：：======" + earn);
  }


  public static void main(String[] args) {
    getThreeDarn();
  }


}
