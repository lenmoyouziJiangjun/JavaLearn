package com.lll.collection;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Version 1.0
 * Created by lll on 17/5/11.
 * Description 优先级队列测试
 *
 * copyright generalray4239@gmail.com
 */
public class PriorityQueueTest {

    public static void main(String[] args){
        testPriorityQueue();
    }

    /**
     * 优先级队列底层采用堆实现。线程不安全
     *  通过comparable 实现优先级。
     *  1、元素实现comparable接口；
     *  2、在初始化PriorityQueue的时候传递一个Comparator比较器对象
     */
    public static void testPriorityQueue(){

        //使用方式1，GregorianCalendar实现comparable接口
        PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER,9));
        pq.add(new GregorianCalendar(1903, Calendar.DECEMBER,9));
        pq.add(new GregorianCalendar(1805, Calendar.DECEMBER,9));
        pq.add(new GregorianCalendar(1804, Calendar.DECEMBER,9));
        pq.add(new GregorianCalendar(1909, Calendar.DECEMBER,9));
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER,2));
        for (GregorianCalendar cal:pq) {//默认从小到大
            System.out.println("----year----"+cal.get(Calendar.YEAR));
        }

        //使用方式2：构造器传递Comparator
        PriorityQueue<GregorianCalendar> pq2 = new PriorityQueue<>(2, new Comparator<GregorianCalendar>() {
            @Override
            public int compare(GregorianCalendar o1, GregorianCalendar o2) {
                return o2.get(Calendar.YEAR)-o1.get(Calendar.YEAR);//从大到小
            }
        });
        pq2.add(new GregorianCalendar(1906, Calendar.DECEMBER,9));
        pq2.add(new GregorianCalendar(1903, Calendar.DECEMBER,9));
        pq2.add(new GregorianCalendar(1805, Calendar.DECEMBER,9));
        pq2.add(new GregorianCalendar(1804, Calendar.DECEMBER,9));
        pq2.add(new GregorianCalendar(1909, Calendar.DECEMBER,9));
        pq2.add(new GregorianCalendar(1906, Calendar.DECEMBER,2));
        System.out.println("----year---2------");
        for (GregorianCalendar cal2:pq2) {//默认从小到大
            System.out.println("----year----"+cal2.get(Calendar.YEAR));
        }
    }

    /**
     * 测试PriorityBlockQueue：阻塞队列，线程安全
     */
    public static void testPriorityBlockingQueue(){
        PriorityBlockingQueue queue = new PriorityBlockingQueue();
    }
}
