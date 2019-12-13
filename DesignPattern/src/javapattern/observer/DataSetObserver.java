package javapattern.observer;

/**
 * Version 1.0
 * Created by lll on 21/12/2017.
 * Description
 * copyright generalray4239@gmail.com
 */
public abstract class DataSetObserver {

  /**
   * This method is called when the entire data set has changed,
   */
  public void onChanged() {
    // Do nothing
  }

  /**
   * This method is called when the entire data becomes invalid,
   */
  public void onInvalidated() {
    // Do nothing
  }
}
