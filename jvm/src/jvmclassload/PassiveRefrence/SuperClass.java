package jvmclassload.PassiveRefrence;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SuperClass {

  static {
    System.out.println("super class");
  }

  public static int value = 127;

  public static final String HELLWORLD = "hello world";
}
