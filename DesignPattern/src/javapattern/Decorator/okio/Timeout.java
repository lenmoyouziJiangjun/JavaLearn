package javapattern.Decorator.okio;

import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/**
 * Version 1.0
 * Created by lll on 17/10/9.
 * Description
 * A policy on how much time to spend on a task before giving up. When a task
 * times out, it is left in an unspecified state and should be abandoned. For
 * example, if reading from a source times out, that source should be closed and
 * the read should be retried later. If writing to a sink times out, the same
 * rules apply: close the sink and retry later.
 * <p>
 * <h3>Timeouts and Deadlines</h3>
 * This class offers two complementary controls to define a timeout policy.
 * <p>
 * <p><strong>Timeouts</strong> specify the maximum time to wait for a single
 * operation to complete. Timeouts are typically used to detect problems like
 * network partitions. For example, if a remote peer doesn't return <i>any</i>
 * data for ten seconds, we may assume that the peer is unavailable.
 * <p>
 * <p><strong>Deadlines</strong> specify the maximum time to spend on a job,
 * composed of one or more operations. Use deadlines to set an upper bound on
 * the time invested on a job. For example, a battery-conscious app may limit
 * how much time it spends pre-loading content.
 * copyright generalray4239@gmail.com
 */
public class Timeout {

    /**
     * An empty timeout that neither tracks nor detects timeouts. Use this when
     * timeouts aren't necessary, such as in implementations whose operations
     * do not block.
     */
    public static final Timeout NONE = new Timeout(){
        @Override public Timeout timeout(long timeout, TimeUnit unit) {
            return this;
        }

        @Override public Timeout deadlineNanoTime(long deadlineNanoTime) {
            return this;
        }

        @Override public void throwIfReached() throws IOException {
        }
    };

    private boolean hasDeadline;
    private long deadlineNanoTime;
    private long timeoutNanos;


    public Timeout(){

    }

    /**
     * Wait at most {@code timeout} time before aborting an operation. Using a
     * per-operation timeout means that as long as forward progress is being made,
     * no sequence of operations will fail.
     *
     * <p>If {@code timeout == 0}, operations will run indefinitely. (Operating
     * system timeouts may still apply.)
     *
     */
    public Timeout timeout(long timeout,TimeUnit unit){
        if (timeout < 0) throw new IllegalArgumentException("timeout < 0: " + timeout);
        if (unit == null) throw new IllegalArgumentException("unit == null");
        this.timeoutNanos = unit.toNanos(timeout);
        return this;
    }

    /** Returns the timeout in nanoseconds, or {@code 0} for no timeout. */
    public long timeoutNanos() {
        return timeoutNanos;
    }

    /** Returns true if a deadline is enabled. */
    public boolean hasDeadline() {
        return hasDeadline;
    }

    /**
     * Returns the {@linkplain System#nanoTime() nano time} when the deadline will
     * be reached.
     *
     * @throws IllegalStateException if no deadline is set.
     */
    public long deadlineNanoTime() {
        if (!hasDeadline) throw new IllegalStateException("No deadline");
        return deadlineNanoTime;
    }

    /**
     * Sets the {@linkplain System#nanoTime() nano time} when the deadline will be
     * reached. All operations must complete before this time. Use a deadline to
     * set a maximum bound on the time spent on a sequence of operations.
     */
    public Timeout deadlineNanoTime(long deadlineNanoTime) {
        this.hasDeadline = true;
        this.deadlineNanoTime = deadlineNanoTime;
        return this;
    }

    /** Set a deadline of now plus {@code duration} time. */
    public final Timeout deadline(long duration, TimeUnit unit) {
        if (duration <= 0) throw new IllegalArgumentException("duration <= 0: " + duration);
        if (unit == null) throw new IllegalArgumentException("unit == null");
        return deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
    }

    /** Clears the timeout. Operating system timeouts may still apply. */
    public Timeout clearTimeout() {
        this.timeoutNanos = 0;
        return this;
    }

    /** Clears the deadline. */
    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    /**
     * Throws an {@link InterruptedIOException} if the deadline has been reached or if the current
     * thread has been interrupted. This method doesn't detect timeouts; that should be implemented to
     * asynchronously abort an in-progress operation.
     */
    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }

        if (hasDeadline && deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    /**
     * Waits on {@code monitor} until it is notified. Throws {@link InterruptedIOException} if either
     * the thread is interrupted or if this timeout elapses before {@code monitor} is notified. The
     * caller must be synchronized on {@code monitor}.
     *
     * <p>Here's a sample class that uses {@code waitUntilNotified()} to await a specific state. Note
     * that the call is made within a loop to avoid unnecessary waiting and to mitigate spurious
     * notifications. <pre>{@code
     *
     *   class Dice {
     *     Random random = new Random();
     *     int latestTotal;
     *
     *     public synchronized void roll() {
     *       latestTotal = 2 + random.nextInt(6) + random.nextInt(6);
     *       System.out.println("Rolled " + latestTotal);
     *       notifyAll();
     *     }
     *
     *     public void rollAtFixedRate(int period, TimeUnit timeUnit) {
     *       Executors.newScheduledThreadPool(0).scheduleAtFixedRate(new Runnable() {
     *         public void run() {
     *           roll();
     *          }
     *       }, 0, period, timeUnit);
     *     }
     *
     *     public synchronized void awaitTotal(Timeout timeout, int total)
     *         throws InterruptedIOException {
     *       while (latestTotal != total) {
     *         timeout.waitUntilNotified(this);
     *       }
     *     }
     *   }
     * }</pre>
     */
    public final void waitUntilNotified(Object monitor)throws InterruptedIOException{
       try {
           boolean hasDeadline = hasDeadline();
           long timeoutNanos = timeoutNanos();
           if(!hasDeadline && timeoutNanos ==0L){
               monitor.wait();//There is no timeout: wait forever.
               return;
           }
           // Compute how long we'll wait.
           long waitNanos;
           long start = System.nanoTime();
           if (hasDeadline && timeoutNanos != 0) {
               long deadlineNanos = deadlineNanoTime() - start;
               waitNanos = Math.min(timeoutNanos, deadlineNanos);
           } else if (hasDeadline) {
               waitNanos = deadlineNanoTime() - start;
           } else {
               waitNanos = timeoutNanos;
           }

           // Attempt to wait that long. This will break out early if the monitor is notified.
           long elapsedNanos = 0L;
           if (waitNanos > 0L) {
               long waitMillis = waitNanos / 1000000L;
               monitor.wait(waitMillis, (int) (waitNanos - waitMillis * 1000000L));
               elapsedNanos = System.nanoTime() - start;
           }

           // Throw if the timeout elapsed before the monitor was notified.
           if (elapsedNanos >= waitNanos) {
               throw new InterruptedIOException("timeout");
           }
       }catch (Exception e){
           throw new InterruptedIOException("interrupted");
       }
    }


}
