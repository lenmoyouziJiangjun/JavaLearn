package com.lll.aop.asm.demo1;

/**
 * Version 1.0
 * Created by lll on 17/6/26.
 * Description 需要AOP插入的类
 * copyright generalray4239@gmail.com
 */
public class TestAsm {

    /**
     * 定义一个测试方法，要求在这个方法调用之前和之后，插入我们的代码逻辑
     */
    public void testAsm(){
        System.out.println("在我前面和后面插入检查逻辑");
    }

}
