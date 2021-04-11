package com.lll.reference;

import com.lll.reference.bean.Activity;

import java.lang.ref.ReferenceQueue;
import java.util.*;

/**
 * Version 1.0
 * Created by lll on 2020-02-26.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ReferenceQueueLearn {

    ReferenceQueue queue = new ReferenceQueue();

    Map<String, KeyedWeakReference> watchedObjects = new HashMap<>();

    Timer mTimer = new Timer();

    public ReferenceQueueLearn() {

    }

    private void watch() {
        for (int i = 0; i < 10; i++) {
            Activity activity = new Activity("test" + i);
            activity.startInnerClass();
            try {
                Thread.sleep(2000); //睡1.5秒，表示执行完成
                activity.ondestroy();
                KeyedWeakReference reference = new KeyedWeakReference(activity, activity.name, activity.name, System.currentTimeMillis(), queue);
                watchedObjects.put(activity.name, reference);

                Thread.sleep(1500); //睡1.5秒，表示执行完成

                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        removeWeaklyReachableObjects();
                        KeyedWeakReference ref = watchedObjects.get(activity.name);

                        if (ref != null) {
                            //map has data
                            ref.retainedUptimeMillis = System.currentTimeMillis() - ref.watchUptimeMillis;
                            System.out.println(ref.mKey + "__存活___" + ref.retainedUptimeMillis);
                        }
                    }
                }, 1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doGc() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.gc();//不一定马上执行
                System.out.println("调用GC----------------");
                Runtime.getRuntime().runFinalization();
                System.gc();
            }
        }, 3000, 3000);

    }


    private void removeWeaklyReachableObjects() {
        try {
            KeyedWeakReference ref = null;
            do {
                ref = (KeyedWeakReference) queue.poll();
                if (ref != null) {
                    System.out.println("remove queue key " + ref.mKey);
                    watchedObjects.remove(ref.mKey);
                }
            } while (ref != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void test() {
        doGc();
        watch();
    }


    public static void main(String[] args) {
        ReferenceQueueLearn learn = new ReferenceQueueLearn();
        learn.test();
    }

}
