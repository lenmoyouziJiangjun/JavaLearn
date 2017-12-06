package com.lll.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/9/15.
 * Description  引用传递和值传递测试
 * copyright generalray4239@gmail.com
 */
public class CallByReference {


    public static void main(String[] args) {
        CallByReference reference = new CallByReference();
        reference.initArray();

        TestPerson test1 = new TestPerson(personList);
        test1.addPerson(new Person("王麻子",123));
        test1.printArray("test1");
        reference.printArray("refrence1");


        TestPerson test2 = new TestPerson(personList);
        test2.addPerson(new Person("尕娃子",123));
        test2.printArray("test2");

        reference.printArray("refrence2");

        TestPerson2 test3 = new TestPerson2(personList);
        test3.addPerson(new Person("尕娃子3",123));
        test3.printArray("test3");

        reference.printArray("refrence3");
    }


    public static List<Person> personList = new ArrayList<>();

    public void initArray() {
        for (int i = 0; i < 3; i++) {
            Person person = new Person("张三" + i, i);
            personList.add(person);
        }
    }

    public void printArray(String name){
        System.out.println("-----"+name+"-------"+personList.size());
        for(Person p:personList){
            System.out.println(p.name+"-----"+p.age);
        }
    }

    public static class Person {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


    public static class TestPerson {
        List<Person> personList;

        public TestPerson(List<Person> personList) {
            this.personList = personList;
        }

        public void addPerson(Person p) {
            personList.add(p);
        }

        public void printArray(String name){
            System.out.println("------"+name+"------"+this.personList.size());
            for(Person p:this.personList){
                System.out.println(p.name+"-----"+p.age);
            }
        }
    }

    public static class TestPerson2 {
        List<Person> personList;

        public TestPerson2(List<Person> personList) {
            this.personList =new ArrayList<>(personList) ;
        }

        public void addPerson(Person p) {
            personList.add(p);
        }

        public void printArray(String name){
            System.out.println("----"+name+"--------"+this.personList.size());
            for(Person p:this.personList){
                System.out.println(p.name+"-----"+p.age);
            }
        }
    }


}
