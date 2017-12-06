package org.apache.commons.proxy2;/**
 * Created by liaoxueyan on 17/6/28.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description 抽象工厂，负责生成Delegator,Interceptor,Invoker
 * copyright generalray4239@gmail.com
 */
public interface ProxyFactory {

    /**
     * Learn whether this {@link ProxyFactory} is capable of creating a javapattern.proxy for the specified set of classes.
     *
     * @param proxyClasses
     * @return
     */
    boolean canProxy(Class<?>... proxyClasses);

    /**
     * Creates a javapattern.proxy which delegates to the object provided by <code>delegateProvider</code>. The javapattern.proxy will be
     * generated using the current thread's "context class loader."
     *
     * @param delegateProvider the delegate provider
     * @param proxyClasses     the interfaces that the javapattern.proxy should implement
     * @return a javapattern.proxy which delegates to the object provided by the target object provider
     */
    <T> T createDelegatorProxy(ObjectProvider<?> delegateProvider, Class<?>... proxyClasses);

    /**
     * Creates a javapattern.proxy which delegates to the object provided by <code>delegateProvider</code>.
     *
     * @param classLoader      the class loader to use when generating the javapattern.proxy
     * @param delegateProvider the delegate provider
     * @param proxyClasses     the interfaces that the javapattern.proxy should implement
     * @return a javapattern.proxy which delegates to the object provided by the target <code>delegateProvider>
     */
    <T> T createDelegatorProxy(ClassLoader classLoader, ObjectProvider<?> delegateProvider, Class<?>... proxyClasses);


    /**
     * Creates a javapattern.proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     * <code>target</code> object. The javapattern.proxy will be generated using the current thread's "context class loader."
     *
     * @param target
     *            the target object
     * @param interceptor
     *            the method interceptor
     * @param proxyClasses
     *            the interfaces that the javapattern.proxy should implement
     * @return a javapattern.proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     *         <code>target</code> object.
     */
    <T> T createInterceptorProxy(Object target,Interceptor interceptor,Class<?>... proxyClasses);


    /**
     * Creates a javapattern.proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     * <code>target</code> object.
     *
     * @param classLoader
     *            the class loader to use when generating the javapattern.proxy
     * @param target
     *            the target object
     * @param interceptor
     *            the method interceptor
     * @param proxyClasses
     *            the interfaces that the javapattern.proxy should implement.
     * @return a javapattern.proxy which passes through a {@link Interceptor interceptor} before eventually reaching the
     *         <code>target</code> object.
     */
    <T> T createInterceptorProxy(ClassLoader classLoader, Object target, Interceptor interceptor,
                                 Class<?>... proxyClasses);

    /**
     * Creates a javapattern.proxy which uses the provided {@link Invoker} to handle all method invocations. The javapattern.proxy will be
     * generated using the current thread's "context class loader."
     *
     * @param invoker
     *            the invoker
     * @param proxyClasses
     *            the interfaces that the javapattern.proxy should implement
     * @return a javapattern.proxy which uses the provided {@link Invoker} to handle all method invocations
     */
    <T> T createInvokerProxy(Invoker invoker, Class<?>... proxyClasses);

    /**
     * Creates a javapattern.proxy which uses the provided {@link Invoker} to handle all method invocations.
     *
     * @param classLoader
     *            the class loader to use when generating the javapattern.proxy
     * @param invoker
     *            the invoker
     * @param proxyClasses
     *            the interfaces that the javapattern.proxy should implement
     * @return a javapattern.proxy which uses the provided {@link Invoker} to handle all method invocations
     */
    <T> T createInvokerProxy(ClassLoader classLoader, Invoker invoker, Class<?>... proxyClasses);

}
