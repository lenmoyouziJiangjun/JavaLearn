package javapattern.factory.factory;

public class GrapeGardener implements FruitGardener {
  public Fruit factory() {
    return new Apple();
  }
}
