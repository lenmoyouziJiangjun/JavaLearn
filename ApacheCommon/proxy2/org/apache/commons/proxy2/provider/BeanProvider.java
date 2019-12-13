package org.apache.commons.proxy2.provider;

import com.sun.istack.internal.NotNull;
import org.apache.commons.proxy2.ObjectProvider;
import org.apache.commons.proxy2.exception.ObjectProviderException;

import java.io.Serializable;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description Uses <code>Class.newInstance()</code> to instantiate an object.
 * copyright generalray4239@gmail.com
 */
public class BeanProvider<T> implements ObjectProvider<T>, Serializable {

  private final Class<? extends T> beanClass;

  public BeanProvider(@NotNull Class<? extends T> beanClass) {
    this.beanClass = beanClass;
  }


  @Override
  public T getObject() {
    try {
      return beanClass.newInstance();
    } catch (InstantiationException e) {
      throw new ObjectProviderException(e, "%s is not concrete.", beanClass);
    } catch (IllegalAccessException e) {
      throw new ObjectProviderException(e, "Constructor for %s is not accessible.", beanClass);
    }
  }
}
