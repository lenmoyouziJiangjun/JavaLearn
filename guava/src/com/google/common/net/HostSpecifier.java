package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.Nullable;

import java.net.InetAddress;
import java.text.ParseException;

/**
 * Version 1.0
 * Created by lll on 2019/3/6.
 * Description
 * copyright generalray4239@gmail.com
 */
@Beta
@GwtIncompatible
public class HostSpecifier {

    private final String canonicalForm;

    private HostSpecifier(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    /**
     * Returns a {@code HostSpecifier} built from the provided {@code specifier}, which is already
     * known to be valid. If the {@code specifier} might be invalid, use {@link #from(String)}
     * instead.
     * <p>
     * <p>The specifier must be in one of these formats:
     * <p>
     * <ul>
     * <li>A domain name, like {@code google.com}
     * <li>A IPv4 address string, like {@code 127.0.0.1}
     * <li>An IPv6 address string with or without brackets, like {@code [2001:db8::1]} or {@code
     * 2001:db8::1}
     * </ul>
     *
     * @throws IllegalArgumentException if the specifier is not valid.
     */
    public static HostSpecifier fromValid(String specifier) {
        // Verify that no port was specified, and strip optional brackets from
        // IPv6 literals.
        final HostAndPort parsedHost = HostAndPort.fromString(specifier);
        Preconditions.checkArgument(!parsedHost.hasPort());
        final String host = parsedHost.getHost();

        // Try to interpret the specifier as an IP address. Note we build
        // the address rather than using the .is* methods because we want to
        // use InetAddresses.toUriString to convert the result to a string in
        // canonical form.
        InetAddress addr = null;
        try {
            addr = InetAddresses.forString(host);
        } catch (IllegalArgumentException e) {
            // It is not an IPv4 or IPv6 literal
        }

        if (addr != null) {
            return new HostSpecifier(InetAddresses.toUriString(addr));
        }

        // It is not any kind of IP address; must be a domain name or invalid.

        // TODO(user): different versions of this for different factories?
        final InternetDomainName domain = InternetDomainName.from(host);

        if (domain.hasPublicSuffix()) {
            return new HostSpecifier(domain.toString());
        }

        throw new IllegalArgumentException(
                "Domain name does not have a recognized public suffix: " + host);
    }

    /**
     * Attempts to return a {@code HostSpecifier} for the given string, throwing an exception if
     * parsing fails. Always use this method in preference to {@link #fromValid(String)} for a
     * specifier that is not already known to be valid.
     *
     * @throws ParseException if the specifier is not valid.
     */
    public static HostSpecifier from(String specifier) throws ParseException {
        try {
            return fromValid(specifier);
        } catch (IllegalArgumentException e) {
            // Since the IAE can originate at several different points inside
            // fromValid(), we implement this method in terms of that one rather
            // than the reverse.

            ParseException parseException = new ParseException("Invalid host specifier: " + specifier, 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    /**
     * Determines whether {@code specifier} represents a valid {@link HostSpecifier} as described in
     * the documentation for {@link #fromValid(String)}.
     */
    public static boolean isValid(String specifier) {
        try {
            fromValid(specifier);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof HostSpecifier) {
            final HostSpecifier that = (HostSpecifier) other;
            return this.canonicalForm.equals(that.canonicalForm);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return canonicalForm.hashCode();
    }

    /**
     * Returns a string representation of the host specifier suitable for inclusion in a URI. If the
     * host specifier is a domain name, the string will be normalized to all lower case. If the
     * specifier was an IPv6 address without brackets, brackets are added so that the result will be
     * usable in the host part of a URI.
     */
    @Override
    public String toString() {
        return canonicalForm;
    }

}
