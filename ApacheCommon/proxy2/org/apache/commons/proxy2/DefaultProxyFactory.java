package org.apache.commons.proxy2;

import java.util.Arrays;
import java.util.ServiceLoader;

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description
 *
 * copyright generalray4239@gmail.com
 */
public class DefaultProxyFactory implements ProxyFactory {

    static final DefaultProxyFactory INSTANCE = new DefaultProxyFactory();

    /**
     * ServiceLoader 的使用：http://blog.csdn.net/hintcnuie/article/details/37922089
     *        加载当前工程目录：META-INF/services/在的文件类
     */
    private static final ServiceLoader<ProxyFactory> SERVICES = ServiceLoader.load(ProxyFactory.class);

    @Override
    public boolean canProxy(Class<?>... proxyClasses) {
        for(ProxyFactory proxyFactory:SERVICES){
            if(proxyFactory.canProxy(proxyClasses)){
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> T createDelegatorProxy(ObjectProvider<?> delegateProvider, Class<?>... proxyClasses) {
        final T result = (T) getCapableProxyFactory(proxyClasses).createDelegatorProxy(delegateProvider, proxyClasses);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createDelegatorProxy(ClassLoader classLoader, ObjectProvider<?> delegateProvider,
                                      Class<?>... proxyClasses)
    {
        @SuppressWarnings("unchecked") // type inference
        final T result = (T) getCapableProxyFactory(proxyClasses).createDelegatorProxy(classLoader, delegateProvider,
                proxyClasses);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createInterceptorProxy(Object target, Interceptor interceptor, Class<?>... proxyClasses)
    {
        @SuppressWarnings("unchecked") // type inference
        final T result = (T) getCapableProxyFactory(proxyClasses).createInterceptorProxy(target, interceptor,
                proxyClasses);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createInterceptorProxy(ClassLoader classLoader, Object target, Interceptor interceptor,
                                        Class<?>... proxyClasses)
    {
        @SuppressWarnings("unchecked") // type inference
        final T result = (T) getCapableProxyFactory(proxyClasses).createInterceptorProxy(classLoader, target,
                interceptor, proxyClasses);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createInvokerProxy(Invoker invoker, Class<?>... proxyClasses)
    {
        @SuppressWarnings("unchecked") // type inference
        final T result = (T) getCapableProxyFactory(proxyClasses).createInvokerProxy(invoker, proxyClasses);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T createInvokerProxy(ClassLoader classLoader, Invoker invoker, Class<?>... proxyClasses)
    {
        @SuppressWarnings("unchecked") // type inference
        final T result = (T) getCapableProxyFactory(proxyClasses)
                .createInvokerProxy(classLoader, invoker, proxyClasses);
        return result;
    }

    private ProxyFactory getCapableProxyFactory(Class<?>... proxyClasses){
        for(ProxyFactory proxyFactory:SERVICES){
            if (proxyFactory.canProxy(proxyClasses))
            {
                return proxyFactory;
            }
        }
        throw new IllegalArgumentException("Could not proxy " + Arrays.toString(proxyClasses));
    }
}
