package com.lll.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Version 1.0
 * Created by lll on 2020-08-06.
 * Description
 * copyright generalray4239@gmail.com
 */
public class KeyedWeakReference<T> extends WeakReference<T> {

    String mKey;
    String mDescription;
    long watchUptimeMillis;

    volatile float retainedUptimeMillis = -1L;
    static volatile float heapDumpUptimeMillis = -0L;


    public KeyedWeakReference(T referent, String key, String description, long watchUptimeMillis, ReferenceQueue<? super T> q) {
        super(referent, q);
        this.mDescription = description;
        this.mKey = key;
        this.watchUptimeMillis = watchUptimeMillis;
    }
}
