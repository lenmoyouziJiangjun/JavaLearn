package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * <p>
 * A time source; returns a time value representing the number of nanoseconds elapsed since some
 * fixed but arbitrary point in time. Note that most users should use {@link Stopwatch} instead of
 * interacting with this class directly.
 * <p>
 * <p><b>Warning:</b> this interface can only be used to measure elapsed time, not wall time.
 * <p>
 * copyright generalray4239@gmail.com
 */
@Beta
@GwtCompatible
public abstract class Ticker {
  /**
   * Constructor for use by subclasses.
   */
  protected Ticker() {
  }

  /**
   * Returns the number of nanoseconds elapsed since this ticker's fixed point of reference.
   */
  public abstract long read();

  /**
   * A ticker that reads the current time using {@link System#nanoTime}.
   *
   * @since 10.0
   */
  public static Ticker systemTicker() {
    return SYSTEM_TICKER;
  }

  private static final Ticker SYSTEM_TICKER =
          new Ticker() {
            @Override
            public long read() {
              return Platform.systemNanoTime();
            }
          };
}

