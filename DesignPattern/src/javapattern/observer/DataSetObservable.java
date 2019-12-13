package javapattern.observer;

/**
 * Version 1.0
 * Created by lll on 21/12/2017.
 * Description
 * copyright generalray4239@gmail.com
 */
public class DataSetObservable extends Observable<DataSetObserver> {

  /**
   * Invokes {@link DataSetObserver#onChanged} on each observer.
   * Called when the contents of the data set have changed.  The recipient
   * will obtain the new contents the next time it queries the data set.
   */
  public void notifyChanged() {
    synchronized (mObservers) {
      // since onChanged() is implemented by the app, it could do anything, including
      // removing itself from {@link mObservers} - and that could cause problems if
      // an iterator is used on the ArrayList {@link mObservers}.
      // to avoid such problems, just march thru the list in the reverse order.
      mObservers.forEach(observer -> {
        observer.onChanged();
      });
    }
  }

  /**
   * Invokes {@link DataSetObserver#onInvalidated} on each observer.
   * Called when the data set is no longer valid and cannot be queried again,
   * such as when the data set has been closed.
   */
  public void notifyInvalidated() {
    synchronized (mObservers) {
      mObservers.forEach(observer -> {
        observer.onInvalidated();
      });
    }
  }

}
