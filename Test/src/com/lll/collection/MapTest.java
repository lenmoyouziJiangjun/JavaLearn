package com.lll.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Version 1.0
 * Created by lll on 17/8/29.
 * Description
 * Map学习：采用jdk1.8版本源码
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class MapTest {

    /**
     * map底层采用一个 transient Node<K,V>[] table; Node数组来存储数据。也就是说Map底层将数据封装成一个Node对象。
     * 对象包含，final int hash;final K key;V value;Node<K,V> next;。
     * Node对象看是一个链表结构，但是HashMap并没有使用链表的功能。链表功能在LinkedHashMap使用
     */
    public static Map<String, String> hashMap = new HashMap<String, String>(8, .75f);

    /**
     * LinkedHashMap继承了HashMap，只是节点Node为链表节点。
     */
    public static Map<String, String> linkedHashMap = new LinkedHashMap<String, String>(8, .75f, true);

    /**
     *
     */
    public static Map<String, String> treeMap = new TreeMap<String, String>();

    /**
     * 使用"==" 代替equals对键进行散列映射。转为解决特殊问题设计的
     */
    public static Map<String, String> identityHashMap = new IdentityHashMap<>();

    /**
     *
     */
    public static Map<String, MapTest> weakHashMap = new WeakHashMap<>();
    /**
     *
     */
    public static Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    /**
     *
     */
    public static Map<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();


    public static void main(String[] args) {
        hashMapPut();
        System.out.println("==============我是分割线=====================");
        linkedHashMapPut();
        System.out.println("==============我是分割线=====================");
        treeMapPut();
        System.out.println("==============我是分割线=====================");
        identityHashMapPut();
    }


    /**
     * hashMap 添加元素的原理：
     * 1、根据 key.hashCode & (数组长度-1) 的值获取到数组Node下标i
     * 2、如果 Node[i]==null ,创建元素Node并复制给Node[i];
     * 3、如果Node[i] !=null, 取出Node[i]的key 比较，如果相等，就替换掉旧的值
     */
    public static void hashMapPut() {

        hashMap.put("key3", "value3");
        hashMap.put("key4", "value4");
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key4", "value5");//key重复了，新的替换掉老的
        hashMap.put("akey4", "avalue5");
        hashMap.put("dkey4", "dvalue5");
        printMap(hashMap);//无序；
    }


    /**
     * hashMap 取值：
     * (node = tab[(n - 1) & key.hashCode()]) != null ;//取出节点，然后根据节点的hash和key比较，相等就取出value;
     */
    public static void hashMapGet() {
        String value = hashMap.get("key4");
    }


    /**
     * LinkedHashMap的添加元素的逻辑
     * 1、LinkedHashMapLearn 定义了一个Entry节点，继承HashMap的Node节点。并增加了before 和 after两个元素，双端链表
     * <p>
     * 2、LinkedHashMap定义了链表的头尾节点
     * transient LinkedHashMapLearn.Entry<K,V> head;
     * transient LinkedHashMapLearn.Entry<K,V> tail;
     * 3、重写了HashMap的newNode方法，  里面增加了链表功能linkNodeLast。
     * <p>
     * private void linkNodeLast(LinkedHashMapLearn.Entry<K,V> p) {
     * LinkedHashMapLearn.Entry<K,V> last = tail;//
     * tail = p;
     * if (last == null)
     * head = p;
     * else {
     * p.before = last; 前驱等于链表最后一个
     * last.after = p;  最后的的后继等于p
     * </p>
     */
    public static void linkedHashMapPut() {
        linkedHashMap.put("key1", "value1");
        linkedHashMap.put("key2", "value2");
        linkedHashMap.put("key3", "value3");
        linkedHashMap.put("key4", "value4");
        linkedHashMap.put("key4", "value5");//key重复了，新的替换掉老的
        linkedHashMap.put("akey4", "avalue5");
        linkedHashMap.put("dkey4", "dvalue5");
        printMap(linkedHashMap);//无序；
    }


    /**
     * trueMap底层采用红黑树的方式添加数据，因此元素是根据Key的字典属性排列
     */
    public static void treeMapPut() {
        treeMap.put("key1", "value1");
        treeMap.put("key2", "value2");
        treeMap.put("key3", "value3");
        treeMap.put("key4", "value4");
        treeMap.put("key4", "value5");//key重复了，新的替换掉老的
        treeMap.put("akey4", "avalue5");
        treeMap.put("dkey4", "dvalue5");
        printMap(treeMap);//无序；
    }

    /**
     *  一般的map  key采用 hashCode方法生成的hash去equals 比较
     *  而identityHashMapPut 采用 if (item == k) {}的方式去比较。所以对于两个
     *
     */
    public static void identityHashMapPut() {
        String key4 = "key4";
        String key5 = new String("key4");
        System.out.println("------identityHashMap----"+(key4==key5)+"-------"+key4.equals(key5));//----false-------true
        System.out.println("------identityHashMap----"+(key4=="key4")+"-------"+key4.equals("key4"));//----true-------true
        identityHashMap.put("key1", "value1");
        identityHashMap.put("key2", "value2");
        identityHashMap.put("key3", "value3");
        identityHashMap.put(key5, "value4");
        identityHashMap.put(key4, "value5");//key重复了，新的替换掉老的
        identityHashMap.put("akey4", "avalue5");
        identityHashMap.put("dkey4", "dvalue5");
        printMap(identityHashMap);//无序；
    }

    public static void weakHashMapPut(){
        weakHashMap.put("tes1",new MapTest());
        weakHashMap.put("tes2",new MapTest());
        weakHashMap.put("tes3",new MapTest());
        weakHashMap.put("tes4",new MapTest());
        weakHashMap.put("tes5",new MapTest());
        weakHashMap.put("tes6",new MapTest());

    }



    private static void printMap(Map map) {
        Set<Map.Entry> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            System.out.println("key=====" + entry.getKey() + "==========" + entry.getValue());
        }
    }
}
