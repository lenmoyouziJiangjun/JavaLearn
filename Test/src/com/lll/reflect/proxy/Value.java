package com.lll.reflect.proxy;/**
 * Created by liaoxueyan on 17/6/15.
 */

import java.lang.annotation.*;

/**
 * Version 1.0
 * Created by lll on 17/6/15.
 * Description 自定义一个注解：
 * copyright generalray4239@gmail.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

  String value();
}
