package org.apache.commons.proxy2.invoker.recorder;

import org.apache.commons.proxy2.Invoker;
import org.apache.commons.proxy2.ProxyFactory;
import org.apache.commons.proxy2.ProxyUtils;
import org.apache.commons.proxy2.invoker.RecordedInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class InvocationRecorder {
    private final ProxyFactory proxyFactory;
    private final List<RecordedInvocation> recordedInvocations = new LinkedList<>();


    public InvocationRecorder(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    /**
     * Get the invocations that have been recorded up to this point. The list is "live" and should not be modified.
     *
     * @return {@link List} of {@link RecordedInvocation}
     */
    public List<RecordedInvocation> getRecordedInvocations() {
        return recordedInvocations;
    }

    /**
     * Generate a recording proxy for the specified class.
     *
     * @param <T>
     * @param type
     * @return the generated proxy
     */
    public <T> T proxy(Class<T> type) {
        return proxy(type, type);
    }

    /**
     * @param genericType
     * @param proxyClass
     * @param <T>
     * @return
     */
    public <T> T proxy(Type genericType, Class<T> proxyClass) {
        if (proxyFactory.canProxy(proxyClass)) {
            return proxyFactory.createInvokerProxy(new InvocationRecorderInvoker(genericType), proxyClass);
        }
        return ProxyUtils.nullValue(proxyClass);
    }

    private final class InvocationRecorderInvoker implements Invoker {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 1L;

        private final Type targetType;

        private InvocationRecorderInvoker(Type targetType) {
            this.targetType = targetType;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            return null;
        }
    }

    /**
     * Reset this {@link InvocationRecorder}.
     */
    public void reset() {
        recordedInvocations.clear();
    }

}
