package javapattern.design.isp;

public interface BadExample {
  void first();

  void last();

  void next();

  void previous();

  String getExcerpt();

  String getFullRecord();

  void reIndexAll();

  void updateIndex();

  void search(String[] keywords);

  void getResultset();
}
