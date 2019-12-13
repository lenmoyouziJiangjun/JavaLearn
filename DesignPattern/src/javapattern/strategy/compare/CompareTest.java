package javapattern.strategy.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/7/28.
 * Description
 * copyright generalray4239@gmail.com
 */
public class CompareTest {


  public static void main(String[] args) {
    List<Person> persons = new ArrayList<>();
    //比较方式一
    Collections.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        return o1.id.compareToIgnoreCase(o2.id);
      }
    });

    //让domain对象实现Comparable接口
    List<Teacher> teachers = new ArrayList<>();
    Collections.sort(teachers);
  }


  public class Person {
    public int age;
    public String id;
  }


  public class Teacher implements Comparable {
    public String tid;

    @Override
    public int compareTo(Object o) {
      Teacher t2 = (Teacher) o;
      return this.tid.compareToIgnoreCase(t2.tid);
    }
  }
}
