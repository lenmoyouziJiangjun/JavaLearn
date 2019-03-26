package effectivejava;

/**
 * Version 1.0
 * Created by lll on 2019/2/25.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObjectEnhance {
    //1、尽可能降低成员的访问范围：
    //public 共有;
    // protected 类和子类，
    // 省略：包级
    // private 只有当前类。


    //final static 使用tips
    //1:常量构成类提供的整个抽象中的一部分，可以通过公有的静态final域来暴露这些常量
    //public final static
    public static final String TAG = "";

    //2、非常糟糕的代码，用public static final 修改数组，数组不会变，但是数组成员会变
    public static final String[] TAGS = new String[10];

    /**
     3、组合由于继承
     3.1、继承违背了封装原则，在包内部使用继承是非常安全的，但对于跨包访问的集成是非常危险的，因为你不晓得父类什么时候就增加一个方法逻辑。
     3.2、包装类，不适合用在回调框架中。在回调框架中，对象把自身的引用传递给其它的对象，用于后续的调用。
     3.3、只有子类真正是超类的子类型时，才适合继承。is a 关系
     */

    /**4、接口由于抽象类*/


    /**
     * 5、常量接口：只有静态常量的接口
     * 5.1、谨慎使用常量接口：常量接口模式是对接口的不良使用，在类的内部使用某些常量，存粹是实现细节，不用暴露出去。
     */
    public interface PhysicalConstants {
        double AVOGADROS_NUMBERS = 6.12;
    }


    /**
     * 策略模式
     *   函数指针的主要用途是实现策略模式，为了在java中实现策略模式，需要申明一个接口来表示该策略，并且为每一个具体策略提供一个实现逻辑。
     *   当一个具体策略重复使用的时候，他的类通常就要被实现为私有的静态成员类。并通过静态的final域导出
     */

    /**
     * 嵌套类：定义在一个类的内部的类，java成为内部类。kotlin分为内部类（inner修饰） 和嵌套类
     *
     * java的嵌套类有四种类型：
     *    1、静态成员类（static class）
     *    2、非静态内部类
     *    3、匿名类
     *    4、局部类
     *
     *
     * 如果什么成员类不要求访问外围类实例，就要始终把static修饰符放在他的申明中。是之成为静态成员类
     */
}
