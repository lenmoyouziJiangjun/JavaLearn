package com.lll.concurent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Version 1.0
 * Created by lll on 27/12/2017.
 * Description
 * <pre>
 *     1、线程私有变量
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ThreadLocalLearn<T> {

    /**
     * <pre>
     * 1、线程私有变量，线程结束后线程对应的私有变量将自动被垃圾回收器回收。
     *
     * 2、ThreadLocal的原理：
     *    1、每一个Thread对象里面有一个ThreadLocalMap 成员变量，用来维护线程的私有变量。
     *    2、ThreadLocalMap的底层采用 Entry[] 数组来存储数据。Entry的key为
     * </pre>
     */
    private ThreadLocal<T> mThreadLocal = new ThreadLocal<T>();

    /**
     * A random number generator isolated to the current thread
     */
    private ThreadLocalRandom mLocalRandom = ThreadLocalRandom.current();

    /**
     * get逻辑为：获取当前线程的ThreadLocalMap ，再用map.getEntry
     *
     * @return
     */
    public T get() {
        return mThreadLocal.get();
    }

    public void set(T t) {
        mThreadLocal.set(t);
    }


}
