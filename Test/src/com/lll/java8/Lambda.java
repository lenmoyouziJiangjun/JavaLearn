package com.lll.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Version 1.0
 * Created by lll on 10/24/17.
 * Description
 * java 8 Lambda 表达式
 * <p>
 * (参数列表)->{表达式}
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class Lambda {

  public static List<String> nameList = new ArrayList<String>();

  static {
    for (int i = 0; i < 10; i++) {
      nameList.add("张望+" + i);
    }
  }

  public static void main(String[] args) {
    testRunnable();

    testFunctionalInterface();

    testMethodReference();

    System.out.println("uuid===" + UUID.randomUUID());

    Lambda lambda = new Lambda();
    lambda.repeatMessage("测试变量作用域", 2);
  }

  /**
   * Lambda 入门
   */
  public static void testRunnable() {
    //java 8之前
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "Before Java8, too much code for too little to do");
      }
    }).start();

    //java 8 Lambda
    new Thread(() -> {//替换匿名内部类
      System.out.println(Thread.currentThread().getName() + "Java8, use Lambda");
    }).start();
  }


  /**
   * Functional interface
   * 如果一个接口（interface）仅有一个抽象方法（abstract method），就称为
   * Functional Interface，比如Runnable、Comparator等。
   * 在任何一个需要Functional Interface对象的地方，都可以使用lambda表达式：
   */
  public static void testFunctionalInterface() {
    String[] words = new String[]{"name", "aaa", "23dafsd4"};
        /*这里，sort()的第二个参数需要的是一个Comparator对象，而Comparator是
        Functional Interface，因此可以直接传入lambda表达式，在调用该对象的compare()方法
        时，就是执行该lambda表达式中的语句体*/
    Arrays.sort(words, (first, second) -> Integer.compare(first.length(), second.length()));
  }

  /**
   * 如果将lambda表达式的参数作为参数传递给一个方法，他们的执行效果是相同的，则该lambda表达式
   * 可以使用Method Reference表达，以下两种方式是等价的：
   * <p>
   * Method Reference 的三种形式
   * <p>
   * object::instanceMethod 对象方法     ：对应的lambda表达式的参数和method的参数是一致的
   * Class::staticMethod   类静态方法     ：对应的lambda表达式的参数和method的参数是一致的
   * Class::instanceMethod  类实例方法    ：对应的lambda表达式的语句体中，第一个参数作为对象，调用method，将其它参数作为method的参数
   */
  public static void testMethodReference() {
    List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
    features.forEach(n -> System.out.println(n));//表达式参数为n

    //Class::staticMethod   类静态方法
    features.forEach(System.out::println);


    String[] words = new String[]{"name", "aaa", "23dafsd4"};
    //Class::instanceMethod
    Arrays.sort(words, (first, second) -> first.compareToIgnoreCase(second));
  }


  /**
   * Lambda 变量的作用域
   * (参数)->{}
   * <p>
   * 1、lambda表达式会捕获当前作用域下可用的变量，这些变量是不可变的。
   * 因为可变的变量在lambda表达式中不是线程安全的，这和内部类的要求是一致的，内部类中只能引用外部定义的final变量
   * <p>
   * 2、lambda表达式的作用域与嵌套代码块的作用域是一样的，所以在lambd表达式中的参数名或变量名不能与局部变量冲突
   * <p>
   * 3、如果在lambda表达式中引用this变量，则引用的是创建该lambda表达式的方法的this变量
   */

  public void repeatMessage(String text, int count) {
    Runnable r = () -> {
      for (int i = 0; i < count; i++) {
        System.out.println(text + "----" + i);//text变量是不可变的
        System.out.println(this.toString());//this 指lambda这个类对象.输出：你好吗
        Thread.yield();
      }
    };
    new Thread(r).start();
  }

  @Override
  public String toString() {
    return "你好吗?";
  }
}

