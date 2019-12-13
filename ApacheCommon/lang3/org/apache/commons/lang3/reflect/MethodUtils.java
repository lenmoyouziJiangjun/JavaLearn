package main.org.apache.commons.lang3.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * <p>
 * * <p>Utility reflection methods focused on {@link Method}s, originally from Commons BeanUtils.
 * Differences from the BeanUtils version may be noted, especially where similar functionality
 * already existed within Lang.
 * </p>
 * <p>
 * <h3>Known Limitations</h3>
 * <h4>Accessing Public Methods In A Default Access Superclass</h4>
 * <p>There is an issue when invoking {@code public} methods contained in a default access superclass on JREs prior to 1.4.
 * Reflection locates these methods fine and correctly assigns them as {@code public}.
 * However, an {@link IllegalAccessException} is thrown if the method is invoked.</p>
 * <p>
 * <p>{@link MethodUtils} contains a workaround for this situation.
 * It will attempt to call {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} on this method.
 * If this call succeeds, then the method can be invoked as normal.
 * This call will only succeed when the application has sufficient security privileges.
 * If this call fails then the method may fail.</p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class MethodUtils {

  /**
   * <p>Returns an accessible method (that is, one that can be invoked via
   * reflection) with given name and parameters. If no such method
   * can be found, return {@code null}.
   * This is just a convenience wrapper for
   * {@link #getAccessibleMethod(Method)}.</p>
   *
   * @param cls            get method from this class
   * @param methodName     get method with this name
   * @param parameterTypes with these parameters types
   * @return The accessible method
   */
  public static Method getAccessibleMethod(final Class<?> cls, final String methodName,
                                           final Class<?>... parameterTypes) {
    try {
      Method method = cls.getMethod(methodName, parameterTypes);
      return getAccessibleMethod(method);
    } catch (NoSuchMethodException e) {
      return null;
    }
  }


  /**
   * <p>Returns an accessible method (that is, one that can be invoked via
   * reflection) that implements the specified Method. If no such method
   * can be found, return {@code null}.</p>
   *
   * @param method The method that we wish to call
   * @return The accessible method
   */
  public static Method getAccessibleMethod(Method method) {
    if (!MemberUtils.isAccessible(method)) {
      return null;
    }
    final Class<?> cls = method.getDeclaringClass();
    if (Modifier.isPublic(method.getModifiers())) {
      return method;
    }
    final String methodName = method.getName();
    final Class<?>[] parameterTypes = method.getParameterTypes();

    // Check the implemented interfaces and subinterfaces
    method = getAccessibleMethodFromInterfaceNest(cls, methodName,
            parameterTypes);

    if (method == null) {
      method = getAccessibleMethodFromSuperclass(cls, methodName,
              parameterTypes);
    }
    return method;
  }

  /**
   * <p>Returns an accessible method (that is, one that can be invoked via
   * reflection) that implements the specified method, by scanning through
   * all implemented interfaces and subinterfaces. If no such method
   * can be found, return {@code null}.</p>
   * <p>
   * <p>There isn't any good reason why this method must be {@code private}.
   * It is because there doesn't seem any reason why other classes should
   * call this rather than the higher level methods.</p>
   *
   * @param cls            Parent class for the interfaces to be checked
   * @param methodName     Method name of the method we wish to call
   * @param parameterTypes The parameter type signatures
   * @return the accessible method or {@code null} if not found
   */
  private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, final String methodName,
                                                             final Class<?>... parameterTypes) {
    // Search up the superclass chain
    for (; cls != null; cls = cls.getSuperclass()) {
      // Check the implemented interfaces of the parent class
      final Class<?>[] interfaces = cls.getInterfaces();
      for (Class clazz : interfaces) {
        if (!Modifier.isPublic(clazz.getModifiers())) {
          continue;
        }
        try {
          return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
        //递归上一级
        final Method method = getAccessibleMethodFromInterfaceNest(clazz,
                methodName, parameterTypes);
        if (method != null) {
          return method;
        }
      }
    }
    return null;
  }

  /**
   * <p>Returns an accessible method (that is, one that can be invoked via
   * reflection) by scanning through the superclasses. If no such method
   * can be found, return {@code null}.</p>
   *
   * @param cls            Class to be checked
   * @param methodName     Method name of the method we wish to call
   * @param parameterTypes The parameter type signatures
   * @return the accessible method or {@code null} if not found
   */
  public static Method getAccessibleMethodFromSuperclass(Class<?> cls, final String methodName,
                                                         final Class<?>... parameterTypes) {
    Class<?> parentClass = cls.getSuperclass();
    while (parentClass != null) {
      if (Modifier.isPublic(parentClass.getModifiers())) {
        try {
          return parentClass.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
      parentClass = parentClass.getSuperclass();
    }
    return null;
  }

}
