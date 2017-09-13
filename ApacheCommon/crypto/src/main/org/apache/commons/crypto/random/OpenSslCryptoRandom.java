package org.apache.commons.crypto.random;

import org.apache.commons.crypto.Crypto;
import org.apache.commons.crypto.utils.Utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

/**
 * Version 1.0
 * Created by lll on 17/9/5.
 * Description
 * copyright generalray4239@gmail.com
 */
public class OpenSslCryptoRandom extends Random implements CryptoRandom {

    private static final long serialVersionUID = -7828193502768789584L;

    private static final boolean nativeEnabled;

    private static final Throwable initException;

    static {
        boolean opensslLoaded = false;
        Throwable except = null;
        if (Crypto.isNativeCodeLoaded()) {
            try {
                OpenSslCryptoRandomNative.initSR();
                opensslLoaded = true;
            } catch (Exception t) {
                except = t;
            } catch (UnsatisfiedLinkError t) {
                except = t;
            }
        }
        nativeEnabled = opensslLoaded;
        initException = except;
    }

    /**
     * Judges whether native library was successfully loaded and initialised.
     *
     * @return true if library was loaded and initialised
     */
    public static boolean isNativeCodeEnabled() {
        return nativeEnabled;
    }


    /**
     * Constructs a {@link OpenSslCryptoRandom}.
     *
     * @param props the configuration properties - not used
     * @throws GeneralSecurityException if the native library could not be initialised successfully
     */
    // N.B. this class is not public/protected so does not appear in the main Javadoc
    // Please ensure that property use is documented in the enum CryptoRandomFactory.RandomProvider
    public OpenSslCryptoRandom(Properties props) throws GeneralSecurityException { // NOPMD
        if (!nativeEnabled) {
            if (initException != null) {
                throw new GeneralSecurityException("Native library could not be initialised", initException);
            }
            throw new GeneralSecurityException("Native library is not loaded");
        }
        // Check that nextRandBytes works (is this really needed?)
        if (!OpenSslCryptoRandomNative.nextRandBytes(new byte[1])) {
            throw new GeneralSecurityException("Check of nextRandBytes failed");
        }
    }

    /**
     * Generates a user-specified number of random bytes. It's thread-safe.
     *
     * @param bytes the array to be filled in with random bytes.
     */
    @Override
    public void nextBytes(byte[] bytes) {
        // Constructor ensures that native is enabled here
        if (!OpenSslCryptoRandomNative.nextRandBytes(bytes)) {
            // Assume it's a problem with the argument, rather than an internal issue
            throw new IllegalArgumentException("The nextRandBytes method failed");
        }
    }

    /**
     * Overrides {@link OpenSslCryptoRandom}. For {@link OpenSslCryptoRandom},
     * we don't need to set seed.
     *
     * @param seed the initial seed.
     */
    @Override
    public void setSeed(long seed) {
        // Self-seeding.
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
    final protected int next(int numBits) {
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

    /**
     * Overrides {@link java.lang.AutoCloseable#close()}.
     * Does nothing.
     */
    @Override
    public void close() {
    }
}
