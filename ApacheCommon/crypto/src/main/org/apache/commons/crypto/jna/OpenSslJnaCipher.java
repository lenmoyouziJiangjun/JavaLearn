package org.apache.commons.crypto.jna;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.PointerByReference;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.utils.Utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description Implements the CryptoCipher using JNA into OpenSSL
 * copyright generalray4239@gmail.com
 */
public class OpenSslJnaCipher implements CryptoCipher {

  private PointerByReference algo;
  private final PointerByReference context;
  private final AlgorithmMode algMode;
  private final int padding;
  private final String transformation;


  public OpenSslJnaCipher(Properties props, String transformation) throws GeneralSecurityException {
    if (!OpenSslJna.isEnabled()) {
      throw new GeneralSecurityException("Could not enable JNA access", OpenSslJna.initialisationError());
    }
    this.transformation = transformation;
    Transform transform = tokenizeTransformation(transformation);
    algMode = AlgorithmMode.get(transform.algorithm, transform.mode);

    if (algMode != AlgorithmMode.AES_CBC && algMode != AlgorithmMode.AES_CTR) {
      throw new GeneralSecurityException("unknown algorithm " + transform.algorithm + "_" + transform.mode);
    }

    padding = Padding.get(transform.padding);
    context = OpenSslNativeJna.EVP_CIPHER_CTX_new();
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
  protected void finalize() throws Throwable {
    OpenSslNativeJna.EVP_CIPHER_CTX_free(context);
    super.finalize();
  }

  @Override
  public void init(int mode, Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
    Utils.checkNotNull(key);
    Utils.checkNotNull(params);
    int cipherMode = OpenSslNativeJna.OOSL_JNA_DECRYPT_MODE;
    if (mode == Cipher.ENCRYPT_MODE) {
      cipherMode = OpenSslNativeJna.OOSL_JNA_ENCRYPT_MODE;
    }
    byte[] iv;
    if (params instanceof IvParameterSpec) {
      iv = ((IvParameterSpec) params).getIV();
    } else {
      // other AlgorithmParameterSpec such as GCMParameterSpec is not
      // supported now.
      throw new InvalidAlgorithmParameterException("Illegal parameters");
    }
    if (algMode == AlgorithmMode.AES_CBC) {
      switch (key.getEncoded().length) {
        case 16:
          algo = OpenSslNativeJna.EVP_aes_128_cbc();
          break;
        case 24:
          algo = OpenSslNativeJna.EVP_aes_192_cbc();
          break;
        case 32:
          algo = OpenSslNativeJna.EVP_aes_256_cbc();
          break;
        default:
          throw new InvalidKeyException("keysize unsupported (" + key.getEncoded().length + ")");
      }
    } else {
      switch (key.getEncoded().length) {
        case 16:
          algo = OpenSslNativeJna.EVP_aes_128_ctr();
          break;
        case 24:
          algo = OpenSslNativeJna.EVP_aes_192_ctr();
          break;
        case 32:
          algo = OpenSslNativeJna.EVP_aes_256_ctr();
          break;
        default:
          throw new InvalidKeyException("keysize unsupported (" + key.getEncoded().length + ")");
      }
    }

    int retVal = OpenSslNativeJna.EVP_CipherInit_ex(context, algo, null, key.getEncoded(), iv, cipherMode);
    throwOnError(retVal);
    OpenSslNativeJna.EVP_CIPHER_CTX_set_padding(context, padding);
  }

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
  @Override
  public int update(ByteBuffer inBuffer, ByteBuffer outBuffer)
          throws ShortBufferException {
    int[] outlen = new int[1];
    int retVal = OpenSslNativeJna.EVP_CipherUpdate(context, outBuffer, outlen, inBuffer, inBuffer.remaining());
    throwOnError(retVal);
    int len = outlen[0];
    inBuffer.position(inBuffer.limit());
    outBuffer.position(outBuffer.position() + len);
    return len;
  }

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
  @Override
  public int update(byte[] input, int inputOffset, int inputLen,
                    byte[] output, int outputOffset) throws ShortBufferException {
    ByteBuffer outputBuf = ByteBuffer.wrap(output, outputOffset, output.length - outputOffset);
    ByteBuffer inputBuf = ByteBuffer.wrap(input, inputOffset, inputLen);
    return update(inputBuf, outputBuf);
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
  public int doFinal(ByteBuffer inBuffer, ByteBuffer outBuffer)
          throws ShortBufferException, IllegalBlockSizeException,
          BadPaddingException {
    int uptLen = update(inBuffer, outBuffer);
    int[] outlen = new int[1];
    int retVal = OpenSslNativeJna.EVP_CipherFinal_ex(context, outBuffer, outlen);
    throwOnError(retVal);
    int len = uptLen + outlen[0];
    outBuffer.position(outBuffer.position() + outlen[0]);
    return len;
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
  public int doFinal(byte[] input, int inputOffset, int inputLen,
                     byte[] output, int outputOffset) throws ShortBufferException,
          IllegalBlockSizeException, BadPaddingException {
    ByteBuffer outputBuf = ByteBuffer.wrap(output, outputOffset, output.length - outputOffset);
    ByteBuffer inputBuf = ByteBuffer.wrap(input, inputOffset, inputLen);
    return doFinal(inputBuf, outputBuf);
  }

  /**
   * Closes the OpenSSL cipher. Clean the OpenSsl native context.
   */
  @Override
  public void close() {
    if (context != null) {
      OpenSslNativeJna.EVP_CIPHER_CTX_cleanup(context);
      // Freeing the context multiple times causes a JVM crash
      // A work-round is to only free it at finalize time
      // TODO is that sufficient?
//            OpenSslNativeJna.EVP_CIPHER_CTX_free(context);
    }
  }

  private void throwOnError(int retVal) {
    if (retVal != 1) {
      NativeLong err = OpenSslNativeJna.ERR_peek_error();
      String errdesc = OpenSslNativeJna.ERR_error_string(err, null);

      if (context != null) {
        OpenSslNativeJna.EVP_CIPHER_CTX_cleanup(context);
      }
      throw new RuntimeException("return code " + retVal + " from OpenSSL. Err code is " + err + ": " + errdesc);
    }
  }

  /**
   * Tokenize the transformation.
   *
   * @param transformation current transformation
   * @return the Transform
   * @throws NoSuchAlgorithmException if the algorithm is not supported
   */
  private static Transform tokenizeTransformation(String transformation) throws NoSuchAlgorithmException {
    if (transformation == null) {
      throw new NoSuchAlgorithmException("No transformation given.");
    }
    /*
     * Array containing the components of a Cipher transformation: index 0:
     * algorithm (e.g., AES) index 1: mode (e.g., CTR) index 2: padding
     * (e.g., NoPadding)
     */
    String[] parts = new String[3];
    int count = 0;
    StringTokenizer parser = new StringTokenizer(transformation, "/");
    while (parser.hasMoreTokens() && count < 3) {
      parts[count++] = parser.nextToken().trim();
    }
    if (count != 3 || parser.hasMoreTokens()) {
      throw new NoSuchAlgorithmException(
              "Invalid transformation format: " + transformation);
    }
    return new Transform(parts[0], parts[1], parts[2]);
  }

  private static class Transform {
    final String algorithm;
    final String mode;
    final String padding;

    /**
     * Constructor of Transform.
     *
     * @param algorithm the algorithm name
     * @param mode      the mode name
     * @param padding   the padding name
     */
    public Transform(String algorithm, String mode, String padding) {
      this.algorithm = algorithm;
      this.mode = mode;
      this.padding = padding;
    }
  }


  /**
   * AlgorithmMode of JNA.  Currently only support AES/CTR/NoPadding.
   */
  private static enum AlgorithmMode

  {
    AES_CTR, AES_CBC;

    /**
     * Gets the AlgorithmMode instance.
     *
     * @param algorithm the algorithm name
     * @param mode      the mode name
     * @return the AlgorithmMode instance
     * @throws NoSuchAlgorithmException if the algorithm is not support
     */
    static AlgorithmMode get (String algorithm, String mode) throws NoSuchAlgorithmException {
    try {
      return AlgorithmMode.valueOf(algorithm + "_" + mode);
    } catch (Exception e) {
      throw new NoSuchAlgorithmException("Doesn't support algorithm: " + algorithm + " and mode: " + mode);
    }
  }
  }

  private static enum Padding

  {
    NoPadding, PKCS5Padding;

    /**
     * Gets the Padding instance.
     *
     * @param padding the padding name
     * @return the AlgorithmMode instance
     * @throws NoSuchPaddingException if the algorithm is not support
     */
    static int get (String padding) throws NoSuchPaddingException {
    try {
      return Padding.valueOf(padding).ordinal();
    } catch (Exception e) {
      throw new NoSuchPaddingException("Doesn't support padding: " + padding);
    }
  }
  }
}
