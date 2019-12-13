package org.apache.commons.crypto.utils;

import org.apache.commons.crypto.stream.input.Input;

import java.io.IOException;
import java.io.InputStream;

/**
 * Version 1.0
 * Created by lll on 17/9/1.
 * Description
 * copyright generalray4239@gmail.com
 */
public class IoUtils {

  /**
   * The private constructor of {@link IoUtils}.
   */
  private IoUtils() {
  }

  /**
   * Does the readFully based on the Input read.
   *
   * @param in  the input stream of bytes.
   * @param buf the buffer to be read.
   * @param off the start offset in array buffer.
   * @param len the maximum number of bytes to read.
   * @throws IOException if an I/O error occurs.
   */
  public static void readFully(InputStream in, byte buf[], int off, int len)
          throws IOException {
    int toRead = len;
    while (toRead > 0) {
      int ret = in.read(buf, off, toRead);
      if (ret < 0) {
        throw new IOException("Premature EOF from inputStream");
      }
      toRead -= ret;
      off += ret;
    }
  }

  /**
   * Does the readFully based on Input's positioned read. This does not change
   * the current offset of the stream and is thread-safe.
   *
   * @param in       the input source.
   * @param position the given position.
   * @param buffer   the buffer to be read.
   * @param length   the maximum number of bytes to read.
   * @param offset   the start offset in array buffer.
   * @throws IOException if an I/O error occurs.
   */
  public static void readFully(Input in, long position, byte[] buffer,
                               int offset, int length) throws IOException {
    int nread = 0;
    while (nread < length) {
      int nbytes = in.read(position + nread, buffer, offset + nread,
              length - nread);
      if (nbytes < 0) {
        throw new IOException(
                "End of stream reached before reading fully.");
      }
      nread += nbytes;
    }
  }

  /**
   * Closes the Closeable objects and <b>ignore</b> any {@link IOException} or
   * null pointers. Must only be used for cleanup in exception handlers.
   *
   * @param closeables the objects to close.
   */
  public static void cleanup(java.io.Closeable... closeables) {
    for (java.io.Closeable c : closeables) {
      if (c != null) {
        try {
          c.close();
        } catch (IOException e) { // NOPMD
        }
      }
    }
  }
}
