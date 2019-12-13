package javapattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 21/12/2017.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Adapter<T> {
  private DataSetObservable mObservable = new DataSetObservable();
  private MyObserver mObserver = new MyObserver();

  private List<T> mDatas = new ArrayList<T>();

  public void registerObserver() {
    mObservable.registerObserver(mObserver);
  }

  public void unregisterObserver() {
    mObservable.unregisterObserver(mObserver);
  }

  public void addData(T t) {
    synchronized (mDatas) {
      mDatas.add(t);
    }
    mObservable.notifyChanged();
  }

  public void removeData(T t) {
    synchronized (mDatas) {
      mDatas.remove(t);
    }
    mObservable.notifyChanged();
  }


  public class MyObserver extends DataSetObserver {
    @Override
    public void onChanged() {
      System.out.println("数据变化了");
    }

    @Override
    public void onInvalidated() {
      System.out.println("数据无用了");
    }
  }

}
