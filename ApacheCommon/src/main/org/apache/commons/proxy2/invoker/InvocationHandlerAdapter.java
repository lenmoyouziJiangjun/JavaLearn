package main.org.apache.commons.proxy2.invoker;

import main.org.apache.commons.proxy2.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class InvocationHandlerAdapter implements Invoker {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 1L;

    private final InvocationHandler invocationHandler;


    public InvocationHandlerAdapter(InvocationHandler invocationHandler) {
        this.invocationHandler = invocationHandler;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        return invocationHandler.invoke(proxy, method, arguments);
    }
}
