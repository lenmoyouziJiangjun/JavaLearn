package org.apache.commons.crypto.utils;

import org.apache.commons.crypto.Crypto;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Version 1.0
 * Created by lll on 17/9/1.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Utils {

    private static final String SYSTEM_PROPERTIES_FILE = Crypto.CONF_PREFIX
            + "properties";

    private static class DefaultPropertiesHolder {
        static final Properties DEFAULT_PROPERTIES = createDefaultProperties();
    }

    private Utils() {

    }

    /**
     * Loads system properties when configuration file of the name
     * {@link #SYSTEM_PROPERTIES_FILE} is found.
     *
     * @return the default properties
     */
    private static Properties createDefaultProperties() {
        Properties defaultedProps = new Properties(System.getProperties());
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(SYSTEM_PROPERTIES_FILE);
            if (inputStream == null) {
                return defaultedProps;// no configuration file is found
            }
            //Load property file
            Properties fileProps = new Properties();
            fileProps.load(inputStream);
            inputStream.close();

            Enumeration<?> names = fileProps.propertyNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                // ensure System properties override ones in the file so one can override the file on the command line
                if (System.getProperty(name) == null) {
                    defaultedProps.setProperty(name, fileProps.getProperty(name));
                }
            }
        } catch (Exception ex) {
            System.err.println("Could not load '"
                    + SYSTEM_PROPERTIES_FILE
                    + "' from classpath: " + ex.toString());
        }
        return defaultedProps;
    }

    /**
     * Gets a properties instance that defaults to the System Properties
     * plus any other properties found in the file
     * {@link #SYSTEM_PROPERTIES_FILE}
     *
     * @return a Properties instance with defaults
     */
    public static Properties getDefaultProperties() {
        return new Properties(DefaultPropertiesHolder.DEFAULT_PROPERTIES);
    }

    /**
     * Gets the properties merged with default properties.
     *
     * @param newProp User-defined properties
     * @return User-defined properties with the default properties
     */
    public static Properties getProperties(Properties newProp) {
        Properties properties = new Properties(DefaultPropertiesHolder.DEFAULT_PROPERTIES);
        properties.putAll(newProp);
        return properties;
    }

    /**
     * Helper method to create a CryptoCipher instance and throws only
     * IOException.
     *
     * @param props          The <code>Properties</code> class represents a set of
     *                       properties.
     * @param transformation the name of the transformation, e.g.,
     *                       <i>AES/CBC/PKCS5Padding</i>.
     *                       See the Java Cryptography Architecture Standard Algorithm Name Documentation
     *                       for information about standard transformation names.
     * @return the CryptoCipher instance.
     * @throws IOException if an I/O error occurs.
     */
    public static CryptoCipher getCipherInstance(
            String transformation, Properties props)
            throws IOException {
        try {
            return CryptoCipherFactory.getCryptoCipher(transformation, props);
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to
     * the calling method.
     *
     * @param expression a boolean expression.
     * @throws IllegalArgumentException if expression is false.
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks the truth of an expression.
     *
     * @param expression   a boolean expression.
     * @param errorMessage the exception message to use if the check fails; will
     *                     be converted to a string using <code>String
     *                     .valueOf(Object)</code>.
     * @throws IllegalArgumentException if expression is false.
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null.
     *
     * @param <T>       the type of the object reference to be checked.
     * @param reference an object reference.
     * @return the non-null reference that was validated.
     * @throws NullPointerException if reference is null.
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression involving the state of the calling
     * instance, but not involving any parameters to the calling method.
     *
     * @param expression a boolean expression.
     * @throws IllegalStateException if expression is false.
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * Splits class names sequence into substrings, Trim each substring into an
     * entry,and returns an list of the entries.
     *
     * @param clazzNames a string consist of a list of the entries joined by a
     *                   delimiter, may be null or empty in which case an empty list is returned.
     * @param separator  a delimiter for the input string.
     * @return a list of class entries.
     */
    public static List<String> splitClassNames(String clazzNames, String separator) {
        List<String> res = new ArrayList<>();
        if (clazzNames == null || clazzNames.isEmpty()) {
            return res;
        }
        for (String clazzName : clazzNames.split(separator)) {
            clazzName = clazzName.trim();
            if (!clazzName.isEmpty()) {
                res.add(clazzName);
            }
        }
        return res;
    }

}
