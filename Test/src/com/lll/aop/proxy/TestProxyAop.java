package com.lll.aop.proxy;/**
 * Created by liaoxueyan on 17/6/27.
 */

import com.lll.aop.demo.account.Account;
import com.lll.aop.demo.account.AccountImpl;

import java.lang.reflect.Proxy;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 测试主入口
 * copyright generalray4239@gmail.com
 */
public class TestProxyAop {


    public static void main(String[] args) {
        Account account = (Account) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Account.class}, new SecurityProxyInvocationHandler(new AccountImpl()));

        account.operation();
    }

    /**
     * javapattern.proxy 实现AOP
     * 优点：代码实现简单
     * 缺点：1、Proxy 是面向接口的，所有使用 Proxy 的对象都必须定义一个接口，而且用这些对象的代码也必须是对接口编程的：
     * Proxy 生成的对象是接口一致的而不是对象一致的：例子中 Proxy.newProxyInstance生成的是实现 Account接口的对象而不是 AccountImpl的子类。这对于软件架构设计，尤其对于既有软件系统是有一定掣肘的。
     * 2、Proxy 毕竟是通过反射实现的，必须在效率上付出代价：有实验数据表明，调用反射比一般的函数开销至少要大 10 倍。
     * 而且，从程序实现上可以看出，对 javapattern.proxy class 的所有方法调用都要通过使用反射的 invoke 方法。因此，对于性能关键的应用，使用 javapattern.proxy class 是需要精心考虑的，以避免反射成为整个应用的瓶颈。
     */
    public static void testProxy() {

    }


}
