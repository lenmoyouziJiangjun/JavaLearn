package org.apache.commons.proxy2.provider;

import org.apache.commons.proxy2.ObjectProvider;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObjectProviderUtils {

    private ObjectProviderUtils() {
    }

    public static <T> ObjectProvider<T> bean(Class<T> beanClass) {
        return new BeanProvider<T>(beanClass);
    }

    public static <T extends Cloneable> ObjectProvider<T> cloning(T prototype) {
        return new CloningProvider<T>(prototype);
    }

    public static <T> ObjectProvider<T> constant(T value) {
        return new ConstantProvider<T>(value);
    }

    public static <T> ObjectProvider<T> nullValue() {
        return new NullProvider<T>();
    }

    public static <T> ObjectProvider<T> singleton(ObjectProvider<T> inner) {
        return new SingletonProvider<T>(inner);
    }
}
