package io.reactivex.tck;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 10/01/2018.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Learn {


  public static void learnRxJava1() {
    Observable.range(1, 5).subscribe(new DefaultObserver<Integer>() {
      public void onNext(@NonNull Integer integer) {

      }

      public void onError(@NonNull Throwable e) {

      }

      public void onComplete() {

      }
    });

    Observable.just("adkjafdsa", "dahkdhfaksd").subscribe(new Observer<String>() {
      public void onSubscribe(@NonNull Disposable d) {

      }

      public void onNext(@NonNull String s) {

      }

      public void onError(@NonNull Throwable e) {

      }

      public void onComplete() {

      }
    });

    Observable.create(new ObservableOnSubscribe<String>() {
      public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("Observable");
        e.setDisposable(new Disposable() {
          @Override
          public void dispose() {

          }

          @Override
          public boolean isDisposed() {
            return false;
          }
        });
      }
    }).subscribeOn(Schedulers.io())
            .subscribe(new Observer<String>() {
              public void onSubscribe(@NonNull Disposable d) {

              }

              public void onNext(@NonNull String s) {

              }

              public void onError(@NonNull Throwable e) {

              }

              public void onComplete() {

              }
            });
  }


  public static void learnRxJavaSubject() {
    Observer ob = new Observer() {
      public void onSubscribe(@NonNull Disposable d) {

      }

      public void onNext(@NonNull Object o) {

      }

      public void onError(@NonNull Throwable e) {

      }

      public void onComplete() {

      }
    };
    PublishSubject.create().subscribeOn(Schedulers.io()).subscribe(ob);
  }


  public static void learnRxJava2() {
    Flowable.create(new FlowableOnSubscribe<List<User>>() {
      public void subscribe(@NonNull FlowableEmitter<List<User>> e) throws Exception {
        List<User> users = new ArrayList<User>();
        e.onNext(users);
      }
    }, BackpressureStrategy.MISSING).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<List<User>>() {
      public void onSubscribe(Subscription s) {

      }

      public void onNext(List<User> users) {

      }

      public void onError(Throwable t) {

      }

      public void onComplete() {

      }
    });
  }

  public static class User {

  }


  public static void main(String[] args) {

  }
}
