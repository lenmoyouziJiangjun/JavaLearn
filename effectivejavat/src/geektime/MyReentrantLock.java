package geektime;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * Version 1.0
 * Created by lll on 2019/2/28.
 * Description
 * copyright generalray4239@gmail.com
 */
public class MyReentrantLock {


    public void testReentrantLock() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();//lock 放在try 外面
        try {

        } finally { //finally 里面回收锁
            lock.unlock();
        }
    }

    public void testArrayBlockingQueue() {
        ArrayBlockingQueue<MyReentrantLock> queue = new ArrayBlockingQueue<MyReentrantLock>(100);
    }


    public synchronized void testReentrantReadWriteLock() {

    }

    /**
     * 读写锁：
     * 1、传统的synchronized 和ReentrantLock简单实用，但是太霸道了，要么独占要么不占。
     * 实际开发中有些场景不需要大量的写操作，而是大量的读操作。就可以实用读写锁
     */
    public class RWSimple {
        final Map<String, String> maps = new TreeMap<>();
        final ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();
        private final Lock mReadLock = mLock.readLock();
        private final Lock mWriteLock = mLock.writeLock();

        public String get(String key) {
            mReadLock.lock();
            try {
                return maps.get(key);
            } finally {
                mReadLock.unlock();
            }
        }

        public void put(String key, String value) {
            mWriteLock.lock();
            try {
                maps.put(key, value);
            } finally {
                mWriteLock.unlock();
            }
        }
    }

    /**
     * ReentrantReadWriteLock 在实际开发中，因为开销比较大，使用起来不是特别好。
     * jdk提供了一个优化的类 StampedLock
     */
    public class StampedSample {
        private final Map<String, String> maps = new TreeMap<>();
        private final StampedLock mStampedLock = new StampedLock();
//        private final Lock mReadLock = mStampedLock.asReadLock();
//        private final Lock mWriteLock = mStampedLock.asReadLock();
//
//        public String get(String key) {
//            mReadLock.lock();
//            try {
//                return maps.get(key);
//            } finally {
//                mReadLock.unlock();
//            }
//        }

//        public void put(String key, String value) {
//            mWriteLock.lock();
//            try {
//                maps.put(key, value);
//            } finally {
//                mWriteLock.unlock();
//            }
//        }

        public String get(String key) {
            long stamp = mStampedLock.tryOptimisticRead();
            String value = maps.get(key);
            if (!mStampedLock.validate(stamp)) {
                stamp = mStampedLock.readLock();
                try {
                    value = maps.get(key);
                } finally {
                    mStampedLock.unlockRead(stamp);
                }
            }
            return value;
        }

        public void put(String key, String value) {
            long stamp = mStampedLock.writeLock();
            try {
                maps.put(key, value);
            } finally {
                mStampedLock.unlockWrite(stamp);
            }
        }
    }

    /**
     * 同一个thread 调用两次start
     *
     * 多次调用start 会报错：
     * 报错：java.lang.IllegalThreadStateException
     *
     */
    public static void testThreadStart(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("---thread id----===="+Thread.currentThread());
            }
        };
        thread.start();
        thread.start(); //第二次调用的时候，线程可能处理终止或者其它非NEW状态，所以报错
    }


    public static void main(String[] args){
        MyReentrantLock.testThreadStart();
    }
}
