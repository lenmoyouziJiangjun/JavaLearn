package com.lll.collection;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Version 1.0
 * Created by lll on 11/12/2017.
 * Description
 * <p>
 * 集合学习
 * <p>
 * copyright generalray4239@gmail.com
 */
public class ArrayTest {

    public Vector vector = new Vector();

    /**
     * 数组，底层采用一个Object[] elementData存储数据，
     * 1、添加的时候，判断数组是否满了，满了就elementData = Arrays.copyOf(elementData, newCapacity); copy一个
     * 2、数组的删除：根据索引删除数据。用索引的下一个数据覆盖这个。数组长度--；，同时将源数组的最后一个元素置为null,让垃圾回收期回收
     * <pre>
     *       public E remove(int index) {
     * rangeCheck(index);
     * modCount++;
     * E oldValue = elementData(index);
     *
     * int numMoved = size - index - 1;//需要移动的长度
     * if (numMoved > 0){
     *      //表示源数据的index+1位置，numMoved的数据copy到 目的数组的index位置
     *      System.arraycopy(elementData, index+1, elementData, index,numMoved);}
     * elementData[--size] = null; // clear to let GC do its work
     *
     * return oldValue;
     * }
     *
     * </pre>
     */
    public ArrayList arrayList = new ArrayList(10);

    public LinkedList LikenList = new LinkedList();

    public CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();


/*******************************数组的错误删除****************************************/
    /**
     * 容易报错：  java.lang.IndexOutOfBoundsException:
     *
     * @param list
     * @param target
     */
    public static void remove11(List<String> list, String target) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String item = list.get(i);
            if (target.contains(item)) {
                list.remove(item);
            }
        }
    }

    /**
     * bug:有两种bug出现
     * 1、容易导致元素删不干净
     * 2、ConcurrentModificationException
     *
     * @param list
     * @param target
     */
    public static void remove12(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {//每次重新计算一次size，索引却变化了，导致数据删不干净
            String item = list.get(i);
            if (target.equals(item)) {
                list.remove(item);
            }
        }
    }

    /**
     * bug: java.util.ConcurrentModificationException
     *
     * @param list
     * @param target
     */
    public static void remove13(List<String> list, String target) {
        for (String item : list) {
            if (target.equals(item)) {
                list.remove(item);
            }
        }
    }

    /**
     * bug:java.util.ConcurrentModificationException
     *
     * @param list
     * @param target
     */
    public static void remove14(List<String> list, String target) {
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                list.remove(item);
            }
        }
    }

    /**
     * bug:java.util.ConcurrentModificationException
     *
     * @param list
     * @param target
     */
    public static void remove15(List<String> list, String target) {
        for (int i = list.size() - 1; i >= 0; i--) {
            String item = list.get(i);
            if (target.equals(item)) {
                list.remove(item);
            }
        }
    }

    /*******************************数组的错误删除end****************************************/


    /*******************************数组的正确删除end****************************************/
    public static void remove16(List<String> list, String target) {
        //将数组转为CopyOnWriteArrayList 解决并发问题。
        final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);
        for (String item : cowList) {
            if (item.equals(target)) {
                cowList.remove(item);
            }
        }
    }

    public static void remove17(List<String> list, String target) {
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();//采用 Iterator 的remove删除
            }
        }
    }

    public static void remove18(List<String> list, String target) {
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();//采用 Iterator 的remove删除
            }
        }
    }

    public static void remove19(List<String> list, String target) {
        int i = 0;
        while (i < list.size()) {//解决了索引问题，清除里面为null的元素，删不干净，留了一个
            if (list.get(i).equals(target)) {
                list.remove(i);
            } else {
                i++;
            }
        }

        for (i = 0; i < list.size(); i++) { //删除一个
            if (list.get(i).equals(target)) {
                list.remove(i);
            }
        }
    }


    public static void main(String[] argc) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("beijing");
        list.add("shanghai2");
        list.add("shanghai1");
        list.add("guangzhou");
        list.add("shenzhen");
        list.add("hangzhou");
        list.add("shanghai3");
        list.add("shanghai4");

        ArrayList<String> list2 = new ArrayList<String>(list);
        ArrayList<String> list3 = new ArrayList<String>(list);
        ArrayList<String> list4 = new ArrayList<String>(list);
        ArrayList<String> list5 = new ArrayList<String>(list);

//        /**
//         * bug:java.lang.ArrayStoreException。目标和源必须都是数组
//         */
//        System.arraycopy(list,0,list2,0,list.size());
//        System.arraycopy(list,0,list3,0,list.size());
//        System.arraycopy(list,0,list4,0,list.size());
//        System.arraycopy(list,0,list5,0,list.size());
        try {
            remove11(list, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            remove11(list, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            remove12(list2, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            remove13(list3, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            remove14(list4, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            remove15(list5, "shang");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Object[] mValues;
    private int[] keys;


}
