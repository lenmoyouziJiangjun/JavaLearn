package org.apache.commons.crypto.jna;

import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.random.CryptoRandom;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description
 * Public class to give access to the package protected class objects
 * copyright generalray4239@gmail.com
 */
public class OpenSslJna {

  /**
   * @return The cipher class of JNA implementation
   */
  public static Class<? extends CryptoCipher> getCipherClass() {
    return OpenSslJnaCipher.class;
  }

  /**
   * @return The random class of JNA implementation
   */
  public static Class<? extends CryptoRandom> getRandomClass() {
    return OpenSslJnaCryptoRandom.class;
  }

  /**
   * @return true if JNA native loads successfully
   */
  public static boolean isEnabled() {
    return OpenSslNativeJna.INIT_OK;
  }

  /**
   * @return the error of JNA
   */
  public static Throwable initialisationError() {
    return OpenSslNativeJna.INIT_ERROR;
  }


}
