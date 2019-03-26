package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * Hidden superclass of {@link Futures} that provides us a place to declare special GWT versions of
 * the {@link Futures#catching(ListenableFuture, Class, com.google.common.base.Function)
 * Futures.catching} family of methods. Those versions have slightly different signatures.
 * copyright generalray4239@gmail.com
 */
@GwtCompatible(emulated = true)
abstract class GwtFuturesCatchingSpecialization {
}
