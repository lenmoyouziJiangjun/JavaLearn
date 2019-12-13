package org.apache.commons.crypto.random;

import java.io.Closeable;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description
 * copyright generalray4239@gmail.com
 */
public interface CryptoRandom extends Closeable {
  /**
   * Generates random bytes and places them into a user-supplied byte array.
   * The number of random bytes produced is equal to the length of the byte
   * array.
   *
   * @param bytes the byte array to fill with random bytes
   */
  void nextBytes(byte[] bytes);
}
