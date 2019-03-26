package com.google.common.base;/**
 * Created by liaoxueyan on 2019/2/26.
 */

import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
@FunctionalInterface
public interface Supplier<T> extends java.util.function.Supplier<T>  {

    T get();
}
