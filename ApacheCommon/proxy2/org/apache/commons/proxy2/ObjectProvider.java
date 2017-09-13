package org.apache.commons.proxy2;


import org.apache.commons.proxy2.exception.ObjectProviderException;

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description Provides an object to a delegating proxy.
 * copyright generalray4239@gmail.com
 */
public interface ObjectProvider<T> {

    /**
     * Returns an object. Implementing classes should throw a
     * {@link ObjectProviderException} if any problems arise while
     * constructing/finding the object.
     *
     * @return the object on which the method should be called
     */
    T getObject();
}
