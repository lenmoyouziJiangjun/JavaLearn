package org.apache.commons.proxy2.impl;

import org.apache.commons.proxy2.exception.ProxyFactoryException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * Parent {@link AbstractProxyFactory} for implementations that permit the generation of proxies with a specific
 * inheritance hierarchy.
 * copyright generalray4239@gmail.com
 */
public abstract class AbstractSubclassingProxyFactory extends AbstractProxyFactory {

  /**
   * Returns true if a suitable superclass can be found, given the desired <code>proxyClasses</code>.
   *
   * @param proxyClasses the javapattern.proxy classes
   * @return true if a suitable superclass can be found, given the desired <code>proxyClasses</code>
   */
  @Override
  public boolean canProxy(Class<?>... proxyClasses) {
    try {
      getSupperClass(proxyClasses);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Returns either {@link Object} if all of the <code>proxyClasses</code> are interfaces or the javapattern.single non-interface
   * class from <code>proxyClasses</code>.
   *
   * @param proxyClasses the javapattern.proxy classes
   * @return either {@link Object} if all of the <code>proxyClasses</code> are interfaces or the javapattern.single non-interface
   * class from <code>proxyClasses</code>
   * @throws ProxyFactoryException if multiple non-interface classes are contained in <code>proxyClasses</code> or any of the
   *                               non-interface classes are final
   */
  public static Class<?> getSupperClass(Class<?>[] proxyClasses) {
    final Class<?>[] superClasses = toNonInterfaces(proxyClasses);
    switch (superClasses.length) {
      case 0:
        return Object.class;
      case 1:
        final Class<?> superClass = superClasses[0];
        if (Modifier.isFinal(superClass.getModifiers())) {
          //
          throw new ProxyFactoryException("Proxy class cannot extend " + superClass.getName()
                  + " as it is final.");
        }
        if (!hasSuitableDefaultConstructor(superClass)) {
          throw new ProxyFactoryException("Proxy class cannot extend " + superClass.getName()
                  + ", because it has no visible \"default\" constructor.");
        }
        return superClass;
      default:
        final StringBuilder errorMessage = new StringBuilder("Proxy class cannot extend ");
        for (int i = 0; i < superClasses.length; i++) {
          Class<?> c = superClasses[i];
          errorMessage.append(c.getName());
          if (i != superClasses.length - 1) {
            errorMessage.append(", ");
          }
        }
        errorMessage.append("; multiple inheritance not allowed.");
        throw new ProxyFactoryException(errorMessage.toString());
    }
  }


  /**
   * get all Object type from proxyClasses
   *
   * @param proxyClasses
   * @return
   */
  private static Class<?>[] toNonInterfaces(Class<?>[] proxyClasses) {
    final Set<Class<?>> supperClasses = new LinkedHashSet<>();
    for (Class<?> proxyClass : proxyClasses) {
      if (!proxyClass.isInterface()) {//not interface
        supperClasses.add(proxyClass);
      }
    }
    return supperClasses.toArray(new Class[supperClasses.size()]);
  }

  /**
   * @param superClass
   * @return
   */
  private static boolean hasSuitableDefaultConstructor(Class<?> superClass) {
    final Constructor<?>[] declaredConstructors = superClass.getDeclaredConstructors();
    for (int i = 0; i < declaredConstructors.length; i++) {
      Constructor<?> constructor = declaredConstructors[i];
      if (constructor.getParameterTypes().length == 0
              && (Modifier.isPublic(constructor.getModifiers()))
              && (Modifier.isProtected(constructor.getModifiers()))) {
        return true;
      }
    }
    return false;
  }
}
