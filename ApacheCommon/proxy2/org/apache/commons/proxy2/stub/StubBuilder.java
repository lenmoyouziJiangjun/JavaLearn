package org.apache.commons.proxy2.stub;

import main.org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import main.org.apache.commons.lang3.builder.Builder;
import org.apache.commons.proxy2.Invoker;
import org.apache.commons.proxy2.ObjectProvider;
import org.apache.commons.proxy2.ProxyFactory;
import org.apache.commons.proxy2.interceptor.SwitchInterceptor;
import org.apache.commons.proxy2.invoker.NullInvoker;
import org.apache.commons.proxy2.provider.ConstantProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StubBuilder<T> implements Builder<T> {

  private final ProxyFactory proxyFactory;
  private final T target;
  private final SwitchInterceptor switchInterceptor = new SwitchInterceptor();
  private final Set<Class<?>> proxyTypes = new HashSet<>();

  public StubBuilder(ProxyFactory factory, Class<T> type) {
    this(factory, type, NullInvoker.INSTANCE);
  }

  public StubBuilder(ProxyFactory factory, Class<T> type, Invoker invoker) {
    this.proxyFactory = factory;
    this.target = proxyFactory.createInvokerProxy(invoker, type);
    this.proxyTypes.add(Validate.notNull(type));
  }

  public StubBuilder(ProxyFactory proxyFactory, Class<T> type, ObjectProvider<? extends T> provider) {
    this.proxyFactory = proxyFactory;
    this.target = proxyFactory.createDelegatorProxy(provider, type);
    this.proxyTypes.add(Validate.notNull(type));
  }

  public StubBuilder(ProxyFactory proxyFactory, Class<T> type, T target) {
    this.proxyFactory = proxyFactory;
    this.target = proxyFactory.createDelegatorProxy(new ConstantProvider<T>(target), type);
    this.proxyTypes.add(Validate.notNull(type));
  }


  @Override
  public T build() {
    return proxyFactory.createInterceptorProxy(target, switchInterceptor, proxyTypes.toArray(ArrayUtils.EMPTY_CLASS_ARRAY));
  }

  public <O> StubBuilder<T> train(BaseTrainer<?, O> trainer) {
    final TrainingContext trainingContext = TrainingContext.join(proxyFactory);
    try {
      final O trainee = trainingContext.push(trainer.traineeType, switchInterceptor);
      trainer.train(trainee);
      proxyTypes.add(trainer.traineeType);
    } finally {
      trainingContext.part();
    }
    return this;
  }

  public StubBuilder<T> addProxyTypes(Class<?>... proxyTypes) {
    Collections.addAll(this.proxyTypes, Validate.noNullElements(proxyTypes));
    return this;
  }
}
