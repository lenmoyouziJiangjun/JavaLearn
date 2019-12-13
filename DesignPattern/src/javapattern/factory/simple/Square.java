package javapattern.factory.simple;

public class Square implements Shape {
  public void draw() {
    System.out.println("Square.draw()");
  }

  public void erase() {
    System.out.println("Square.erase()");
  }
}
