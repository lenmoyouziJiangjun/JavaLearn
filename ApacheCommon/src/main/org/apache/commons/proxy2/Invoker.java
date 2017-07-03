package main.org.apache.commons.proxy2;

import main.org.apache.commons.proxy2.exception.InvokerException;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description
 * copyright generalray4239@gmail.com
 */
public interface Invoker extends Serializable {
    /**
     * "Invokes" the method. Implementation should throw a {@link InvokerException}
     * if problems arise while trying to invoke the method.
     *
     * @param proxy     the proxy2 object
     * @param method    the method being invoked
     * @param arguments the arguments
     * @return the return value
     * @throws Throwable thrown by the implementation
     */
    Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable;
}
