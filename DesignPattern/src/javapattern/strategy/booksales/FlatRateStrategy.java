package javapattern.strategy.booksales;

import java.math.BigDecimal;

/**
 * Version 1.0
 * Created by lll on 17/7/28.
 * Description 具体算法一：
 * copyright generalray4239@gmail.com
 */
public class FlatRateStrategy extends DiscountStrategy<FlatRateBook> {
  @Override
  public BigDecimal discount(FlatRateBook book) {
    return null;
  }
}
