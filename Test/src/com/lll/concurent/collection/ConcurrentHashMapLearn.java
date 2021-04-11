package com.lll.concurent.collection;

import com.lll.collection.learn.ArrayMap;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Version 1.0
 * Created by lll on 01/03/2018.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ConcurrentHashMapLearn {

    /**
     * 并发容器：
     *
     * <pre>
     *     ConcurrentHashMap;
     * Hashtable;
     * ConcurrentLinkedQueue;
     * ConcurrentLinkedDeque
     *
     * </pre>
     */

    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


    /**
     *
     */
    Hashtable hashtable = new Hashtable();

    /**
     *
     */
    ConcurrentSkipListMap skipListMap = new ConcurrentSkipListMap();

    /**
     *
     */
    CopyOnWriteArrayList list = new CopyOnWriteArrayList();

}
