package org.apache.commons.crypto;

/**
 * Version 1.0
 * Created by lll on 17/9/1.
 * Description
 * copyright generalray4239@gmail.com
 */
public class OpenSslInfoNative {
    /**
     * Makes the constructor private.
     */
    private OpenSslInfoNative() {
    }

    /**
     * @return version of native
     */
    public static native String NativeVersion();

    /**
     * @return name of native
     */
    public static native String NativeName();

    /**
     * @return timestamp of native
     */
    public static native String NativeTimeStamp();

    /**
     * @return the value of SSLEAY_VERSION_NUMBER.
     */
    public static native long SSLeay();

    /**
     * Returns SSLeay_version according the version type.
     *
     * @param type The version type
     * @return The text variant of the version number and the release date.
     */
    public static native String SSLeayVersion(int type);
}
