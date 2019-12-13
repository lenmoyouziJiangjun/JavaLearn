package jvmclassload.classinit;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Parent {
  public static int A = 1;

  static {
    A = 2;
    i = 10;//静态字段的初始顺序，为可以引用之前定义的静态字段，可以为之后定义的静态字段，但是不能引用。
    //System.out.print(i);编译出错
  }

  public static int i = 9;
}
