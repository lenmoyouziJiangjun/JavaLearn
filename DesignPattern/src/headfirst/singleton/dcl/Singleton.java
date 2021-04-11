package headfirst.singleton.dcl;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//

/**
 * double check 双重检查，
 *    实现起来比较麻烦
 *
 *
 */
public class Singleton {
  private volatile static Singleton uniqueInstance;

  private Singleton() {
  }

  public static Singleton getInstance() {
    if (uniqueInstance == null) {
      synchronized (Singleton.class) {
        if (uniqueInstance == null) {
          uniqueInstance = new Singleton();
        }
      }
    }
    return uniqueInstance;
  }
}
