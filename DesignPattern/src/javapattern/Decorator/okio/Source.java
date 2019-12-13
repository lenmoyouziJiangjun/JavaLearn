package javapattern.Decorator.okio;

import java.io.Closeable;
import java.io.IOException;

/**
 * Version 1.0
 * Created by lll on 17/10/9.
 * Description 类似InputStream .
 * copyright generalray4239@gmail.com
 */
public interface Source extends Closeable {
  /**
   * Removes at least 1, and up to {@code byteCount} bytes from this and appends
   * them to {@code sink}. Returns the number of bytes read, or -1 if this
   * source is exhausted.
   */
  long read(Buffer sink, long byteCount) throws IOException;

  /**
   * Returns the timeout for this source.
   */
  Timeout timeout();

  /**
   * Closes this source and releases the resources held by this source. It is an
   * error to read a closed source. It is safe to close a source more than once.
   */
  @Override
  void close() throws IOException;
}
