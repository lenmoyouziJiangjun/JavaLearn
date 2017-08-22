package com.lll.reflect.annotation.fruit;

import java.lang.annotation.*;

/**
 * Version 1.0
 * Created by lll on 17/8/22.
 * Description
 * copyright generalray4239@gmail.com
 */

@Target(ElementType.FIELD) /*修饰字段*/
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    /**
     * 颜色枚举
     *
     * @author
     */
    public enum Color {
        BULE, RED, GREEN
    }


    /**
     * 颜色属性
     *
     * @return
     */
    Color fruitColor() default Color.GREEN;
}
