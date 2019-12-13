package com.lll.concurent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Version 1.0
 * Created by lll on 14/12/2017.
 * Description
 * <pre>
 *     一、阻塞队列：支持阻塞添加删除的队列。队列满时会阻塞添加的线程，直到队列不满
 *     二、阻塞队列的常用操作：
 *        添加：
 *           1、offer:添加成功返回true
 *           2、add:添加失败报错
 *        删除：
 *           1、poll
 *
 *      三、有界阻塞和无界阻塞的区别：
 *          1、有界阻塞队列：
 *
 *
 * </pre>
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class BlockingQueueLearn {


  /**
   * 数组组成的阻塞队列
   * <p>
   * 第一个参数表示 初始大小，第二个表示顺序 true 表示有序FIFO，false表示无序
   * 原理就是里面有个Object[] 数组。 put的时候，判断数组是否已满，满了就wait，等待take。
   */
  public ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(4, false);

  /**
   * 链表组成的阻塞队列
   */
  public LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(5);

  /**
   * 优先级阻塞队列
   */
  public PriorityBlockingQueue<MyPriority> priorityBlockingQueue = new PriorityBlockingQueue<>();

  /**
   * 延时队列
   */
  public DelayQueue<MyDelay> delayQueue = new DelayQueue<>();

  /**
   *
   */
  public SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(true);

  private volatile boolean isStopProducer = false;
  private volatile boolean isStopConsumer = false;

  private volatile int num;
  private List<String> integerList = new CopyOnWriteArrayList<>();

  /**
   * 生成者
   */
  public class Producer implements Runnable {
    public final BlockingQueue queue;

    public Producer(BlockingQueue queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      while (!isStopProducer) {
        try {
          queue.put(produce());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    public int produce() {
      synchronized (queue) {
        num += 1;
        integerList.add(num + "");
        if (num == 20) {
          isStopProducer = true;
        }
        System.out.println(Thread.currentThread().getName() + "---------生产了------" + num);
        return num;
      }
    }
  }

  /**
   * 消费者
   */
  public class Consumer implements Runnable {

    public final BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      while (!isStopConsumer) {
        try {
          consume((Integer) queue.take());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    public void consume(int obj) {
      synchronized (queue) {
        integerList.remove(obj + "");
        if (integerList.size() == 0) {
          isStopConsumer = true;
        }
        System.out.println(Thread.currentThread().getName() + "---------消费了------" + obj);
      }
    }
  }


  public class MyPriority implements Comparable {


    @Override
    public int compareTo(Object o) {
      return 0;
    }
  }

  /**
   * 验证DelayQueue
   */
  public class MyDelay implements Delayed {

    @Override
    public long getDelay(TimeUnit unit) {
      return 0;
    }

    @Override
    public int compareTo(Delayed o) {
      return 0;
    }
  }


  public static void main(String[] args) {
    BlockingQueueLearn lean = new BlockingQueueLearn();
    Producer producer;
    for (int i = 0; i < 10; i++) {
      producer = lean.new Producer(lean.arrayBlockingQueue);
      new Thread(producer, "生产者" + i + " 号").start();
    }

    Consumer consumer;
    for (int i = 0; i < 10; i++) {
      consumer = lean.new Consumer(lean.arrayBlockingQueue);
      new Thread(consumer, "消费者" + i + " 号").start();
    }

  }

}
