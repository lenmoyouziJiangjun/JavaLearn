package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.sun.istack.internal.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible(emulated = true)
public class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final PatternCompiler patternCompiler = loadPatternCompiler();


    private Platform() {}

    /** Calls {@link System#nanoTime()}. */
    static long systemNanoTime() {
        return System.nanoTime();
    }

    static CharMatcher precomputeCharMatcher(CharMatcher matcher) {
        return matcher.precomputedInternal();
    }

    static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> enumClass, String value) {
        WeakReference<? extends Enum<?>> ref = Enums.getEnumConstants(enumClass).get(value);
        return ref == null
                ? Optional.<T>absent()
                : Optional.of(enumClass.cast(ref.get()));
    }

    static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    static boolean stringIsNullOrEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    static CommonPattern compilePattern(String pattern) {
        Preconditions.checkNotNull(pattern);
        return patternCompiler.compile(pattern);
    }

    static boolean usingJdkPatternCompiler() {
        return patternCompiler instanceof JdkPatternCompiler;
    }

    private static PatternCompiler loadPatternCompiler() {
        ServiceLoader<PatternCompiler> loader = ServiceLoader.load(PatternCompiler.class);
        // Returns the first PatternCompiler that loads successfully.
        try {
            for (Iterator<PatternCompiler> it = loader.iterator(); it.hasNext();) {
                try {
                    return it.next();
                } catch (ServiceConfigurationError e) {
                    logPatternCompilerError(e);
                }
            }
        } catch (ServiceConfigurationError e) { // from hasNext()
            logPatternCompilerError(e);
        }
        // Fall back to the JDK regex library.
        return new JdkPatternCompiler();
    }

    private static void logPatternCompilerError(ServiceConfigurationError e) {
        logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", e);
    }

    private static final class JdkPatternCompiler implements PatternCompiler {
        @Override
        public CommonPattern compile(String pattern) {
            return new JdkPattern(Pattern.compile(pattern));
        }
    }
}
