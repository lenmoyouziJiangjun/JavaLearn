package org.apache.commons.crypto.cipher;

import org.apache.commons.crypto.utils.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
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
public class OpenSslCipher implements CryptoCipher {

  private final OpenSsl cipher;

  private final String transformation;

  /**
   * Constructs a {@link CryptoCipher} using JNI into OpenSSL
   *
   * @param props          properties for OpenSSL cipher (unused)
   * @param transformation transformation for OpenSSL cipher (algorithm/mode/padding)
   * @throws GeneralSecurityException if OpenSSL cipher initialize failed
   */
  public OpenSslCipher(Properties props, String transformation) throws GeneralSecurityException {
    this.transformation = transformation;

    Throwable loadingFailureReason = OpenSsl.getLoadingFailureReason();
    if (loadingFailureReason != null) {
      throw new RuntimeException(loadingFailureReason);
    }

    cipher = OpenSsl.getInstance(transformation);
  }


  @Override
  public int getBlockSize() {
    return CryptoCipherFactory.AES_BLOCK_SIZE;
  }

  @Override
  public String getAlgorithm() {
    return transformation;
  }

  @Override
  public void init(int mode, Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
    Utils.checkNotNull(key);
    Utils.checkNotNull(params);
    int cipherMode = OpenSsl.DECRYPT_MODE;
    if (mode == Cipher.ENCRYPT_MODE) {
      cipherMode = OpenSsl.ENCRYPT_MODE;
    }
    byte[] iv;
    if (params instanceof IvParameterSpec) {
      iv = ((IvParameterSpec) params).getIV();
    } else {
      // other AlgorithmParameterSpec such as GCMParameterSpec is not
      // supported now.
      throw new InvalidAlgorithmParameterException("Illegal parameters");
    }
    cipher.init(cipherMode, key.getEncoded(), iv);
  }

  @Override
  public int update(ByteBuffer inBuffer, ByteBuffer outBuffer) throws ShortBufferException {
    return cipher.update(inBuffer, outBuffer);
  }

  @Override
  public int update(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
    return cipher.update(input, inputOffset, inputLen, output, outputOffset);
  }

  /**
   * Encrypts or decrypts data in a single-part operation, or finishes a
   * multiple-part operation. The data is encrypted or decrypted, depending on
   * how this cipher was initialized.
   *
   * @param inBuffer  the input ByteBuffer
   * @param outBuffer the output ByteBuffer
   * @return int number of bytes stored in <code>output</code>
   * @throws BadPaddingException       if this cipher is in decryption mode, and
   *                                   (un)padding has been requested, but the decrypted data is not
   *                                   bounded by the appropriate padding bytes
   * @throws IllegalBlockSizeException if this cipher is a block cipher, no
   *                                   padding has been requested (only in encryption mode), and the
   *                                   total input length of the data processed by this cipher is not a
   *                                   multiple of block size; or if this encryption algorithm is unable
   *                                   to process the input data provided.
   * @throws ShortBufferException      if the given output buffer is too small to
   *                                   hold the result
   */
  @Override
  public int doFinal(ByteBuffer inBuffer, ByteBuffer outBuffer) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
    int n = cipher.update(inBuffer, outBuffer);
    return n + cipher.doFinal(outBuffer);
  }

  /**
   * Encrypts or decrypts data in a single-part operation, or finishes a
   * multiple-part operation.
   *
   * @param input        the input byte array
   * @param inputOffset  the offset in input where the input starts
   * @param inputLen     the input length
   * @param output       the byte array for the result
   * @param outputOffset the offset in output where the result is stored
   * @return the number of bytes stored in output
   * @throws ShortBufferException      if the given output byte array is too small
   *                                   to hold the result
   * @throws BadPaddingException       if this cipher is in decryption mode, and
   *                                   (un)padding has been requested, but the decrypted data is not
   *                                   bounded by the appropriate padding bytes
   * @throws IllegalBlockSizeException if this cipher is a block cipher, no
   *                                   padding has been requested (only in encryption mode), and the
   *                                   total input length of the data processed by this cipher is not a
   *                                   multiple of block size; or if this encryption algorithm is unable
   *                                   to process the input data provided.
   */
  @Override
  public int doFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
    int n = cipher.update(input, inputOffset, inputLen, output,
            outputOffset);
    return n + cipher.doFinal(output, outputOffset + n);
  }

  /**
   * Closes the OpenSSL cipher. Clean the OpenSsl native context.
   */
  @Override
  public void close() throws IOException {
    cipher.clean();
  }
}
