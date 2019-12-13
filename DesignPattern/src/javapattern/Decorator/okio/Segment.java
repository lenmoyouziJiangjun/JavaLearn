package javapattern.Decorator.okio;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.security.Signature;

/**
 * Version 1.0
 * Created by lll on 17/10/9.
 * Description
 * A segment of a buffer.
 * <p>
 * <p>Each segment in a buffer is a circularly-linked list node referencing the following and
 * preceding segments in the buffer.
 * <p>
 * <p>Each segment in the pool is a singly-linked list node referencing the rest of segments in the
 * pool.
 * <p>
 * <p>The underlying byte arrays of segments may be shared between buffers and byte strings. When a
 * segment's byte array is shared the segment may not be recycled, nor may its byte data be changed.
 * The lone exception is that the owner segment is allowed to append to the segment, writing data at
 * {@code limit} and beyond. There is a single owning segment for each byte array. Positions,
 * limits, prev, and next references are not shared.
 * copyright generalray4239@gmail.com
 */
public class Segment {

  /**
   * The size of all segments in bytes.
   */
  static final int SIZE = 8192;

  /**
   * Segments will be shared when doing so avoids {@code arraycopy()} of this many bytes.
   */
  static final int SHARE_MINIMUM = 1024;

  final byte[] data;

  /**
   * The next byte of application data byte to read in this segment.
   */
  int pos;

  /***/
  int limit;

  boolean shared;
  boolean owner;
  Segment next;
  Segment prev;

  Segment() {
    this.data = new byte[SIZE];
    this.owner = true;
    this.shared = false;
  }

  Segment(Segment shareFrom) {
    this(shareFrom.data, shareFrom.pos, shareFrom.limit);
    shareFrom.shared = true;
  }

  Segment(byte[] data, int pos, int limit) {
    this.data = data;
    this.pos = pos;
    this.limit = limit;
    this.owner = false;
    this.shared = true;
  }

  /**
   * Removes this segment of a circularly-linked list and returns its successor.
   * Returns null if the list is now empty.
   */
  public
  @Nullable
  Segment pop() {
    Segment result = next != null ? next : null;
    prev.next = next;
    next.prev = prev;
    next = null;
    prev = null;
    return result;
  }

  /**
   * Appends {@code segment} after this segment in the circularly-linked list.
   * Returns the pushed segment.
   */
  public Segment push(Segment segment) {
    segment.prev = this;
    segment.next = next;
    next.prev = segment;
    next = segment;
    return segment;
  }

  /**
   * Splits this head of a circularly-linked list into two segments. The first
   * segment contains the data in {@code [pos..pos+byteCount)}. The second
   * segment contains the data in {@code [pos+byteCount..limit)}. This can be
   * useful when moving partial segments from one buffer to another.
   * <p>
   * <p>Returns the new head of the circularly-linked list.
   * <p>
   * 将一个Segment的数据拆成两个，注意，这里有trick。如果有两个Segment相同的字节超过了SHARE_MINIMUM （1024），那么这两个Segment会共享一份数据，这样就省去了开辟内存及复制内存的开销，达到了提高性能的目的。
   *
   * @param byteCount
   */
  public Segment split(int byteCount) {
    if (byteCount <= 0 || byteCount > limit - pos) throw new IllegalArgumentException();
    Segment prefix;

    // We have two competing performance goals:
    //  - Avoid copying data. We accomplish this by sharing segments.
    //  - Avoid short shared segments. These are bad for performance because they are readonly and
    //    may lead to long chains of short segments.
    // To balance these goals we only share segments when the copy will be large.
    if (byteCount >= SHARE_MINIMUM) {
      prefix = new Segment(this);
    } else {
      prefix = SegmentPool.take();
      System.arraycopy(data, pos, prefix.data, 0, byteCount);
    }

    prefix.limit = prefix.pos + byteCount;
    pos += byteCount;
    prev.push(prefix);
    return prefix;
  }

  /**
   * Call this when the tail and its predecessor may both be less than half
   * full. This will copy data so that segments can be recycled.
   * 当Segment的前一个和自身的数据量都不足一半时，会对segement进行压缩，把自身的数据写入到前一个Segment中，然后将自身进行回收。
   */
  public void compact() {
    if (prev == this) {
      throw new IllegalStateException();
    }
    if (!prev.owner) {
      return;// Cannot compact: prev isn't writable.
    }
    int byteCount = limit - pos;
    int availableByteCount = SIZE - prev.limit + (prev.shared ? 0 : prev.pos);
    if (byteCount > availableByteCount) return; // Cannot compact: not enough writable space.
    writeTo(prev, byteCount);
    pop();
    SegmentPool.recycle(this);
  }

  public void writeTo(Segment sink, int byteCount) {
    if (!sink.owner) {
      throw new IllegalStateException();
    }
    if (sink.limit + byteCount > SIZE) {
      // We can't fit byteCount bytes at the sink's current position. Shift sink first.
      if (sink.shared) {
        throw new IllegalArgumentException();
      }
      if (sink.limit + byteCount - sink.pos > SIZE) {
        throw new IllegalArgumentException();
      }
      System.arraycopy(sink.data, sink.pos, sink.data, 0, sink.limit - sink.pos);
      sink.limit -= sink.pos;
      sink.pos = 0;
    }
    System.arraycopy(data, pos, sink.data, sink.limit, byteCount);
    sink.limit += byteCount;
    pos += byteCount;
  }
}
