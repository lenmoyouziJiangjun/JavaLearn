package com.lll.reflect.annotation.fruit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Version 1.0
 * Created by lll on 17/8/22.
 * Description 定义一个水果名称的注解
 * copyright generalray4239@gmail.com
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitName {

  /**
   * 注解参数的可支持数据类型：
   * <p>
   * 　　　　1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
   * 　　　　2.String类型
   * 　　　　3.Class类型
   * 　　　　4.enum类型
   * 　　　　5.Annotation类型
   * 　　　　6.以上所有类型的数组
   * <p>
   * 　　Annotation类型里面的参数该怎么设定:
   * 　　第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；
   * 　　第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和 String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;
   * 　　第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号.例:下面的例子FruitName注解就只有一个参数成员。
   *
   * @return
   */
  String value() default "";
}
