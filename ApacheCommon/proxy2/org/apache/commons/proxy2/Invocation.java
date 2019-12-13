package org.apache.commons.proxy2;

import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description Method invocation for use by an {@link Interceptor}.
 * copyright generalray4239@gmail.com
 */
public interface Invocation {
  /**
   * Returns the arguments being passed to this method invocation. Changes in the elements of this array will be
   * propagated to the recipient of this invocation.
   *
   * @return the arguments being passed to this method invocation
   */
  Object[] getArguments();

  /**
   * Returns the method being called.
   *
   * @return the method being called
   */
  Method getMethod();

  /**
   * Returns the javapattern.proxy object on which this invocation was invoked.
   *
   * @return the javapattern.proxy object on which this invocation was invoked
   */
  Object getProxy();

  /**
   * Called in order to let the invocation proceed.
   *
   * @return the return value of the invocation
   * @throws Throwable any exception or error that was thrown as a result of this invocation
   */
  Object proceed() throws Throwable;
}
