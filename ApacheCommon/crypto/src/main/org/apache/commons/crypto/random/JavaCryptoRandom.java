package org.apache.commons.crypto.random;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Version 1.0
 * Created by lll on 17/9/5.
 * Description
 * copyright generalray4239@gmail.com
 */
public class JavaCryptoRandom implements CryptoRandom {

    private final SecureRandom instance;

    /**
     * Constructs a {@link JavaCryptoRandom}.
     *
     * @param properties the configuration properties.
     *                   Uses the key {@link CryptoRandomFactory#SECURE_RANDOM_JAVA_ALGORITHM_KEY}
     *                   to get the name of the algorithm, with a default of
     *                   {@link CryptoRandomFactory#JAVA_ALGORITHM_DEFAULT}
     * @throws NoSuchAlgorithmException if no Provider supports a
     *                                  SecureRandomSpi implementation for the specified algorithm.
     */
    public JavaCryptoRandom(Properties properties) throws NoSuchAlgorithmException {
        instance = SecureRandom.getInstance(properties.getProperty(
                CryptoRandomFactory.JAVA_ALGORITHM_KEY,
                CryptoRandomFactory.JAVA_ALGORITHM_DEFAULT));
    }


    @Override
    public void nextBytes(byte[] bytes) {
        instance.nextBytes(bytes);
    }

    @Override
    public void close() throws IOException {

    }
}
