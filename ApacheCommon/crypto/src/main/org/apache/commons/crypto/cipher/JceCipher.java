package org.apache.commons.crypto.cipher;

import org.apache.commons.crypto.utils.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Properties;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description
 * copyright generalray4239@gmail.com
 */
public class JceCipher implements CryptoCipher {

  private final Cipher cipher;

  /**
   * Constructs a {@link CryptoCipher} based on JCE Cipher {@link Cipher}.
   *
   * @param props          properties for JCE cipher (only uses {@link CryptoCipherFactory#JCE_PROVIDER_KEY})
   * @param transformation transformation for JCE cipher (algorithm/mode/padding)
   * @throws GeneralSecurityException if JCE cipher initialize failed
   */
  // N.B. this class is not public/protected so does not appear in the main Javadoc
  // Please ensure that property use is documented in the enum CryptoRandomFactory.RandomProvider
  public JceCipher(Properties props, String transformation) throws GeneralSecurityException {
    final String provider = props.getProperty(CryptoCipherFactory.JCE_PROVIDER_KEY);
    if (provider == null || provider.isEmpty()) {
      cipher = Cipher.getInstance(transformation);
    } else {
      cipher = Cipher.getInstance(transformation, provider);
    }
  }

  @Override
  public int getBlockSize() {
    return cipher.getBlockSize();
  }

  @Override
  public String getAlgorithm() {
    return cipher.getAlgorithm();
  }

  @Override
  public void init(int mode, Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
    Utils.checkNotNull(key);
    Utils.checkNotNull(params);

    // Jce uses the javax.crypto.Cipher modes; no need to convert the input
    cipher.init(mode, key, params);
  }

  @Override
  public int update(ByteBuffer inBuffer, ByteBuffer outBuffer) throws ShortBufferException {
    return cipher.update(inBuffer, outBuffer);
  }

  @Override
  public int update(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
    return cipher.update(input, inputOffset, inputLen, output, outputOffset);
  }

  @Override
  public int doFinal(ByteBuffer inBuffer, ByteBuffer outBuffer) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
    return cipher.doFinal(inBuffer, outBuffer);
  }

  @Override
  public int doFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
    return cipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
  }

  @Override
  public void close() throws IOException {

  }
}
