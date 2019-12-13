package javapattern.observer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 21/12/2017.
 * Description
 * 观察者模式的订阅角色
 * copyright generalray4239@gmail.com
 */
public abstract class Observable<T> {
  /**
   * The list of observers.  An observer can be in the list at most
   * once and will never be null.
   */
  protected final Set<T> mObservers = new LinkedHashSet<T>();

  /**
   * Adds an observer to the list. The observer cannot be null and it must not already
   * be registered.
   *
   * @param observer the observer to register
   * @throws IllegalArgumentException the observer is null
   * @throws IllegalStateException    the observer is already registered
   */
  public void registerObserver(T observer) {
    if (observer == null) {
      throw new IllegalArgumentException("the oberver is null");
    }
    synchronized (mObservers) {
      mObservers.add(observer);
    }
  }

  /**
   * Removes a previously registered observer. The observer must not be null and it
   * must already have been registered.
   *
   * @param observer the observer to unregister
   * @throws IllegalArgumentException the observer is null
   * @throws IllegalStateException    the observer is not yet registered
   */
  public void unregisterObserver(T observer) {
    if (observer == null) {
      throw new IllegalArgumentException("the oberver is null");
    }
    synchronized (mObservers) {
      mObservers.remove(observer);
    }
  }


  /**
   * Remove all registered observers.
   */
  public void unregisterAll() {
    synchronized (mObservers) {
      mObservers.clear();
    }
  }
}
