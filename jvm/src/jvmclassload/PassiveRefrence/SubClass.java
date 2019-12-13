package jvmclassload.PassiveRefrence;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class SubClass extends SuperClass {

  static {
    System.out.println("subclass init");
  }

}
