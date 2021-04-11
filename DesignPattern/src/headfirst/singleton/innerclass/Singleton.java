package headfirst.singleton.innerclass;

/**
 * Version 1.0
 * Created by lll on 2020-08-27.
 * Description
 *
 * <pre>
 *     基于懒汉模式的优化的singleton
 *
 * </pre>
 *
 * copyright generalray4239@gmail.com
 */
public class Singleton {


    public static class InnerSingleton {
        private static Singleton instance = new Singleton();//自行创建实例
    }

    public static Singleton getInstance() {
        return InnerSingleton.instance;// 返回内部类中的静态变量 }
    }

}
