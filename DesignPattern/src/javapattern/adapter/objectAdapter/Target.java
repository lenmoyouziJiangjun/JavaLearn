package javapattern.adapter.objectAdapter;

/**
 * 客户端需要 sampleOperation1，sampleOperation2 两个方法。
 * 现有的Adaptee 无法满足，所以提供一个Adapter,
 */
public interface Target {
  /**
   * Class Adaptee contains operation sampleOperation1.
   */
  void sampleOperation1();

  /**
   * Class Adaptee doesn't contain operation sampleOperation2.
   */
  void sampleOperation2();
}
