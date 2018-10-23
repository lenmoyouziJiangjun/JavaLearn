package com.lll.basic;

import java.util.*;

/**
 * Version 1.0
 * Created by lll on 12/5/17.
 * Description
 * 对象的equals 和hashCode方法介绍
 *
 * 1、object 的equals方法和hashCode方法根据就没有什么关系。
 *     硬要说有什么关系的话：就是java默认一个规范：
 *    1. 如果两个对象相等的话，它们的hash code必须相等；
 *    2. 但如果两个对象的hash code相等的话，这两个对象不一定相等。
 *
 * 2、object 的equals 方法：比较两个对象是否具有相同的引用。
 * 3、object 的hashCode方法：对象存储地址。
 *
 * 4、java建议我们重写equals方法就重写hashCode方法，主要是为了方便将对象存储到散列表中(Map,HashSet)
 *
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class ObjectLearn {

    public String name;
    public String address;

    public ObjectLearn() {
    }

    public ObjectLearn(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        //  采用this==obj 实现equal方法。
        if (this == obj) {
            return true;
        }
        ObjectLearn other = (ObjectLearn) obj;
        if (name.equals(other.name) || address.equals(other.address)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[ " + name + "    ,   " + address + " ]";
    }

    @Override
    public int hashCode() {
//        return super.hashCode();
        return name.hashCode();
//        return new Random().nextInt(10);
    }


    /**
     * 1、final 修饰类：标明这个类不能被继承
     * 2、final 修饰方法：表示这个方法不能被子类复写
     * 3、final修饰成员变量：表示这个成员变量必须在构造方法前初始化，同时不能再赋值
     * 4、final 修饰局部变量：表示这个变量不能被修改。
     */
    public final void fileLearn() {


    }


    /**
     * 1. 如果两个对象相等的话，它们的hash code必须相等；
     * 2. 但如果两个对象的hash code相等的话，这两个对象不一定相等。
     *
     * @param args
     */
    public static void main(String[] args) {
        ObjectLearn learn1 = new ObjectLearn("zhang", "san");
        ObjectLearn learn2 = new ObjectLearn("zhang", "san");
        ObjectLearn learn3 = new ObjectLearn("zhang3", "san3");
        ObjectLearn learn4 = new ObjectLearn("zhang", "san4");

        System.out.print(learn1.hashCode() + "-------" + learn2.hashCode() + "--------" + learn1.equals(learn2) + "\n");


        Map map = new HashMap();
        map.put(learn1.name, learn1);
        map.put(learn2.name, learn2);
        map.put(learn3.name, learn3);
        map.put(learn4.name, learn4);
        printCollection(map.values());

        System.out.println("==================================");
        printCollection(map.keySet());
        System.out.println("==================================");


        Set set = new HashSet();
        set.add(learn1);
        set.add(learn2);
        set.add(learn3);
        set.add(learn4);
        printCollection(set);//返回了4次，证明四个对象不相等
        //HashSet的去重逻辑：
        // hashSet的底层是一个hashMap,key的生成方式为：
//        static final int hash(Object key) {
//            int h;
//            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//        }
        System.out.println("==================================");
    }

    public static void printCollection(Collection collection) {

        collection.forEach(obj -> System.out.println(collection.size() + "-----" + obj.toString()));
    }

//    public void method1(List<String> args){
//
//    }
//
//    public void method1(List<Integer> args){//方法重载，编译出错，泛型擦除，
//
//    }
//
//    public List<Integer>  method2(){
//        return null;
//    }
//
//    public List<String>  method2(){//方法已经被定义：泛型擦除
//        return null;
//    }
//
//    public String method2(){//方法重载，要求方法名称相同，返回值类型不同，参数
//        return null;
//    }


}
