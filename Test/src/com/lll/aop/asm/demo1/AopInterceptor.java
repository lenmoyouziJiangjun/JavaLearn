package com.lll.aop.asm.demo1;

/**
 * Version 1.0
 * Created by lll on 17/6/26.
 * Description aop 业务类
 * copyright generalray4239@gmail.com
 */
public class AopInterceptor {
    /**
     * 具体业务逻辑之前调用
     */
    public static void beforeInvoke() {
        System.out.println("before");
    }

    /**
     * 业务逻辑之后调用
     */
    public static void afterInvoke() {
        System.out.println("after");
    }
}
