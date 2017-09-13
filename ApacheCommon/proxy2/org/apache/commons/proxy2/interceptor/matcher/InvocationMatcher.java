package org.apache.commons.proxy2.interceptor.matcher;

import org.apache.commons.proxy2.Invocation;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * An {@link InvocationMatcher} is used to conditionally match {@link Invocation} objects based on some criteria such as
 * method name, parameter values, etc.
 * copyright generalray4239@gmail.com
 */
public interface InvocationMatcher {

    boolean matches(Invocation invocation);

}
