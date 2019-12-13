package com.lll.aop.asm.demo2;

/**
 * Version 1.0
 * Created by lll on 2019-12-06.
 * Description
 * <pre>
 *   字节码测试
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class TestBytecode {

  class Person {
    public String name;
    public String address;

    @Override
    public String toString() { //查看编译器String++ 的优化
      return "Person{" +
              "name='" + name + '\'' +
              ", address='" + address + '\'' +
              '}';
    }
  }

  class Student extends  Person { //查看内部类的字节码
    public String sID;

    //测试synchronized 字节码
    public synchronized void updateName(String name) {
      this.name = name;
    }
  }

  //查看静态内部类的字节码
  static class TextByteCode {

    public void test() {
      try { //查看try catch 的字节码

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
