package thread;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Version 1.0
 * Created by lll on 2019/3/6.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObservableSet<E> {


    public interface SetObserver<E> {
        void added(ObservableSet<E> set, E element);
    }

    private final List<SetObserver> observers = new ArrayList<>();
    private final Set<E> sets = new HashSet<E>();


    public void addObserver(SetObserver observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver observer : observers) {
                observer.added(this, element);
            }
        }
    }

    public boolean add(E element) {
        boolean added = sets.add(element);
        if (added) {
            notifyElementAdded(element);
        }
        return added;
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean result = false;
        for (E element : collection) {
            result |= add(element);
        }
        return result;
    }


    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>();
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
//                测试一：
//                if (element == 23) {
//                    set.removeObserver(this); //数组遍历是删除，
//                    //Exception in thread "main" java.util.ConcurrentModificationException
//                }

                if (element == 23) {
                    ExecutorService service = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        service.submit(new Runnable() {
                            @Override
                            public void run() {//放到子线程删除。到23了就卡死，为什么呢？死锁了
                                //子线程想要删除，但是没有锁，锁在主线程，
                                //主线程又在等子线程删除，
                                set.removeObserver(observer);
                            }
                        }).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        service.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

}


