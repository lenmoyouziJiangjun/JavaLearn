package jvmclassload.PassiveRefrence;

/**
 * Version 1.0
 * Created by lll on 17/8/7.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Test2 {

    public static void main(String[] args){
        //输出hello world？ why?
        //final 变量存储到常量池了，对类的常量的引用，不需要在乎类是否初始化。按照这么说的话，定义final常量不占用堆内存，不涉及到内存泄漏
        System.out.println(SuperClass.HELLWORLD);
    }

}
