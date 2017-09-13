package org.apache.commons.proxy2.provider;

import org.apache.commons.proxy2.ObjectProvider;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * Returns the result of the inner {@link ObjectProvider provider}. Subclasses can override the {@link #getObject()}
 * method and decorate what comes back from the inner provider in some way (by {@link SingletonProvider caching it} for
 * example).
 * copyright generalray4239@gmail.com
 */
public class ProviderDecorator<T> implements ObjectProvider<T> {

    private static final long serialVersionUID = 1L;

    /**
     * The wrapped {@link ObjectProvider}.
     */
    private ObjectProvider<? extends T> inner;

    /**
     * Create a new ProviderDecorator instance.
     *
     * @param inner
     */
    public ProviderDecorator(ObjectProvider<? extends T> inner) {
        this.inner = inner;
    }

    //******************************************************************************************************************
    // ObjectProvider Implementation
    //******************************************************************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public T getObject() {
        return inner.getObject();
    }

    //******************************************************************************************************************
    // Getter/Setter Methods
    //******************************************************************************************************************

    protected ObjectProvider<? extends T> getInner() {
        return inner;
    }

    public void setInner(ObjectProvider<? extends T> inner) {
        this.inner = inner;
    }
}
