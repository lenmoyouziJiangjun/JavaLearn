package javapattern.factory.abs;

public interface ComputerProducer {
  Cpu createCpu();

  Ram createRam();
}
