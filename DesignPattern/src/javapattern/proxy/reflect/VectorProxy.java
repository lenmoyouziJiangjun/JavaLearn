package javapattern.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

/**
 * Version 1.0
 * Created by lll on 17/7/11.
 * Description 反射动态代理
 * copyright generalray4239@gmail.com
 */
public class VectorProxy implements InvocationHandler {
  private Object proxyobj;

  /**
   * @link dependency
   */
  /*#Proxy lnkProxy;*/
  public VectorProxy(Object obj) {
    proxyobj = obj;
  }

  public static Object factory(Object obj) {
    Class cls = obj.getClass();

    return Proxy.newProxyInstance(cls.getClassLoader(),
            cls.getInterfaces(),
            new VectorProxy(obj));
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before calling " + method);
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        System.out.println(args[i] + "");
      }
    }
    Object o = method.invoke(proxyobj, args);
    System.out.println("after calling " + method);
    return o;
  }

  public static void main(String[] args) {
    List v = null;
    v = (List) factory(new Vector(10));
    v.add("New");
    v.add("York");
  }
}
