package com.google.common.base;/**
 * Created by liaoxueyan on 2019/2/26.
 */

import com.google.common.annotations.GwtIncompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * Pluggable interface for compiling a regex pattern. By default this package uses the
 * {@code java.util.regex} library, but an alternate implementation can be supplied
 * using the {@link java.util.ServiceLoader} mechanism.
 * copyright generalray4239@gmail.com
 */
@GwtIncompatible
public interface PatternCompiler {
    /**
     * Compiles the given pattern.
     *
     * @throws IllegalArgumentException if the pattern is invalid
     */
    CommonPattern compile(String pattern);
}
