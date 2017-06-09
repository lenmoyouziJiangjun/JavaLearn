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
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    /**
     * 测试atomicBoolean
     * atomicBoolean 的原理：里面有个volatile修饰的成员变量  value。
     */
    public static void testAtomicBoolean(){

    }
}
