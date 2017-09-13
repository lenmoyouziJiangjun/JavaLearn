package org.apache.commons.crypto.jna;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.PointerByReference;
import org.apache.commons.crypto.random.CryptoRandom;
import org.apache.commons.crypto.utils.Utils;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

/**
 * Version 1.0
 * Created by lll on 17/9/4.
 * Description
 * OpenSSL secure random using JNA. This implementation is thread-safe.
 * copyright generalray4239@gmail.com
 */
public class OpenSslJnaCryptoRandom extends Random implements CryptoRandom {

    private static final long serialVersionUID = -7128193502768749585L;
    private final boolean rdrandEnabled;
    private PointerByReference rdrandEngine;

    /**
     * Constructs a {@link OpenSslJnaCryptoRandom}.
     *
     * @param props the configuration properties (not used)
     * @throws GeneralSecurityException if could not enable JNA access
     */
    public OpenSslJnaCryptoRandom(Properties props) throws GeneralSecurityException {
        if (!OpenSslJna.isEnabled()) {
            throw new GeneralSecurityException("Could not enable JNA access", OpenSslJna.initialisationError());
        }
        boolean rdrandLoaded = false;
        try {
            OpenSslNativeJna.ENGINE_load_rdrand();
            rdrandEngine = OpenSslNativeJna.ENGINE_by_id("rdrand");
            int ENGINE_METHOD_RAND = 0x0008;
            if (rdrandEngine != null) {
                int rc = OpenSslNativeJna.ENGINE_init(rdrandEngine);
                if (rc != 0) {
                    int rc2 = OpenSslNativeJna.ENGINE_set_default(rdrandEngine, ENGINE_METHOD_RAND);
                    if (rc2 != 0) {
                        rdrandLoaded = true;
                    }
                }
            }
        } catch (Exception e) {
            throw new NoSuchAlgorithmException();
        }

        rdrandEnabled = rdrandLoaded;
        if (!rdrandLoaded) {
            closeRdrandEngine();
        }
    }

    /**
     * @param bytes the byte array to fill with random bytes
     */
    public void nextBytes(byte[] bytes) {
        synchronized (OpenSslJnaCryptoRandom.class) {
            //this method is synchronized for now
            //to support multithreading https://wiki.openssl.org/index.php/Manual:Threads(3) needs to be done

            if (rdrandEnabled && OpenSslNativeJna.RAND_get_rand_method().equals(OpenSslNativeJna.RAND_SSLeay())) {
                close();
                throw new RuntimeException("rdrand should be used but default is detected");
            }

            ByteBuffer buf = ByteBuffer.allocateDirect(bytes.length);
            int retVal = OpenSslNativeJna.RAND_bytes(buf, bytes.length);
            throwOnError(retVal);
            buf.rewind();
            buf.get(bytes, 0, bytes.length);
        }
    }

    public void setSeed(long seed) {

    }

    /**
     * Overrides Random#next(). Generates an integer containing the
     * user-specified number of random bits(right justified, with leading
     * zeros).
     *
     * @param numBits number of random bits to be generated, where 0
     *                {@literal <=} <code>numBits</code> {@literal <=} 32.
     * @return int an <code>int</code> containing the user-specified number of
     * random bits (right justified, with leading zeros).
     */
    @Override
    protected int next(int numBits) {
        Utils.checkArgument(numBits >= 0 && numBits <= 32);
        int numBytes = (numBits + 7) / 8;
        byte b[] = new byte[numBytes];
        int next = 0;

        nextBytes(b);
        for (int i = 0; i < numBytes; i++) {
            next = (next << 8) + (b[i] & 0xFF);
        }
        return next >>> (numBytes * 8 - numBits);
    }

    @Override
    public void close() {
        closeRdrandEngine();
        OpenSslNativeJna.ENGINE_cleanup();
    }

    /**
     * Closes the rdrand engine.
     */
    private void closeRdrandEngine() {
        if (rdrandEngine != null) {
            OpenSslNativeJna.ENGINE_finish(rdrandEngine);
            OpenSslNativeJna.ENGINE_free(rdrandEngine);
        }
    }

    /**
     * Checks if rdrand engine is used to retrieve random bytes
     *
     * @return true if rdrand is used, false if default engine is used
     */
    public boolean isRdrandEnabled() {
        return rdrandEnabled;
    }

    /**
     * @param retVal the result value of error.
     */
    private void throwOnError(int retVal) {
        if (retVal != 1) {
            NativeLong err = OpenSslNativeJna.ERR_peek_error();
            String errdesc = OpenSslNativeJna.ERR_error_string(err, null);
            close();
            throw new RuntimeException("return code " + retVal + " from OpenSSL. Err code is " + err + ": " + errdesc);
        }
    }
}
