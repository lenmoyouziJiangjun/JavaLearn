package javapattern.factory.factory;

/**
 * 具体工厂
 */
public class AppleGardener implements FruitGardener {
    public Fruit factory() {
        return new Apple();
    }
}
