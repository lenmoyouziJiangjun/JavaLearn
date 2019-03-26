package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtIncompatible
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {

    // The execution list to hold our listeners.
    private final ExecutionList executionList = new ExecutionList();


    /**
     * Creates a {@code ListenableFutureTask} that will upon running, execute the given {@code
     * Callable}.
     *
     * @param callable the callable task
     * @since 10.0
     */
    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<V>(callable);
    }

    /**
     * Creates a {@code ListenableFutureTask} that will upon running, execute the given {@code
     * Runnable}, and arrange that {@code get} will return the given result on successful completion.
     *
     * @param runnable the runnable task
     * @param result the result to return on successful completion. If you don't need a particular
     *     result, consider using constructions of the form: {@code ListenableFuture<?> f =
     *     ListenableFutureTask.create(runnable, null)}
     * @since 10.0
     */
    public static <V> ListenableFutureTask<V> create(Runnable runnable, @Nullable V result) {
        return new ListenableFutureTask<V>(runnable, result);
    }

    ListenableFutureTask(Callable<V> callable) {
        super(callable);
    }

    ListenableFutureTask(Runnable runnable, @Nullable V result) {
        super(runnable, result);
    }

    @Override
    public void addListener(Runnable listener, Executor exec) {
        executionList.add(listener, exec);
    }

    /** Internal implementation detail used to invoke the listeners. */
    @Override
    protected void done() {
        executionList.execute();
    }
}
