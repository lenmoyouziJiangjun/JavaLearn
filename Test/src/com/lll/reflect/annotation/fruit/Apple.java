package com.lll.reflect.annotation.fruit;

/**
 * Version 1.0
 * Created by lll on 17/8/22.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Apple {

    @FruitName("apple") /*注解元素值必须要么在注解定义时指定，要么在使用是指定*/
    private String name;

    @FruitColor(fruitColor = FruitColor.Color.BULE)
    private String appleColor;

}
