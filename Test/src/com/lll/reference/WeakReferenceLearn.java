package com.lll.reference;

import java.lang.ref.WeakReference;

/**
 * Version 1.0
 * Created by lll on 12/6/17.
 * Description
 * 弱引用学习
 * copyright generalray4239@gmail.com
 */
public class WeakReferenceLearn {

  public String name;
  public String address;

  public Entry entry = new Entry(this, this);



  public WeakReferenceLearn(String name, String address) {
    this.name = name;
    this.address = address;
  }


  static class Entry<T> extends WeakReference<T> {
    WeakReferenceLearn elearn;

    public Entry(T referent, WeakReferenceLearn learn) {
      super(referent);
      elearn = learn;
    }
  }


  public void print() {
    if (entry.get() != null) {
      System.out.println(entry.elearn.address + "------------" + entry.elearn.name);
    }
  }

  public void printName() {
    System.out.println(entry.elearn.address + "------------" + entry.elearn.name);
  }

  public void gc() {
    System.gc();//不一定马上执行
    System.out.println("调用GC-------------========");
    Runtime.getRuntime().runFinalization();
    System.gc();
  }


  public static void main(String[] args) {
    WeakReferenceLearn learn1 = new WeakReferenceLearn("张三1", "李四1");
    WeakReferenceLearn learn2 = new WeakReferenceLearn("张三2", "李四2");
    WeakReferenceLearn learn3 = new WeakReferenceLearn("张三3", "李四3");
    WeakReferenceLearn learn4 = new WeakReferenceLearn("张三4", "李四4");
    WeakReference<WeakReferenceLearn> weakReference = new WeakReference<WeakReferenceLearn>(learn4);
    learn1.print();
    learn2.print();
    learn2.gc();
    learn3.print();
    learn4.print();
    weakReference.get().printName();
  }
}
