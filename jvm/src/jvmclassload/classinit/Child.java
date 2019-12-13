package jvmclassload.classinit;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Child extends Parent {

  //父类中的静态代码块要优先于子类的变量赋值，
  public static int B = A;

  static {
    A = 30;
  }

  public static void main(String args[]) {
    System.out.println(Child.B);//2
    System.out.println(Child.A);//30
    System.out.println(Child.i);//9
  }
}
