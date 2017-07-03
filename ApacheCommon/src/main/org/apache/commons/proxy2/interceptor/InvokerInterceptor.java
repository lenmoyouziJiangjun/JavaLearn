package main.org.apache.commons.proxy2.interceptor;

import com.sun.istack.internal.NotNull;
import main.org.apache.commons.proxy2.Interceptor;
import main.org.apache.commons.proxy2.Invocation;
import main.org.apache.commons.proxy2.Invoker;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class InvokerInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    private final Invoker invoker;

    public InvokerInterceptor(@NotNull Invoker invoker) {
        super();
        this.invoker = invoker;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invoker.invoke(invocation.getProxy(), invocation.getMethod(), invocation.getArguments());
    }
}
