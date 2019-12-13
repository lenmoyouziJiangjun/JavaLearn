package javapattern.single;

/**
 * Version 1.0
 * Created by lll on 2018/5/2.
 * Description
 * <pre>
 *     静态内部类方式实现单例模式
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class StaticSingleton {

  private StaticSingleton() {
  }

  private static class Holder {
    private static StaticSingleton sInstance = new StaticSingleton();
  }

  public static StaticSingleton getInstance() {
    return Holder.sInstance;
  }
}
