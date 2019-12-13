package org.apache.commons.crypto.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description
 * <p>
 * The interface of cryptographic cipher for encryption and decryption.
 * <p>
 * <p>
 * Note that implementations must provide a constructor that has 2 parameters:
 * <br>
 * a Properties instance and a String (transformation)
 * <p>
 * copyright generalray4239@gmail.com
 */
public interface CryptoCipher extends Closeable {

  /**
   * Returns the block size (in bytes).
   *
   * @return the block size (in bytes), or 0 if the underlying algorithm is
   * not a block cipher
   */
  int getBlockSize();

  /**
   * Returns the algorithm name of this {@code CryptoCipher} object.
   * <p>
   * <p>This is the same name that was specified in one of the
   * {@code CryptoCipherFactory#getInstance} calls that created this
   * {@code CryptoCipher} object..
   *
   * @return the algorithm name of this {@code CryptoCipher} object.
   */
  String getAlgorithm();

  /**
   * Initializes the cipher with mode, key and iv.
   *
   * @param mode   {@link javax.crypto.Cipher#ENCRYPT_MODE} or
   *               {@link javax.crypto.Cipher#DECRYPT_MODE}
   * @param key    crypto key for the cipher
   * @param params the algorithm parameters
   * @throws InvalidKeyException                if the given key is inappropriate for
   *                                            initializing this cipher, or its keysize exceeds the maximum
   *                                            allowable keysize (as determined from the configured jurisdiction
   *                                            policy files).
   * @throws InvalidAlgorithmParameterException if the given algorithm
   *                                            parameters are inappropriate for this cipher, or this cipher
   *                                            requires algorithm parameters and <code>params</code> is null, or
   *                                            the given algorithm parameters imply a cryptographic strength
   *                                            that would exceed the legal limits (as determined from the
   *                                            configured jurisdiction policy files).
   */
  void init(int mode, Key key, AlgorithmParameterSpec params)
          throws InvalidKeyException, InvalidAlgorithmParameterException;

  /**
   * Continues a multiple-part encryption/decryption operation. The data is
   * encrypted or decrypted, depending on how this cipher was initialized.
   *
   * @param inBuffer  the input ByteBuffer
   * @param outBuffer the output ByteBuffer
   * @return int number of bytes stored in <code>output</code>
   * @throws ShortBufferException if there is insufficient space in the output
   *                              buffer
   */
  int update(ByteBuffer inBuffer, ByteBuffer outBuffer)
          throws ShortBufferException;

  /**
   * Continues a multiple-part encryption/decryption operation. The data is
   * encrypted or decrypted, depending on how this cipher was initialized.
   *
   * @param input        the input byte array
   * @param inputOffset  the offset in input where the input starts
   * @param inputLen     the input length
   * @param output       the byte array for the result
   * @param outputOffset the offset in output where the result is stored
   * @return the number of bytes stored in output
   * @throws ShortBufferException if there is insufficient space in the output
   *                              byte array
   */
  int update(byte[] input, int inputOffset, int inputLen, byte[] output,
             int outputOffset) throws ShortBufferException;

  /**
   * Encrypts or decrypts data in a single-part operation, or finishes a
   * multiple-part operation.
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
  int doFinal(ByteBuffer inBuffer, ByteBuffer outBuffer)
          throws ShortBufferException, IllegalBlockSizeException,
          BadPaddingException;

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
  int doFinal(byte[] input, int inputOffset, int inputLen, byte[] output,
              int outputOffset) throws ShortBufferException,
          IllegalBlockSizeException, BadPaddingException;

}
