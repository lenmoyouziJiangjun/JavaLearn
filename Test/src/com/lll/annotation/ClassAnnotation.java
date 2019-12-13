package com.lll.annotation;

import java.lang.annotation.*;

/**
 * Version 1.0
 * Created by lll on 2019-12-07.
 * Description
 * copyright generalray4239@gmail.com
 */

@Documented()
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface ClassAnnotation {
  String description() default "Class annotation";

  String value()  default "class";
}
