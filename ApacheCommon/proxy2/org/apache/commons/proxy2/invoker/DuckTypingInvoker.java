package org.apache.commons.proxy2.invoker;

import org.apache.commons.proxy2.Invoker;
import org.apache.commons.proxy2.ObjectProvider;

import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * An invoker which supports <a href="http://en.wikipedia.org/wiki/Duck_typing">&quot;duck typing&quot;</a>, meaning
 * that it finds a matching method on the object returned from the target provider and invokes it. This class is useful
 * for adapting an existing class to an interface it does not implement.
 * <p>
 * <b>Example:</b>
 * </p>
 * <p>
 * <p>
 * <pre>
 * public class LegacyDuck // Does not implement interface!
 * {
 *   public void quack()
 *   {
 *     // Quacking logic...
 *   }
 * }
 * <p/>
 * public interface Duck
 * {
 *   public void quack();
 * }
 * <p/>
 * ObjectProvider targetProvider = new ConstantProvider(new LegacyDuck()); // Always returns a "legacy" duck
 * DuckTypingInvoker invoker = new DuckTypingInvoker(targetProvider);
 * Duck duck = ( Duck )proxyFactory.createInvokerProxy( invoker, new Class[] { Duck.class } );
 * </pre>
 * <p>
 * </p>
 * copyright generalray4239@gmail.com
 */
public class DuckTypingInvoker implements Invoker {

    private static final long serialVersionUID = 1L;

    private final ObjectProvider<?> targetProvider;

    public DuckTypingInvoker(ObjectProvider<?> targetProvider) {
        this.targetProvider = targetProvider;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        final Object target = targetProvider.getObject();
        final Class<?> targetClass = target.getClass();
        try {
            final Method targetMethod = targetClass.getMethod(method.getName(), method.getParameterTypes());
            if (method.getReturnType().isAssignableFrom(targetMethod.getReturnType())) {
                return targetMethod.invoke(target, arguments);
            }
            throw new UnsupportedOperationException("Target type " + targetClass.getName()
                    + " method has incompatible return type.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Target type " + targetClass.getName()
                    + " does not have a method matching " + method + "", e);
        }
    }
}
