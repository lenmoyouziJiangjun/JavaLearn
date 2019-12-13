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
@Retention(RetentionPolicy.SOURCE)
public @interface SourceAnnotation {
  String description() default "source annotation";
  String value() default  "source";
}
