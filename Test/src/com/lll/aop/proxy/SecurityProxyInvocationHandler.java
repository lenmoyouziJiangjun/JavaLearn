package com.lll.aop.proxy;/**
 * Created by liaoxueyan on 17/6/27.
 */

import com.lll.aop.demo.account.Account;
import com.lll.aop.demo.account.SecurityChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 代理实现插桩
 * copyright generalray4239@gmail.com
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {

  private Object mInvokeObj; //实际执行对象

  public SecurityProxyInvocationHandler(Object invokeObj) {
    this.mInvokeObj = invokeObj;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
    //判断方法
    if (proxy instanceof Account && method.getName().equals("operation")) {
      SecurityChecker.checkSecurity();
    }
    return method.invoke(mInvokeObj, arguments);
  }
}
