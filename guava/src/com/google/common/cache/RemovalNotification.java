package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.sun.istack.internal.Nullable;

import java.util.AbstractMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * A notification of the removal of a single entry. The key and/or value may be null if they were
 * already garbage collected.
 * <p>
 * Like other {@code Map.Entry} instances associated with {@code CacheBuilder}, this class holds
 * strong references to the key and value, regardless of the type of references the cache may be
 * using.
 * <p>
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public final class RemovalNotification<K, V> extends AbstractMap.SimpleImmutableEntry<K, V> {

    private static final long serialVersionUID = 0;

    private final RemovalCause cause;

    /**
     * Creates a new {@code RemovalNotification} for the given {@code key}/{@code value} pair, with
     * the given {@code cause} for the removal. The {@code key} and/or {@code value} may be {@code
     * null} if they were already garbage collected.
     *
     * @param key
     * @param value
     * @param cause
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> RemovalNotification<K, V> create(@Nullable K key, @Nullable V value, RemovalCause cause) {
        return new RemovalNotification(key, value, cause);
    }

    private RemovalNotification(@Nullable K key, @Nullable V value, RemovalCause cause) {
        super(key, value);
        this.cause = checkNotNull(cause);
    }

    /**
     * Returns the cause for which the entry was removed.
     */
    public RemovalCause getCause() {
        return cause;
    }

    /**
     * Returns {@code true} if there was an automatic removal due to eviction (the cause is neither
     * {@link RemovalCause#EXPLICIT} nor {@link RemovalCause#REPLACED}).
     */
    public boolean wasEvicted() {
        return cause.wasEvicted();
    }


}
