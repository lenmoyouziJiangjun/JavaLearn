package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 14/03/2018.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StaticLearn {

    static class Person {

        static String getName() {//非静态内部类不能有静态方法
            return "P";
        }

        static int setName(String name) {
            System.out.println(name);
            return 1;
        }

        void setName(String name,int age){
            System.out.println(name+"------"+age);
        }
    }


    static class Student extends Person {
        static String getName() {//
            return "s";
        }


        static int setName(String name) {
            System.out.println("儿子的方法==="+name);
            return 1;
        }

        void setName(String name,int age){
            System.out.println("儿子的方法===="+name+"------"+age);
        }
    }

    public static void main(String[] args) {
        System.out.println(Person.getName());//P
        System.out.println(Student.getName());//s

        Person person = new Person();
        Student student = new Student();

        Person person2 = new Student();

        System.out.println(person.setName("baba"));//P
        System.out.println(student.setName("儿子"));//s
        System.out.println(person2.setName("爸爸指向儿子"));//s

        person.setName("baba",43);
        student.setName("儿子",12);
        person2.setName("儿子",12);
    }

}
