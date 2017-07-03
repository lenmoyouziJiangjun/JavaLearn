package main.org.apache.commons.proxy2.stub;

import main.org.apache.commons.lang3.ArrayUtils;
import main.org.apache.commons.proxy2.*;
import main.org.apache.commons.proxy2.interceptor.SwitchInterceptor;
import main.org.apache.commons.proxy2.interceptor.matcher.ArgumentMatcher;
import main.org.apache.commons.proxy2.interceptor.matcher.InvocationMatcher;
import main.org.apache.commons.proxy2.invoker.NullInvoker;
import main.org.apache.commons.proxy2.invoker.RecordedInvocation;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class TrainingContext {

    private static final ThreadLocal<TrainingContext> TRAINING_CONTEXT = new ThreadLocal<>();

    private final ProxyFactory proxyFactory;

    private final Deque<TrainingContextFrame<?>> frameDeque = new LinkedList<TrainingContextFrame<?>>();

    private final TrainingContext resume;

    private TrainingContext(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
        this.resume = current();
    }

    static TrainingContext current() {
        return TRAINING_CONTEXT.get();
    }

    static synchronized TrainingContext join(ProxyFactory factory) {
        TrainingContext context = new TrainingContext(factory);
        TRAINING_CONTEXT.set(context);
        return context;
    }

    void part() {
        synchronized (TRAINING_CONTEXT) {
            if (resume == null) {
                TRAINING_CONTEXT.remove();
            } else {
                TRAINING_CONTEXT.set(resume);
            }
        }
    }

    private TrainingContextFrame<?> peek() {
        return frameDeque.peek();
    }

    <T> T pop() {
        return pop(NullInvoker.INSTANCE);
    }

    <T> T pop(Invoker invoker) {
        final TrainingContextFrame<?> frame = frameDeque.pop();
        return proxyFactory.createInterceptorProxy(proxyFactory.createInvokerProxy(invoker, frame.type),
                frame.stubInterceptor, frame.type);
    }

    <T> T push(Class<T> type) {
        return push(type, new SwitchInterceptor());
    }

    <T> T push(Class<T> type, SwitchInterceptor switchInterceptor) {
        TrainingContextFrame<T> frame = new TrainingContextFrame<T>(type, switchInterceptor);
        Invoker invoker = new TrainingInvoker(frame);
        frameDeque.push(frame);
        return proxyFactory.createInvokerProxy(invoker, type);
    }

    void record(ArgumentMatcher<?> argumentMatcher) {
        peek().argumentMatchers.add(argumentMatcher);
    }

    void then(Interceptor interceptor) {
        peek().then(interceptor);
    }

    private static final class ExactArgumentsMatcher implements InvocationMatcher {

        private final RecordedInvocation recordedInvocation;

        public ExactArgumentsMatcher(RecordedInvocation invocation) {
            this.recordedInvocation = invocation;
        }

        @Override
        public boolean matches(Invocation invocation) {
            return invocation.getMethod().equals(recordedInvocation.getInvokedMethod())
                    && Arrays.deepEquals(invocation.getArguments(), recordedInvocation.getArguments());
        }
    }

    private static final class MatchingArgumentsMatcher implements InvocationMatcher {
        private final RecordedInvocation recordedInvocation;
        private final ArgumentMatcher<?>[] matchers;

        private MatchingArgumentsMatcher(RecordedInvocation recordedInvocation, ArgumentMatcher<?>[] matchers) {
            this.recordedInvocation = recordedInvocation;
            this.matchers = ArrayUtils.clone(matchers);
        }

        @Override
        public boolean matches(Invocation invocation) {
            return invocation.getMethod().equals(recordedInvocation.getInvokedMethod())
                    && allArgumentsMatch(invocation.getArguments());
        }

        private boolean allArgumentsMatch(Object[] arguments) {
            for (int i = 0; i < arguments.length; i++) {
                Object argument = arguments[i];
                @SuppressWarnings({"rawtypes", "unchecked"}) // we can't know generic argument types
                final boolean matches = ((ArgumentMatcher) matchers[i]).matches(argument);
                if (!matches) {
                    return false;
                }
            }
            return true;
        }
    }


    private static final class TrainingContextFrame<T> {
        private final String id = UUID.randomUUID().toString();

        private final SwitchInterceptor stubInterceptor;

        private final List<ArgumentMatcher<?>> argumentMatchers = new LinkedList<>();

        private InvocationMatcher matcher = null;

        private final Class<T> type;

        private TrainingContextFrame(Class<T> type, SwitchInterceptor stubInterceptor) {
            this.type = type;
            this.stubInterceptor = stubInterceptor;
        }

        private String getId() {
            return id;
        }

        void then(Interceptor interceptor) {
            if (matcher == null) {
                throw new IllegalStateException("No when!");
            }
            stubInterceptor.when(matcher).then(interceptor);
            matcher = null;
        }

        void methodInvoked(Method method, Object[] arguments) {
            final ArgumentMatcher<?>[] matchersArray = argumentMatchers.toArray(new ArgumentMatcher[argumentMatchers
                    .size()]);
            argumentMatchers.clear();
            final RecordedInvocation invocation = new RecordedInvocation(method, arguments);
            if (ArrayUtils.isEmpty(matchersArray)) {
                this.matcher = new ExactArgumentsMatcher(invocation);
            } else if (matchersArray.length == arguments.length) {
                this.matcher = new MatchingArgumentsMatcher(invocation, matchersArray);
            } else {
                throw new IllegalStateException("Either use exact arguments or argument matchers, but not both.");
            }
        }

    }

    private static final class TrainingInvoker implements Invoker {
        private static final long serialVersionUID = 1L;

        private final String id;

        private TrainingInvoker(TrainingContextFrame<?> frame) {
            this.id = frame.getId();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            final TrainingContextFrame<?> frame = current().peek();
            if (!frame.getId().equals(id)) {
                throw new IllegalStateException("Wrong stub!");
            } else {
                frame.methodInvoked(method, arguments);
            }

            final Class<?> type = method.getReturnType();

            if (Object[].class.isAssignableFrom(type)) {
                return Array.newInstance(type.getComponentType(), 0);
            }
            return ProxyUtils.nullValue(type);
        }
    }

}
