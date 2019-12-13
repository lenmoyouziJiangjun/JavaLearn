package com.lll.java8;

import com.lll.basic.CallByReference;
import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Version 1.0
 * Created by lll on 10/26/17.
 * Description
 * java 8 stream 语法
 * <p>
 * stream 语法介绍：
 * 简单来讲，stream就是JAVA8提供给我们的对于元素集合统一、快速、并行操作的一种方式。
 * 它能充分运用多核的优势，以及配合lambda表达式、链式结构对集合等进行许多有用的操作。
 * <p>
 * <p>
 * 概念：
 * stream:可以支持顺序和并行对元素操作的元素集合。
 * <p>
 * <p>
 * <p>
 * 作用：
 * 提供了一种操作大数据接口，让数据操作更容易和更快
 * 使用stream，我们能够对collection的元素进行过滤、映射、排序、去重等许多操作。
 * <p>
 * <p>
 * <p>
 * 3、中间方法和终点方法：
 * 它具有过滤、映射以及减少遍历数等方法，这些方法分两种：中间方法和终端方法，
 * “流”抽象天生就该是持续的，中间方法永远返回的是Stream，因此如果我们要获取最终结果的话，
 * 必须使用终点操作才能收集流产生的最终结果。区分这两个方法是看他的返回值，
 * 如果是Stream则是中间方法，否则是终点方法
 * <p>
 * //通过 Collection.stream() 或者 Collection.parallelStream() 来创建一个Stream
 * <p>
 * <p>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class StreamLearn {


  public static class Person {
    public String name;
    public int age;



    public Person() {
    }

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return "[ name=" + this.name + ", age=" + this.age + "]";
    }
  }

  public static class Student {
    public String name;
    public int age;

    public Student(Person p) {
      this.name = p.name;
      this.age = p.age;
    }
  }

  public static List<Person> persons = new ArrayList<>();


  static {
    for (int i = 0; i < 10; i++) {
      Person person = new Person("张三" + i, (new Random().nextInt(100)) % 100);
      persons.add(person);
    }
  }

  /**
   * 测试Stream的filter方法
   */
  public static void testStreamFilter() {
    /**
     * filter 表示过滤，里面传递一个过滤条件。filter 只是一个中间方法，
     *
     * collect：收集结果流。filter 只是一个中间方法，返回的还是stream，通过collect方法转为list集合返回
     */
    List<Person> personList = persons.stream().filter(p -> p.age > 10).collect(Collectors.toList());

    printList(personList);
  }

  /**
   * map 对元素进行转换操作
   */
  public static List testStreamMap() {
    return Arrays.asList(persons.stream().map(p -> new Student(p)).toArray());
  }

  /**
   * 测试sort排序
   */
  public static void testStreamSorted() {
    persons.stream().sorted((p1, p2) -> {
      return p1.age - p2.age;
    }).forEach(System.out::println);
  }


  /**
   * 测试并行计算
   */
  public static void testParaller() {
    long t0 = System.nanoTime();

    //初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法

    int a[] = IntStream.range(0, 1_000_000).filter(p -> p % 2 == 0).toArray();

    long t1 = System.nanoTime();

    //和上面功能一样，这里是用并行流来计算

    int b[] = IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2 == 0).toArray();

    long t2 = System.nanoTime();

    //我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快

    System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);

  }

  /**
   * test forEach
   *
   * @param personList
   */
  public static void printList(List<Person> personList) {
    /**
     *  forEach
     * */
//        personList.forEach(p -> {
//            System.out.println(p.toString());
//        });
    //和上面一样，采用方法引用
    personList.forEach(System.out::println);
  }


  public static void main(String args[]) {
//        testStreamFilter();
//        testStreamMap();
//
//        testParaller();
    testStreamSorted();
  }


}
