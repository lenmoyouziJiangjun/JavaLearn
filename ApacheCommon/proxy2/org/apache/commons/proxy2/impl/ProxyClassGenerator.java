package org.apache.commons.proxy2.impl;

import org.apache.commons.proxy2.ProxyFactory;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * A javapattern.proxy class generator generates Class instances for a given combination of interfaces in a given classloader.
 * Typically, in the context of a {@link ProxyFactory} implementation, an instance will
 * generate javapattern.proxy class instances for a specific type of proxies (interceptor, invoker, etc.) and will be associated
 * with a corresponding {@link ProxyClassCache}.
 * copyright generalray4239@gmail.com
 */
public interface ProxyClassGenerator {

    /**
     * Generates a javapattern.proxy class for the supplied {@link ClassLoader} and javapattern.proxy classes.
     *
     * @param classLoader
     * @param proxyClasses
     * @return
     */
    Class<?> generateProxyClass(ClassLoader classLoader, Class<?>... proxyClasses);
}
