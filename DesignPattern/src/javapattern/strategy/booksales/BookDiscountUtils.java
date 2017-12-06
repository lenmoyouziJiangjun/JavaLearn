package javapattern.strategy.booksales;

import java.math.BigDecimal;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/7/28.
 * Description
 * copyright generalray4239@gmail.com
 */
public class BookDiscountUtils {

    /**
     * 计算价格
     *
     * @param books
     * @param discountStrategy
     * @return
     */
    public static BigDecimal getBookDiscount(List<Book> books, DiscountStrategy discountStrategy) {
        BigDecimal decimal = null;
        if (books != null && books.size() > 0 && discountStrategy != null) {
            for (Book book : books) {
                decimal.add(discountStrategy.discount(book));
            }
        }
        return decimal;
    }

}
