package org.apache.commons.proxy2.interceptor;

import org.apache.commons.lang3.Validate;
import main.org.apache.commons.proxy2.Interceptor;
import org.apache.commons.proxy2.Invocation;
import org.apache.commons.proxy2.ObjectProvider;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObjectProviderInterceptor implements Interceptor {

  private final ObjectProvider<?> provider;

  public ObjectProviderInterceptor(ObjectProvider<?> provider) {
    this.provider = Validate.notNull(provider, "Provider cannot be null.");
  }

  @Override
  public Object intercept(Invocation invocation) throws Throwable {

    return provider.getObject();
  }
}
