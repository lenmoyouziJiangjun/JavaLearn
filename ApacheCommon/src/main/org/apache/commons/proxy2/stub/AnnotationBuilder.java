package main.org.apache.commons.proxy2.stub;

import com.sun.istack.internal.NotNull;
import main.org.apache.commons.lang3.AnnotationUtils;
import main.org.apache.commons.lang3.Validate;
import main.org.apache.commons.proxy2.*;
import main.org.apache.commons.proxy2.impl.AbstractProxyFactory;
import main.org.apache.commons.proxy2.provider.ObjectProviderUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Version 1.0
 * Created by lll on 17/7/3.
 * Description
 * copyright generalray4239@gmail.com
 */
public class AnnotationBuilder<A extends Annotation> extends StubBuilder<A> {



    public static <A extends Annotation> A buildDefault(Class<A> type)
    {
        return of(type).build();
    }

    public static <A extends Annotation> AnnotationBuilder<A> of(Class<A> type)
    {
        return new AnnotationBuilder<A>(type, AnnotationInvoker.INSTANCE);
    }

    public static <A extends Annotation> AnnotationBuilder<A> of(Class<A> type, ObjectProvider<? extends A> provider)
    {
        return new AnnotationBuilder<A>(type, provider);
    }

    public static <A extends Annotation> AnnotationBuilder<A> of(Class<A> type, A target)
    {
        return new AnnotationBuilder<A>(type, target);
    }

    private final Class<A> annotationType;

    private AnnotationBuilder(Class<A> type, Invoker invoker)
    {
        super(PROXY_FACTORY, type, invoker);
        this.annotationType = type;
        train(new AnnotationTypeTrainer<A>(type));
    }

    private AnnotationBuilder(Class<A> type, ObjectProvider<? extends A> provider)
    {
        super(PROXY_FACTORY, type, provider);
        this.annotationType = type;
        train(new AnnotationTypeTrainer<A>(type));
    }

    private AnnotationBuilder(Class<A> type, A target)
    {
        super(PROXY_FACTORY, type, target);
        this.annotationType = type;
        train(new AnnotationTypeTrainer<A>(type));
    }

    public AnnotationBuilder<A> withMembers(Map<String, ?> members)
    {
        return train(new MapAnnotationTrainer(members));
    }

    @Override
    public <O> AnnotationBuilder<A> train(BaseTrainer<?, O> trainer)
    {
        return (AnnotationBuilder<A>) super.train(trainer);
    }


    private static final ProxyFactory PROXY_FACTORY = new AbstractProxyFactory() {

        @Override
        public <T> T createDelegatorProxy(ClassLoader classLoader, ObjectProvider<?> delegateProvider, Class<?>... proxyClasses) {

            final T result = (T) Proxy.newProxyInstance(classLoader, proxyClasses, new InterceptorIncovationHandler(delegateProvider, new Interceptor() {
                @Override
                public Object intercept(Invocation invocation) throws Throwable {
                    return invocation.proceed();
                }
            }));

            return result;
        }

        @Override
        public <T> T createInterceptorProxy(ClassLoader classLoader, Object target, Interceptor interceptor, Class<?>... proxyClasses) {

            final T result = (T) Proxy.newProxyInstance(classLoader, proxyClasses, new InterceptorIncovationHandler(ObjectProviderUtils.constant(target), interceptor));
            return result;
        }

        @Override
        public <T> T createInvokerProxy(ClassLoader classLoader, Invoker invoker, Class<?>... proxyClasses) {

            final T result = (T) Proxy.newProxyInstance(classLoader, proxyClasses, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return invoker.invoke(proxy, method, args);
                }
            });

            return result;
        }
    };


    private static class InterceptorIncovationHandler implements InvocationHandler, Serializable {

        private final ObjectProvider<?> provider;
        private final Interceptor methodInterceptor;

        public InterceptorIncovationHandler(ObjectProvider<?> provider, Interceptor methodInterceptor) {
            this.provider = provider;
            this.methodInterceptor = methodInterceptor;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (ProxyUtils.isHashCode(method)) {// hashCode method
                return Integer.valueOf(AnnotationUtils.hashCode((Annotation) proxy));
            }
            if (ProxyUtils.isEqualsMethod(method)) {//equals method
                return Boolean.valueOf(args[0] instanceof Annotation
                        && AnnotationUtils.equals((Annotation) proxy, (Annotation) args[0]));
            }
            if ("toString".equals(method.getName()) && method.getParameterTypes().length == 0) {
                return AnnotationUtils.toString((Annotation) proxy);
            }
            final ReflectionInvocation invocation = new ReflectionInvocation(provider.getObject(),method, args);
            return methodInterceptor.intercept(invocation);
        }
    }

    private static class ReflectionInvocation implements Invocation {

        private final Method method;
        private final Object[] arguments;
        private final Object target;

        public ReflectionInvocation(@NotNull Object target, @NotNull Method method, @NotNull Object[] arguments) {
            this.target = target;
            this.method = method;
            this.arguments = arguments;
        }


        @Override
        public Object[] getArguments() {
            return arguments;
        }

        @Override
        public Method getMethod() {
            return method;
        }

        @Override
        public Object getProxy() {
            return target;
        }

        @Override
        public Object proceed() throws Throwable {

            try {
                method.invoke(target, arguments);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
            return null;
        }
    }

    private class MapAnnotationTrainer extends AnnotationTrainer<A>
    {
        private final Map<String, ?> members;

        MapAnnotationTrainer(Map<String, ?> members)
        {
            super(annotationType);
            this.members = members;
        }

        @Override
        protected void train(A trainee)
        {
            WhenObject<Object> bud;
            AnnotationTrainer<A> dy = this;
            for (Map.Entry<String, ?> attr : members.entrySet())
            {
                final Method m;
                try
                {
                    m = traineeType.getDeclaredMethod(attr.getKey());
                }
                catch (Exception e1)
                {
                    throw new IllegalArgumentException(String.format("Could not detect annotation member %1$s",
                            attr.getKey()));
                }
                try
                {
                    bud = dy.when(m.invoke(trainee));
                }
                catch (Exception e)
                {
                    // it must have happened on the invoke, so we didn't call
                    // when... it shouldn't happen, but we'll simply skip:
                    continue;
                }
                final Object value = attr.getValue();
                Validate.isTrue(TypeUtils.isInstance(value, m.getReturnType()), "Value %s can not be assigned to %s",
                        value, m.getReturnType());
                dy = bud.thenReturn(value);
            }
        }
    }




}
