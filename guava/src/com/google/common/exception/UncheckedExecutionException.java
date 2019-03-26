package com.google.common.exception;

import com.google.common.annotations.GwtCompatible;
import com.sun.istack.internal.Nullable;

/**
 * Version 1.0
 * Created by lll on 2019/2/27.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public class UncheckedExecutionException extends RuntimeException {
    /**
     * Creates a new instance with {@code null} as its detail message.
     */
    protected UncheckedExecutionException() {}

    /**
     * Creates a new instance with the given detail message.
     */
    protected UncheckedExecutionException(@Nullable String message) {
        super(message);
    }

    /**
     * Creates a new instance with the given detail message and cause.
     */
    public UncheckedExecutionException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance with the given cause.
     */
    public UncheckedExecutionException(@Nullable Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = 0;
}
