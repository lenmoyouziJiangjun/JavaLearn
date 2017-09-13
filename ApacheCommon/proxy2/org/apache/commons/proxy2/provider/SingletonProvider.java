package org.apache.commons.proxy2.provider;

import org.apache.commons.proxy2.ObjectProvider;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * Wraps another object provider, making sure to only call it once, returning the value returned from the wrapped
 * provider on all subsequent invocations.
 * copyright generalray4239@gmail.com
 */
public class SingletonProvider<T> extends ProviderDecorator<T> {

    private T instance;

    /**
     * Create a new ProviderDecorator instance.
     *
     * @param inner
     */
    public SingletonProvider(ObjectProvider<? extends T> inner) {
        super(inner);
    }

    @Override
    public T getObject() {
        synchronized (this) {
            if (instance == null) {
                instance = super.getObject();
                setInner(null);
            }
            return instance;
        }
    }
}
