package com.lll.concurent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Version 1.0
 * Created by lll on 12/4/17.
 * Description
 * 线程基础:start(),run(),优先级，中断，停止,sleep和wait的区别
 * copyright generalray4239@gmail.com
 */
public class ThreadLearn {


    /**
     * 线程中断
     * interrupt():将线程的中断标志位设置为true,线程会不时的检测这个标志位，以判断这个标志位是否应该被中断
     * <p>
     * 1、对于非阻塞中的线程, 只是改变了中断状态, 即Thread.isInterrupted()将返回true;
     * 2、对于可取消的阻塞状态中的线程, 比如等待在这些函数上的线程, Thread.sleep(), Object.wait(), Thread.join(), 这个线程收到中断信号后, 会抛出InterruptedException, 同时会把中断状态置回为true.
     * 但是再次调用Thread.interrupted()会对中断状态进行复位。
     * 3、被中断的线程不一定会被终止，interrupt只是设置中断标记位，
     */
    public static void testInterrupted() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {//线程
//                        Thread.interrupted();//将标志位清空
                        System.out.print("线程中断了" + Thread.interrupted() + "\n");
                        break;
                    } else {
//                          interrupt();// 设置中断标志位为false;
                        try {
                            System.out.print("当前线程的状态为" + Thread.currentThread().isInterrupted() + "\n");
                            sleep(1000);//如果线程
                            System.out.print("线程没有中断成功" + Thread.currentThread().isInterrupted() + "\n");
//
                        } catch (InterruptedException e) {
                            e.printStackTrace();//表示调用sleep的时候，中断标志位为true了，抛出异常的时候会自动将标志位设置为false；
                            System.out.print("线程sleep的时候轮训到状态为中断了" + Thread.currentThread().isInterrupted() + "\n");
//                            Thread.interrupted();//清空状态，标志位回归，false;
                            interrupt();
                        }
                    }
                }
            }
        };
        thread.start();
        thread.interrupt();// 设置中断标志位为false;

    }


    /**
     * 线程的run和start区别：
     */
    public static void testRunAndStart() {


    }

    /**
     * wait()方法是Object类里的方法；当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁（暂时失去机锁，wait(long timeout)超时时间到后还需要返还对象锁）；其他线程可以访问；
     * wait()使用notify或者notifyAlll或者指定睡眠时间来唤醒当前等待池中的线程。
     * wiat()必须放在synchronized block中，否则会在program runtime时扔出”java.lang.IllegalMonitorStateException“异常。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * sleep()使当前线程进入停滞状态（阻塞当前线程），让出CUP的使用、目的是不让当前线程独自霸占该进程所获的CPU资源，以留一定时间给其他线程执行的机会;
     * sleep()是Thread类的Static(静态)的方法；因此他不能改变对象的机锁，所以当在一个Synchronized块中调用Sleep()方法是，线程虽然休眠了，但是对象的机锁并木有被释放，其他线程无法访问这个对象（即使睡着也持有对象锁）。
     * 在sleep()休眠时间期满后，该线程不一定会立即执行，这是因为其它线程可能正在运行而且没有被调度为放弃执行，除非此线程具有更高的优先级。
     * <p>
     * <p>
     * 最大的区别是：
     * sleep()睡眠时，保持对象锁，仍然占有该锁；
     * wait()睡眠时，释放对象锁。
     */
    public static void testSleepAndWait() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1、thread 的join方法：
     * <p>
     * 2、thread 的yield方法：
     */
    public static void testThreadJoin() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    ++i;
                    System.out.println("i====" + i);
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    ++i;
                    System.out.println("i====" + i);
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                Thread.yield();
                int i = 0;
                while (i < 100) {
                    ++i;
                    System.out.println("i====" + i);
                }
            }
        };
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();


    }


    public static void main(String[] args) {
        testInterrupted();
        System.out.println("----内核数量----"+Runtime.getRuntime().availableProcessors());
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = bean.dumpAllThreads(false, false);
        for (ThreadInfo info : infos) {
            System.out.println(info.getThreadId() + "--------" + info.getThreadName() + "----" + info.getThreadState());
            StackTraceElement[] elements = info.getStackTrace();
            for(StackTraceElement element:elements){
                System.out.println("stack==="+element.getClassName()+"----"+element.getMethodName()+"---line Num-"+element.getLineNumber());
            }
        }



    }
}
