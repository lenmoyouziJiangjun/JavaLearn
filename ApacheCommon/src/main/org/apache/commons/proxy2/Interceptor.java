package main.org.apache.commons.proxy2;

import java.io.Serializable;

/**
 * Version 1.0
 * Created by lll on 17/6/28.
 * Description
 * copyright generalray4239@gmail.com
 */
public interface Interceptor extends Serializable {
    /**
     * Intercept the specified {@link Invocation}.
     *
     * @param invocation
     * @return return value of the method
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;
}
