package effectivejava;

import java.util.EnumSet;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 2019/2/27.
 * Description
 * <pre>
 *   一、枚举
 *      1、枚举的实质：int值
 *      2、java枚举的思想：就是通过公有的静态final域为每个常量导出实例的类，因为没有可以访问的构造器，枚举类型是真正的final
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class EnumLearn {


  /**
   * 每一个枚举都有一个ordinal() 方法，它返回每个枚举常量在类型中的数字位置.
   * 在开发中不要使用ordinal方法，
   */
  public enum Ensemble1

  {
    SOLO, DUET, TRIO, QUARTET, QUINTET;


    public int numberOfMusicians () {
    return ordinal() + 1; //
  }
  }

  /**
   * 正确获取枚举常量对应int值的写法
   */
  public enum SettingSwitch

  {
    ON(0), OFF(1);

    private final int num;

    SettingSwitch( int num){
    this.num = num;
  }

    public int getNum () {
    return num;
  }
  }

  private void test(SettingSwitch sswitch) {
    int num = sswitch.getNum() == 0 ? 1 : 3;
  }


  /**
   * 用EnumSet代替位域
   * <p>
   * 在开发中，我们经常会写这样的代码。参考android window flag
   */
  private static final int STYLE_BOLD = 1 << 0;
  private static final int STYLE_ITALIC = 1 << 2;
  private static final int STYLE_UNDERLINE = 1 << 3;
  private static final int STYLE_STRIKETHROUGH = 1 << 4;

  public void applyStyles(int styles) {
  }

  /**
   * 位域操作
   */
  private void testBitOP() {
    //我们经常会这么调用方法
    EnumLearn learn = new EnumLearn();
    learn.applyStyles(STYLE_BOLD | STYLE_ITALIC); //用OR操作，将几个常量合并到一个集合中，称作位域
  }

  /**
   * 如果枚举类型要用在集合中，不应该用位域来表示，可以用EnumSet 或者EnumMap
   * java 提供EnumSet类：有效的表示从耽搁枚举类型中提取多个值的集合。
   */
  public enum Style

  {
    BOLD, ITALIC
  }

  public void applyStyles(Set<Style> styles) {

  }

  public void testEnum() {
    EnumLearn learn = new EnumLearn();
    learn.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
  }


  public static void main(String[] args) {

  }

}

  /**
   *
   */
  enum Planet {

        MERCURY(3.32,233),
        NEPTUNE(1.12,23);

private final double mass;
private final double radius;
private final double surfaceGravity;

private static final double G=6.67300-11;

        Planet(double mass,double radius){
        this.mass=mass;
        this.radius=radius;
        this.surfaceGravity=G*mass/radius*radius;
        }
        }
