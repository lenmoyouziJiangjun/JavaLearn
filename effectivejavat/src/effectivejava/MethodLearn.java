package effectivejava;

/**
 * Version 1.0
 * Created by lll on 2019/2/27.
 * Description
 * <pre>
 *    1、检查方法参数的有效性
 *    2、对于参数类型可以被不可信任方子类化的参数，请不要使用clone方法进行保护性拷贝
 *    3、将局部变量的作用域最小化（使类和成员的可访问性最小化）。
 *       在第一次使用它的地方声明。
 *    4、for each 循环优于for循环，避免了循环索引容易出错的问题。
 *    5、尽量使用标准库。
 *    6、当可选择的时候，基本类型优先于装箱类型
 *    7、如果其它类型更适合，则尽量避免使用字符串
 *    8、字符串的连接的性能问题：对于大小固定的字符串，用"+"连接不影响。对于大量字符串拼接操作，最好使用StringBuilder
 *
 *
 * 二、保护性拷贝
 *    1、每当编写方法或者构造器时，如果他要允许客户提供的对象进入到内部数据结构中，则有必要考虑保护性拷贝。
 *    客户提供的对象是否有可能是变化的，如果是，且类不能容忍对象进入数据结构后发生变化。就要进行保护性拷贝。
 *     例如：客户提供的对象引用作为内部Set实例的元素或者作为内部Map的key，
 *
 * 三、慎用重载
 *
 * 四、返回零长度的数组或者集合，而不是null。
 *
 *
 *
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class MethodLearn {

    /**
     * 返回零长度的数组或者集合，而不是null。
     */
    private static final String[] EMPTY_ARRAY = new String[0];
    public String[] getCheeseArray(){
        return EMPTY_ARRAY;
    }


}
