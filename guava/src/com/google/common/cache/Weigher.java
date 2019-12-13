package com.google.common.cache;/**
 * Created by liaoxueyan on 2019/2/26.
 */

import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public interface Weigher<K, V> {

  /**
   * Returns the weight of a cache entry. There is no unit for entry weights; rather they are simply
   * relative to each other.
   *
   * @return the weight of the entry; must be non-negative
   */
  int weigh(K key, V value);
}
