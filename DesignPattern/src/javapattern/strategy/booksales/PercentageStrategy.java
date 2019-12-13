package javapattern.strategy.booksales;

import java.math.BigDecimal;

/**
 * Version 1.0
 * Created by lll on 17/7/28.
 * Description 具体算法二
 * copyright generalray4239@gmail.com
 */
public class PercentageStrategy extends DiscountStrategy<PercentageBook> {

  @Override
  public BigDecimal discount(PercentageBook book) {
    return null;
  }
}
