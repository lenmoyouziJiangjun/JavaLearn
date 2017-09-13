package org.apache.commons.proxy2.interceptor;

import main.org.apache.commons.lang3.tuple.ImmutablePair;
import main.org.apache.commons.lang3.tuple.Pair;
import main.org.apache.commons.proxy2.Interceptor;
import org.apache.commons.proxy2.Invocation;
import org.apache.commons.proxy2.interceptor.matcher.InvocationMatcher;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SwitchInterceptor implements Interceptor, Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Pair<InvocationMatcher, Interceptor>> cases =
            new CopyOnWriteArrayList<>();

    public SwitchInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        for (Pair<InvocationMatcher, Interceptor> currentCase : cases) {
            if (currentCase.getLeft().matches(invocation)) {
                return currentCase.getRight().intercept(invocation);
            }
        }
        return invocation.proceed();
    }

    public CaseBuilder when(InvocationMatcher matcher) {
        return new CaseBuilder(matcher);
    }


    public class CaseBuilder {
        private final InvocationMatcher matcher;

        private CaseBuilder(InvocationMatcher matcher) {
            this.matcher = matcher;
        }

        public SwitchInterceptor then(Interceptor interceptor) {
            cases.add(new ImmutablePair<InvocationMatcher, Interceptor>(matcher, interceptor));
            return SwitchInterceptor.this;
        }

    }
}
