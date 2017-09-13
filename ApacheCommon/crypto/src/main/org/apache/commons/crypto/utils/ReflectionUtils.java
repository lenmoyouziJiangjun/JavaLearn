package org.apache.commons.crypto.utils;

import org.apache.commons.crypto.cipher.CryptoCipher;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Version 1.0
 * Created by lll on 17/9/5.
 * Description
 * <p>
 * General utility methods for working with reflection.
 * </p>
 * copyright generalray4239@gmail.com
 */
public class ReflectionUtils {

    private static final Map<ClassLoader, Map<String, WeakReference<Class<?>>>> CACHE_CLASSES = new WeakHashMap<>();

    private static final ClassLoader CLASSLOADER;

    static {
        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        CLASSLOADER = (threadClassLoader != null) ? threadClassLoader : CryptoCipher.class.getClassLoader();
    }

    /**
     * Sentinel value to store negative cache results in {@link #CACHE_CLASSES}.
     */
    private static final Class<?> NEGATIVE_CACHE_SENTINEL = NegativeCacheSentinel.class;

    /**
     * The private constructor of {@link ReflectionUtils}.
     */
    private ReflectionUtils() {
    }

    /**
     * A unique class which is used as a sentinel value in the caching for
     * getClassByName. {@link #getClassByNameOrNull(String)}.
     */
    private static abstract class NegativeCacheSentinel {
    }

    /**
     * Uses the constructor represented by this {@code Constructor} object to
     * create and initialize a new instance of the constructor's declaring
     * class, with the specified initialization parameters.
     *
     * @param <T>   type for the new instance
     * @param klass the Class object.
     * @param args  array of objects to be passed as arguments to the constructor
     *              call.
     * @return a new object created by calling the constructor this object
     * represents.
     */
    public static <T> T newInstance(Class<T> klass, Object... args) {
        try {
            Constructor<T> ctor;
            if (args.length == 0) {
                ctor = klass.getDeclaredConstructor();
            } else {
                Class<?>[] argClses = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argClses[i] = args[i].getClass();
                }
                ctor = klass.getDeclaredConstructor(argClses);
            }
            ctor.setAccessible(true);
            return ctor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a class by name.
     *
     * @param name the class name.
     * @return the class object.
     * @throws ClassNotFoundException if the class is not found.
     */
    public static Class<?> getClassByName(String name)
            throws ClassNotFoundException {
        Class<?> ret = getClassByNameOrNull(name);
        if (ret == null) {
            throw new ClassNotFoundException("Class " + name + " not found");
        }
        return ret;
    }

    /**
     * Loads a class by name, returning null rather than throwing an exception
     * if it couldn't be loaded. This is to avoid the overhead of creating an
     * exception.
     *
     * @param name the class name.
     * @return the class object, or null if it could not be found.
     */
    private static Class<?> getClassByNameOrNull(String name) {
        Map<String, WeakReference<Class<?>>> map;
        synchronized (CACHE_CLASSES) {
            map = CACHE_CLASSES.get(CLASSLOADER);
            if (map == null) {
                map = Collections.synchronizedMap(new WeakHashMap<String, WeakReference<Class<?>>>());
                CACHE_CLASSES.put(CLASSLOADER, map);
            }
        }
        Class<?> clazz = null;
        WeakReference<Class<?>> ref = map.get(name);
        if (ref != null) {
            clazz = ref.get();
        }
        if (clazz == null) {
            try {
                clazz = Class.forName(name, true, CLASSLOADER);
            } catch (ClassNotFoundException e) {
                // Leave a marker that the class isn't found
                map.put(name, new WeakReference<Class<?>>(NEGATIVE_CACHE_SENTINEL));
                return null;
            }
            // two putters can race here, but they'll put the same class
            map.put(name, new WeakReference<Class<?>>(clazz));
            return clazz;
        } else if (clazz == NEGATIVE_CACHE_SENTINEL) {
            return null; // not found
        } else {
            // cache hit
            return clazz;
        }
    }


}
