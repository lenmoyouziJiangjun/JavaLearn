package javapattern.design.isp;

public interface Searcher {
  void search(String[] keywords);

  void getResultset();

  /**
   * @link aggregation
   * @directed
   */
  /*#Resultset lnkResultset;*/
}
