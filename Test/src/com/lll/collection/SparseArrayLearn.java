package com.lll.collection;

/**
 * Version 1.0
 * Created by lll on 07/03/2018.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SparseArrayLearn {

  public static class Person {
    String name;

    public Person(String name) {
      this.name = name;
    }
  }

  public static void main(String[] args) {
    Person[] persons = new Person[10];
    for (int i = 0; i < 10; i++) {
      persons[i] = new Person("name" + i);
    }

    Object[] values = persons;
    for (int i = 0; i < 10; i++) {
      values[i] = null;
    }
    //报空，
    System.out.println("======" + persons[0].name);
  }
}
