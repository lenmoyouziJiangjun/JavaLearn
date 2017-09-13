package org.apache.commons.proxy2.impl;

import main.org.apache.commons.proxy2.Interceptor;
import org.apache.commons.proxy2.Invoker;
import org.apache.commons.proxy2.ObjectProvider;
import org.apache.commons.proxy2.ProxyFactory;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * Base abstract {@link ProxyFactory} implementation, primarily providing implementations of the interface methods that
 * are typically convenience constructs over the other methods.
 * copyright generalray4239@gmail.com
 */
public abstract class AbstractProxyFactory implements ProxyFactory {

    /**
     * Returns true if all <code>proxyClasses</code> are interfaces.
     *
     * @param proxyClasses the proxy classes
     * @return true if all <code>proxyClasses</code> are interfaces
     */
    @Override
    public boolean canProxy(Class<?>... proxyClasses) {
        for (Class cls : proxyClasses) {
            if (!cls.isInterface()) {//动态代理只能代理接口
                return false;
            }
        }

        return true;
    }

    /**
     * Creates a proxy which delegates to the object provided by <code>delegateProvider</code>. The proxy will be
     * generated using the current thread's "context class loader."
     *
     * @param delegateProvider the delegate provider
     * @param proxyClasses     the interfaces that the proxy should implement
     * @return a proxy which delegates to the object provided by the target object provider
     */
    @Override
    public <T> T createDelegatorProxy(ObjectProvider<?> delegateProvider, Class<?>... proxyClasses) {
        return createDelegatorProxy(Thread.currentThread().getContextClassLoader(), delegateProvider, proxyClasses);
    }

    /**
     * Creates a proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     * <code>target</code> object. The proxy will be generated using the current thread's "context class loader."
     *
     * @param target       the target object
     * @param interceptor  the method interceptor
     * @param proxyClasses the interfaces that the proxy should implement
     * @return a proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     * <code>target</code> object.
     */
    @Override
    public <T> T createInterceptorProxy(Object target, Interceptor interceptor, Class<?>... proxyClasses) {
        return createInterceptorProxy(Thread.currentThread().getContextClassLoader(), target, interceptor,
                proxyClasses);
    }

    /**
     * Creates a proxy which uses the provided {@link Invoker} to handle all method invocations. The proxy will be
     * generated using the current thread's "context class loader."
     *
     * @param invoker      the invoker
     * @param proxyClasses the interfaces that the proxy should implement
     * @return a proxy which uses the provided {@link Invoker} to handle all method invocations
     */
    @Override
    public <T> T createInvokerProxy(Invoker invoker, Class<?>... proxyClasses) {
        return createInvokerProxy(Thread.currentThread().getContextClassLoader(), invoker, proxyClasses);
    }

}
