package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
public interface ListenableFuture<V> extends Future<V> {

    /**
     * Registers a listener to be {@linkplain Executor#execute(Runnable) run} on the given executor.
     * The listener will run when the {@code Future}'s computation is {@linkplain Future#isDone()
     * complete} or, if the computation is already complete, immediately.
     *
     * <p>There is no guaranteed ordering of execution of listeners, but any listener added through
     * this method is guaranteed to be called once the computation is complete.
     *
     * <p>Exceptions thrown by a listener will be propagated up to the executor. Any exception thrown
     * during {@code Executor.execute} (e.g., a {@code RejectedExecutionException} or an exception
     * thrown by {@linkplain MoreExecutors#directExecutor direct execution}) will be caught and
     * logged.
     *
     * <p>Note: For fast, lightweight listeners that would be safe to execute in any thread, consider
     * {@link MoreExecutors#directExecutor}. Otherwise, avoid it. Heavyweight {@code directExecutor}
     * listeners can cause problems, and these problems can be difficult to reproduce because they
     * depend on timing. For example:
     *
     * <ul>
     * <li>The listener may be executed by the caller of {@code addListener}. That caller may be a UI
     * thread or other latency-sensitive thread. This can harm UI responsiveness.
     * <li>The listener may be executed by the thread that completes this {@code Future}. That thread
     * may be an internal system thread such as an RPC network thread. Blocking that thread may stall
     * progress of the whole system. It may even cause a deadlock.
     * <li>The listener may delay other listeners, even listeners that are not themselves {@code
     * directExecutor} listeners.
     * </ul>
     *
     * <p>This is the most general listener interface. For common operations performed using
     * listeners, see {@link Futures}. For a simplified but general listener interface, see {@link
     * Futures#addCallback addCallback()}.
     *
     * <p>Memory consistency effects: Actions in a thread prior to adding a listener <a
     * href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.4.5">
     * <i>happen-before</i></a> its execution begins, perhaps in another thread.
     *
     * @param listener the listener to run when the computation is complete
     * @param executor the executor to run the listener in
     * @throws RejectedExecutionException if we tried to execute the listener immediately but the
     *     executor rejected it.
     */
    void addListener(Runnable listener, Executor executor);
}
