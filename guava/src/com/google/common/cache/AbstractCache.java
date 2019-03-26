package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public abstract class AbstractCache<K, V> implements Cache<K, V> {


    /**
     * Accumulates statistics during the operation of a {@link Cache} for presentation by
     * {@link Cache#stats}. This is solely intended for consumption by {@code Cache} implementors.
     *
     * @since 10.0
     */
    public interface StatsCounter {
        /**
         * Records cache hits. This should be called when a cache request returns a cached value.
         *
         * @param count the number of hits to record
         * @since 11.0
         */
        void recordHits(int count);

        /**
         * Records cache misses. This should be called when a cache request returns a value that was not
         * found in the cache. This method should be called by the loading thread, as well as by threads
         * blocking on the load. Multiple concurrent calls to {@link Cache} lookup methods with the same
         * key on an absent value should result in a single call to either {@code recordLoadSuccess} or
         * {@code recordLoadException} and multiple calls to this method, despite all being served by
         * the results of a single load operation.
         *
         * @param count the number of misses to record
         * @since 11.0
         */
        void recordMisses(int count);

        /**
         * Records the successful load of a new entry. This should be called when a cache request causes
         * an entry to be loaded, and the loading completes successfully. In contrast to
         * {@link #recordMisses}, this method should only be called by the loading thread.
         *
         * @param loadTime the number of nanoseconds the cache spent computing or retrieving the new
         *                 value
         */
        void recordLoadSuccess(long loadTime);

        /**
         * Records the failed load of a new entry. This should be called when a cache request causes an
         * entry to be loaded, but an exception is thrown while loading the entry. In contrast to
         * {@link #recordMisses}, this method should only be called by the loading thread.
         *
         * @param loadTime the number of nanoseconds the cache spent computing or retrieving the new
         *                 value prior to an exception being thrown
         */
        void recordLoadException(long loadTime);

        /**
         * Records the eviction of an entry from the cache. This should only been called when an entry
         * is evicted due to the cache's eviction strategy, and not as a result of manual
         * {@linkplain Cache#invalidate invalidations}.
         */
        void recordEviction();

        /**
         * Returns a snapshot of this counter's values. Note that this may be an inconsistent view, as
         * it may be interleaved with update operations.
         */
        CacheStats snapshot();
    }

    public static final class SimpleStatsCounter implements StatsCounter {
        private final LongAddable hitCount = LongAddables.create();
        private final LongAddable missCount = LongAddables.create();
        private final LongAddable loadSuccessCount = LongAddables.create();
        private final LongAddable loadExceptionCount = LongAddables.create();
        private final LongAddable totalLoadTime = LongAddables.create();
        private final LongAddable evictionCount = LongAddables.create();

        /**
         * Constructs an instance with all counts initialized to zero.
         */
        public SimpleStatsCounter() {
        }

        /**
         * @since 11.0
         */
        @Override
        public void recordHits(int count) {
            hitCount.add(count);
        }

        /**
         * @since 11.0
         */
        @Override
        public void recordMisses(int count) {
            missCount.add(count);
        }

        @Override
        public void recordLoadSuccess(long loadTime) {
            loadSuccessCount.increment();
            totalLoadTime.add(loadTime);
        }

        @Override
        public void recordLoadException(long loadTime) {
            loadExceptionCount.increment();
            totalLoadTime.add(loadTime);
        }

        @Override
        public void recordEviction() {
            evictionCount.increment();
        }

        @Override
        public CacheStats snapshot() {
            return new CacheStats(
                    hitCount.sum(),
                    missCount.sum(),
                    loadSuccessCount.sum(),
                    loadExceptionCount.sum(),
                    totalLoadTime.sum(),
                    evictionCount.sum());
        }

        /**
         * Increments all counters by the values in {@code other}.
         */
        public void incrementBy(StatsCounter other) {
            CacheStats otherStats = other.snapshot();
            hitCount.add(otherStats.hitCount());
            missCount.add(otherStats.missCount());
            loadSuccessCount.add(otherStats.loadSuccessCount());
            loadExceptionCount.add(otherStats.loadExceptionCount());
            totalLoadTime.add(otherStats.totalLoadTime());
            evictionCount.add(otherStats.evictionCount());
        }
    }
}
