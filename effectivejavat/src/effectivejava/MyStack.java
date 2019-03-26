package effectivejava;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Version 1.0
 * Created by lll on 2019/2/25.
 * Description
 * 一、内存泄漏常见场景
 *    1、隐藏到的内存泄漏：就是一些对象不使用了，还保留着对象的引用。
 *    2、缓存
 *    3、监听器和回调：（只注册了，没有反注册）
 * 二、清空过期的对象引用
 *    1、清空过期对象的引用，应该是一种例外，而不是一种规范行为。
 *       消除过期引用最好的方法：让包含该引用的变量结束其生命周期
 *    2、一般情况下，只要类是自己管理内存，程序员就应该警惕内存泄漏问题。
 *      一旦元素被释放掉，则该元素中包含的任何引用都应该被清空
 *
 * 三、缓存：WeakHashMap的使用：
 *    只要在缓存之外存在对某个项的键的引用，该项就有意义，那么久可以用WeakHashMap代表缓存，
 *    当缓存中的项过期之后，他们就会自动被删除。记住只有所要的缓存项的生命周期是由该键的外部引用而不是值决定的时候，WeakHashMap才有用处
 *
 * 四、定期清理缓存没用的项：
 *     1、定义一个后台线程（Timer或者ScheduledPoolExecutor），
 *     2、在给缓存添加新元素的时候清理。LinkedHashMap
 * copyright generalray4239@gmail.com
 */
public class MyStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;
    public MyStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object obj) {
        ensureCapacity();
        elements[size++] = obj;
    }

    /**
     * 1、清空过期对象的引用，应该是一种例外，而不是一种规范行为。
     *    消除过期引用最好的方法：让包含该引用的变量结束其生命周期
     *
     * 2、一般情况下，只要类是自己管理内存，程序员就应该警惕内存泄漏问题。
     *    一旦元素被释放掉，则该元素中包含的任何引用都应该被清空
     *
     *
     * @return
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object obj= elements[--size];
        elements[size] = null;  //取出对象后，将该对应位置置为空
        return obj;
    }

    /**
     * 注意这里有一个隐藏的内存泄漏，就是栈中维护着过期的对象引用。有些对象已经不会再使用了，
     *
     * @return
     */
    public Object pop2() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
