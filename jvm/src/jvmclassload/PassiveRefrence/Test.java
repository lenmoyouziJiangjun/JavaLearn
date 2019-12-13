package jvmclassload.PassiveRefrence;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Test {

  public static void main(String[] args) {
    //输出结果：super class 127  为什么？
    //对于静态字段，只有直接定义这个字段的类才会被初始化，
    // 因此通过其子类来引用父类中定义的静态字段,只会触发父类的初始化而不会触发子类的初始化。
    System.out.println(SubClass.value);
  }
}
