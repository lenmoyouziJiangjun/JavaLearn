package com.lll.concurent;/**
 * Created by liaoxueyan on 17/5/12.
 */

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Version 1.0
 * Created by lll on 17/5/12.
 * Description 原子操作测试
 * copyright generalray4239@gmail.com
 */
public class AtomicTest {
    private static AtomicBoolean atomicBoolean = new AtomicBoolean();

    /**
     * 测试atomicBoolean
     * atomicBoolean 的原理：里面有个volatile修饰的成员变量  value。
     */
    public static boolean testAtomicBoolean() {
        return atomicBoolean.compareAndSet(false, true);
    }

    public static boolean getBoolean() {
        return atomicBoolean.get();
    }


    public static void main(String args[]) {
        System.out.println("atomicBoolean==== " + getBoolean() + "--------");
        System.out.println("atomicBoolean==== " + testAtomicBoolean() + "--------");
        System.out.println("atomicBoolean==== " + atomicBoolean.getAndSet(false) + "--------");
        System.out.println("atomicBoolean==== " + atomicBoolean.weakCompareAndSet(true, false) + "--------");
    }
}
