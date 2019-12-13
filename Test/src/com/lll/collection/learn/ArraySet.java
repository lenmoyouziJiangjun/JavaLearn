package com.lll.collection.learn;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Version 1.0
 * Created by lll on 08/12/2017.
 * Description
 * 自定义一个ArraySet集合
 * set 元素不重复的集合：跟hashSet的区别在于hashSet是无序的。HashSet底层采用了HashMap。
 * ArraySet is a generic set data structure that is designed to be more memory efficient than a
 * traditional {@link java.util.HashSet}.  The design is very similar to
 * {@link ArrayMap},
 * copyright generalray4239@gmail.com
 */
public class ArraySet<E> implements Collection<E>, Set<E> {

  /**
   * The minimum amount by which the capacity of a ArraySet will increase.
   * This is tuned to be relatively space-efficient.
   */
  private static final int BASE_SIZE = 4;

  /**
   * Maximum number of entries to have in array caches.
   */
  private static final int CACHE_SIZE = 10;

  /**
   * Caches of small array objects to avoid spamming garbage.  The cache
   * Object[] variable is a pointer to a linked list of array objects.
   * The first entry in the array is a pointer to the next array in the
   * list; the second entry is a pointer to the int[] hash code array for it.
   */
  static Object[] mBaseCache;
  static int mBaseCacheSize;
  static Object[] mTwiceBaseCache;
  static int mTwiceBaseCacheSize;

  final boolean mIdentityHashCode;
  int[] mHashes;
  Object[] mArray;
  int mSize;
  MapCollections<E, E> mCollections;

  /**
   * Create a new empty ArraySet.  The default capacity of an array map is 0, and
   * will grow once items are added to it.
   */
  public ArraySet() {
    this(0, false);
  }

  /**
   * Create a new ArraySet with a given initial capacity.
   */
  public ArraySet(int capacity) {
    this(capacity, false);
  }

  public ArraySet(int capacity, boolean identityHashCode) {
    mIdentityHashCode = identityHashCode;
    if (capacity == 0) {
      mHashes = new int[]{};
      mArray = new Object[]{};
    } else {
      allocArrays(capacity);
    }
    mSize = 0;
  }

  private void allocArrays(final int size) {
    if (size == (BASE_SIZE * 2)) {
      synchronized (ArraySet.class) {
        if (mTwiceBaseCache != null) {
          final Object[] array = mTwiceBaseCache;
          try {
            mArray = array;
            mTwiceBaseCache = (Object[]) array[0];
            mHashes = (int[]) array[1];
            array[0] = array[1] = null;
            mTwiceBaseCacheSize--;
            return;
          } catch (Exception e) {
            e.printStackTrace();
          }
          mTwiceBaseCache = null;
          mTwiceBaseCacheSize = 0;
        }
      }
    } else if (size == BASE_SIZE) {
      synchronized (ArraySet.class) {
        if (mBaseCache != null) {
          final Object[] array = mBaseCache;
          try {
            mArray = array;
            mBaseCache = (Object[]) array[0];
            mHashes = (int[]) array[1];
            array[0] = array[1] = null;
            mBaseCacheSize--;
            return;
          } catch (ClassCastException e) {
          }
          mBaseCache = null;
          mBaseCacheSize = 0;
        }
      }
    }
    mHashes = new int[size];
    mArray = new Object[size];
  }

  /**
   * @param hashes
   * @param array
   * @param size
   */
  private static void freeArrays(final int[] hashes, final Object[] array, final int size) {
    if (hashes.length == BASE_SIZE * 2) {
      synchronized (ArraySet.class) {
        if (mTwiceBaseCacheSize < CACHE_SIZE) {
          array[0] = mTwiceBaseCacheSize;
          array[1] = hashes;
          for (int i = size - 1; i >= 2; i--) {
            array[i] = null;
          }
          mTwiceBaseCache = array;
          mTwiceBaseCacheSize++;
        }
      }
    } else if (hashes.length == BASE_SIZE) {
      synchronized (ArraySet.class) {
        if (mBaseCacheSize < CACHE_SIZE) {
          array[0] = mBaseCache;
          array[1] = hashes;
          for (int i = size - 1; i >= 2; i--) {
            array[i] = null;
          }
          mBaseCache = array;
          mBaseCacheSize++;
        }
      }
    }
  }

  /**
   * 采用二分查找的方式查找数据。网数组里面添加数据的时候，判断数据是否存在数组里面，如果存在就不添加了
   *
   * @param key
   * @param hash
   * @return
   */
  private int indexOf(Object key, int hash) {
    final int N = mSize;
    if (N == 0) {
      return ~0;
    }
    int index = ContainerHelpers.binarySearch(mHashes, N, hash);
    if (index < 0) {//找到中间值
      return index;
    }

    int end;
    for (end = index + 1; end < N && mHashes[end] == hash; end++) {//往后查找
      if (key.equals(mArray[end])) {
        return end;
      }
    }

    for (int i = index - 1; i >= 0 && mHashes[i] == hash; i--) {//往前查找
      if (key.equals(mArray[i])) {
        return i;
      }
    }
    return ~end;
  }

  private int indexOfNull() {
    final int N = mSize;
    if (N == 0) {
      return ~0;
    }
    int index = ContainerHelpers.binarySearch(mHashes, N, 0);

    if (index < 0) {
      return index;
    }
    // If the key at the returned index matches, that's what we want.
    if (null == mArray[index]) {
      return index;
    }

    // Search for a matching key after the index.
    int end;
    for (end = index + 1; end < N && mHashes[end] == 0; end++) {
      if (null == mArray[end]) return end;
    }

    // Search for a matching key before the index.
    for (int i = index - 1; i >= 0 && mHashes[i] == 0; i--) {
      if (null == mArray[i]) return i;
    }

    // Key not found -- return negative value indicating where a
    // new entry for this key should go.  We use the end of the
    // hash chain to reduce the number of array entries that will
    // need to be copied when inserting.
    return ~end;
  }

  public void ensureCapacity(int minimumCapacity) {
    if (mHashes.length < minimumCapacity) {
      final int[] ohashes = mHashes;
      final Object[] oarray = mArray;
      allocArrays(minimumCapacity);
      if (mSize > 0) {
        System.arraycopy(ohashes, 0, mHashes, 0, mSize);
        System.arraycopy(oarray, 0, mArray, 0, mSize);
      }
      freeArrays(ohashes, oarray, mSize);
    }
  }


  @Override
  public int size() {
    return mSize;
  }

  @Override
  public boolean isEmpty() {
    return mSize <= 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  private int indexOf(Object key) {
    return key == null ? indexOfNull() : indexOf(key, mIdentityHashCode ? System.identityHashCode(key) : key.hashCode());
  }

  /**
   * Return the value at the given index in the array.
   *
   * @param index The desired index, must be between 0 and {@link #size()}-1.
   * @return Returns the value stored at the given index.
   */
  public E valueAt(int index) {
    return (E) mArray[index];
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[mSize];
    System.arraycopy(mArray, 0, result, 0, mSize);
    return result;
  }

  @Override
  public <T> T[] toArray(T[] array) {
    if (array.length < mSize) {
      T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), mSize);
      array = newArray;
    }
    System.arraycopy(mArray, 0, array, 0, mSize);
    if (array.length > mSize) {
      array[mSize] = null;
    }
    return array;
  }

  @Override
  public boolean add(E value) {
    final int hash;
    int index;
    if (value == null) {
      hash = 0;
      index = indexOfNull();
    } else {
      hash = mIdentityHashCode ? System.identityHashCode(value) : value.hashCode();
      index = indexOf(value, hash);
    }
    if (index >= 0) {//集合里面已经有数据了
      return false;
    }
    index = ~index;
    if (mSize > mHashes.length) {//集合已经满了
      final int n = mSize >= (BASE_SIZE * 2) ? (mSize + (mSize >> 1))
              : (mSize >= BASE_SIZE ? (BASE_SIZE * 2) : BASE_SIZE);
      final int[] ohashes = mHashes;
      final Object[] oarray = mArray;
      allocArrays(n);
      if (mHashes.length > 0) {
        System.arraycopy(ohashes, 0, mHashes, 0, ohashes.length);
        System.arraycopy(oarray, 0, mArray, 0, oarray.length);
      }
      freeArrays(ohashes, oarray, mSize);
    }
    if (index < mSize) {
      System.arraycopy(mHashes, index, mHashes, index + 1, mSize - index);
      System.arraycopy(mArray, index, mArray, index + 1, mSize - index);
    }
    mHashes[index] = hash;
    mArray[index] = value;
    mSize++;
    return true;
  }

  /**
   * Special fast path for appending items to the end of the array without validation.
   * The array must already be large enough to contain the item.
   *
   * @param value
   */
  public void append(E value) {//
    final int index = mSize;
    final int hash = value == null ? 0
            : (mIdentityHashCode ? System.identityHashCode(value) : value.hashCode());
    if (index >= mHashes.length) {
      throw new IllegalStateException();
    }
    if (index > 0 && mHashes[index - 1] > hash) {
      add(value);
      return;
    }
    mSize = index + 1;
    mHashes[index] = hash;
    mArray[index] = value;
  }

  /**
   * Perform a {@link #add(Object)} of all values in <var>array</var>
   *
   * @param array The array whose contents are to be retrieved.
   */
  public void addAll(ArraySet<? extends E> array) {
    final int N = array.mSize;
    ensureCapacity(mSize + N);
    if (mSize == 0) {
      if (N > 0) {
        System.arraycopy(array.mHashes, 0, mHashes, 0, N);
        System.arraycopy(array.mArray, 0, mArray, 0, N);
        mSize = N;
      }
    } else {
      for (int i = 0; i < N; i++) {
        add(array.valueAt(i));
      }
    }
  }

  @Override
  public boolean remove(Object object) {
    final int index = indexOf(object);
    if (index >= 0) {
      removeAt(index);
      return true;
    }
    return false;
  }

  /**
   * Remove the key/value mapping at the given index.
   *
   * @param index The desired index, must be between 0 and {@link #size()}-1.
   * @return Returns the value that was stored at this index.
   */
  public E removeAt(int index) {
    final Object old = mArray[index];
    if (mSize <= 1) {
      freeArrays(mHashes, mArray, mSize);
      mHashes = new int[]{};
      mArray = new Object[]{};
      mSize = 0;
    } else {
      if (mHashes.length > (BASE_SIZE * 2) && mSize < mHashes.length / 3) {
        final int n = mSize > (BASE_SIZE * 2) ? (mSize + (mSize >> 1)) : (BASE_SIZE * 2);

        final int[] ohashes = mHashes;
        final Object[] oarray = mArray;
        allocArrays(n);

        mSize--;
        if (index > 0) {
          System.arraycopy(ohashes, 0, mHashes, 0, index);
          System.arraycopy(oarray, 0, mArray, 0, index);
        }
        if (index < mSize) {
          System.arraycopy(ohashes, index + 1, mHashes, index, mSize - index);
          System.arraycopy(oarray, index + 1, mArray, index, mSize - index);
        }
      } else {
        mSize--;
        if (index < mSize) {
          System.arraycopy(mHashes, index + 1, mHashes, index, mSize - index);
          System.arraycopy(mArray, index + 1, mArray, index, mSize - index);
        }
        mArray[mSize] = null;
      }
    }
    return (E) old;
  }

  @Override
  public void clear() {
    if (mSize != 0) {
      freeArrays(mHashes, mArray, mSize);
      mHashes = new int[]{};
      mArray = new Object[]{};
      mSize = 0;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Set) {
      Set<?> set = (Set<?>) object;
      if (size() != set.size()) {
        return false;
      }

      try {
        for (int i = 0; i < mSize; i++) {
          E mine = valueAt(i);
          if (!set.contains(mine)) {
            return false;
          }
        }
      } catch (NullPointerException ignored) {
        return false;
      } catch (ClassCastException ignored) {
        return false;
      }
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int[] hashes = mHashes;
    int result = 0;
    for (int i = 0, s = mSize; i < s; i++) {
      result += hashes[i];
    }
    return result;
  }

  private MapCollections<E, E> getCollection() {
    if (mCollections == null) {
      mCollections = new MapCollections<E, E>() {
        @Override
        protected int colGetSize() {
          return mSize;
        }

        @Override
        protected Object colGetEntry(int index, int offset) {
          return mArray[index];
        }

        @Override
        protected int colIndexOfKey(Object key) {
          return indexOf(key);
        }

        @Override
        protected int colIndexOfValue(Object value) {
          return indexOf(value);
        }

        @Override
        protected Map<E, E> colGetMap() {
          throw new UnsupportedOperationException("not a map");
        }

        @Override
        protected void colPut(E key, E value) {
          add(key);
        }

        @Override
        protected E colSetValue(int index, E value) {
          throw new UnsupportedOperationException("not a map");
        }

        @Override
        protected void colRemoveAt(int index) {
          removeAt(index);
        }

        @Override
        protected void colClear() {
          clear();
        }
      };
    }
    return mCollections;
  }

  /**
   * Return an {@link Iterator} over all values in the set.
   * <p>
   * <p><b>Note:</b> this is a fairly inefficient way to access the array contents, it
   * requires generating a number of temporary objects and allocates additional state
   * information associated with the container that will remain for the life of the container.</p>
   */
  @Override
  public Iterator<E> iterator() {
    return getCollection().getKeySet().iterator();
  }

  /**
   * Determine if the array set contains all of the values in the given collection.
   *
   * @param collection The collection whose contents are to be checked against.
   * @return Returns true if this array set contains a value for every entry
   * in <var>collection</var>, else returns false.
   */
  @Override
  public boolean containsAll(Collection<?> collection) {
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      if (!contains(it.next())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Perform an {@link #add(Object)} of all values in <var>collection</var>
   *
   * @param collection The collection whose contents are to be retrieved.
   */
  @Override
  public boolean addAll(Collection<? extends E> collection) {
    ensureCapacity(mSize + collection.size());
    boolean added = false;
    for (E value : collection) {
      added |= add(value);
    }
    return added;
  }

  /**
   * Remove all values in the array set that exist in the given collection.
   *
   * @param collection The collection whose contents are to be used to remove values.
   * @return Returns true if any values were removed from the array set, else false.
   */
  @Override
  public boolean removeAll(Collection<?> collection) {
    boolean removed = false;
    for (Object value : collection) {
      removed |= remove(value);
    }
    return removed;
  }

  /**
   * Remove all values in the array set that do <b>not</b> exist in the given collection.
   *
   * @param collection The collection whose contents are to be used to determine which
   *                   values to keep.
   * @return Returns true if any values were removed from the array set, else false.
   */
  @Override
  public boolean retainAll(Collection<?> collection) {
    boolean removed = false;
    for (int i = mSize - 1; i >= 0; i--) {
      if (!collection.contains(mArray[i])) {
        removeAt(i);
        removed = true;
      }
    }
    return removed;
  }
}
