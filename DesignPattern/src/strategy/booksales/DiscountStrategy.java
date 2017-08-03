package strategy.booksales;

import java.math.BigDecimal;

/**
 * Version 1.0
 * Created by lll on 17/7/28.
 * Description  抽象公共策略
 * copyright generalray4239@gmail.com
 */
public abstract class DiscountStrategy<T extends Book> {

    public abstract BigDecimal discount(T book);
}
