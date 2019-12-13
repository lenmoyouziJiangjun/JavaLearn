package org.apache.commons.proxy2.invoker;

import com.sun.istack.internal.NotNull;
import org.apache.commons.proxy2.Invoker;
import org.apache.commons.proxy2.ObjectProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description Delegates a method invocation to the object provided by an {@link ObjectProvider}.
 * copyright generalray4239@gmail.com
 */
public class DelegatingInvoker<T> implements Invoker {

  private static final long serialVersionUID = 1L;

  private final ObjectProvider<? extends T> delegateProvider;

  public DelegatingInvoker(@NotNull ObjectProvider<? extends T> delegateProvider) {
    this.delegateProvider = delegateProvider;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
    try {
      return method.invoke(delegateProvider.getObject(), arguments);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw e.getTargetException();
    }
  }
}
