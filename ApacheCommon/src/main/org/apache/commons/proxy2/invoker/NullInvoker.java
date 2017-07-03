package main.org.apache.commons.proxy2.invoker;

import main.org.apache.commons.proxy2.Invoker;
import main.org.apache.commons.proxy2.ProxyUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class NullInvoker implements Invoker, Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 1L;


    /**
     * Statically available instance.
     */
    public static final NullInvoker INSTANCE = new NullInvoker();



    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        final Class<?> returnType = method.getReturnType();
        return ProxyUtils.nullValue(returnType);//返回returnType
    }
}
