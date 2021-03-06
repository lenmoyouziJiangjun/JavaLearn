package org.apache.commons.crypto.random;

import org.apache.commons.crypto.utils.IoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Version 1.0
 * Created by lll on 17/9/5.
 * Description
 * copyright generalray4239@gmail.com
 */
public class OsCryptoRandom extends Random implements CryptoRandom {
  private static final long serialVersionUID = 6391500337172057900L;

  private static final int RESERVOIR_LENGTH = 8192;

  private transient FileInputStream stream;

  private final byte[] reservoir = new byte[RESERVOIR_LENGTH];

  private int pos = reservoir.length;


  /**
   * Fills the reservoir.
   *
   * @param min the length.
   */
  private void fillReservoir(int min) {
    if (pos >= reservoir.length - min) {
      try {
        IoUtils.readFully(stream, reservoir, 0, reservoir.length);
      } catch (IOException e) {
        throw new RuntimeException("failed to fill reservoir", e);
      }
      pos = 0;
    }
  }

  /**
   * Constructs a {@link OsCryptoRandom}.
   *
   * @param props the configuration properties.
   *              Uses {@link CryptoRandomFactory#DEVICE_FILE_PATH_KEY} to determine the
   *              path to the random device, default is
   *              {@link CryptoRandomFactory#DEVICE_FILE_PATH_DEFAULT}
   */
  // N.B. this class is not public/protected so does not appear in the main Javadoc
  // Please ensure that property use is documented in the enum CryptoRandomFactory.RandomProvider
  public OsCryptoRandom(Properties props) {
    File randomDevFile = new File(
            props.getProperty(CryptoRandomFactory.DEVICE_FILE_PATH_KEY,
                    CryptoRandomFactory.DEVICE_FILE_PATH_DEFAULT));

    try {
      close();
      this.stream = new FileInputStream(randomDevFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      fillReservoir(0);
    } catch (RuntimeException e) {
      close();
      throw e;
    }
  }

  /**
   * Overrides {@link CryptoRandom#nextBytes(byte[])}. Generates random bytes
   * and places them into a user-supplied byte array. The number of random
   * bytes produced is equal to the length of the byte array.
   *
   * @param bytes the array to be filled in with random bytes.
   */
  @Override
  synchronized public void nextBytes(byte[] bytes) {
    int off = 0;
    int n = 0;
    while (off < bytes.length) {
      fillReservoir(0);
      n = Math.min(bytes.length - off, reservoir.length - pos);
      System.arraycopy(reservoir, pos, bytes, off, n);
      off += n;
      pos += n;
    }
  }

  /**
   * Overrides Random#next(). Generates the next pseudorandom number.
   * Subclasses should override this, as this is used by all other methods.
   *
   * @param nbits random bits.
   * @return the next pseudorandom value from this random number generator's
   * sequence.
   */
  @Override
  synchronized protected int next(int nbits) {
    fillReservoir(4);
    int n = 0;
    for (int i = 0; i < 4; i++) {
      n = (n << 8) | (reservoir[pos++] & 0xff);
    }
    return n & (0xffffffff >> (32 - nbits));
  }

  /**
   * Overrides {@link java.lang.AutoCloseable#close()}. Closes the OS stream.
   */
  @Override
  synchronized public void close() {
    if (stream != null) {
      IoUtils.cleanup(stream);
      stream = null;
    }
  }
}
