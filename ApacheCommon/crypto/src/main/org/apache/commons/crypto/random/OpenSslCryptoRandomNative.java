package org.apache.commons.crypto.random;

/**
 * Version 1.0
 * Created by lll on 17/9/5.
 * Description
 * copyright generalray4239@gmail.com
 */
public class OpenSslCryptoRandomNative {
  /**
   * The private constructor of {@link OpenSslCryptoRandomNative}.
   */
  private OpenSslCryptoRandomNative() {
  }

  /**
   * Declares a native method to initialize SR.
   */
  public native static void initSR();

  /**
   * Judges whether use {@link OpenSslCryptoRandomNative} to generate the
   * user-specified number of random bits.
   *
   * @param bytes the array to be filled in with random bytes.
   * @return true if use {@link OpenSslCryptoRandomNative} to generate the
   * user-specified number of random bits.
   */
  public native static boolean nextRandBytes(byte[] bytes);
}
