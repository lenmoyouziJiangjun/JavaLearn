package headfirst.singleton.stat;


/**
 *
 * 单例模式： 饿汉式
 *
 *  成员变量的时候就开始赋值   uniqueInstance = new Singleton();
 *
 *  1、存在的问题，加入我只访问一些静态变量的成员，也会初始化对象。 不能延迟加载
 *  2、 如果构造函数里面静态变量的话的，访问会报错
 *
 */
public class Singleton {
  private static Singleton uniqueInstance = new Singleton();

  private Singleton() {
  }

  public static Singleton getInstance() {
    return uniqueInstance;
  }
}
