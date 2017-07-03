package main.org.apache.commons.proxy2.impl;


import com.sun.tools.javac.util.Pair;
import main.org.apache.commons.lang3.ArrayUtils;
import main.org.apache.commons.lang3.StringUtils;
import main.org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.ParsePosition;
import java.util.*;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class MethodSignature implements Serializable {

    private static final Map<Class<?>, Character> PRIMITIVE_ABBREVIATIONS;

    private static final Map<Character, Class<?>> REVERSE_ABBREVIATIONS;

    static {
        final Map<Class<?>, Character> primitiveAbbreviations = new HashMap<>();
        primitiveAbbreviations.put(Boolean.TYPE, Character.valueOf('Z'));
        primitiveAbbreviations.put(Byte.TYPE, Character.valueOf('B'));
        primitiveAbbreviations.put(Short.TYPE, Character.valueOf('S'));
        primitiveAbbreviations.put(Integer.TYPE, Character.valueOf('I'));
        primitiveAbbreviations.put(Character.TYPE, Character.valueOf('C'));
        primitiveAbbreviations.put(Long.TYPE, Character.valueOf('J'));
        primitiveAbbreviations.put(Float.TYPE, Character.valueOf('F'));
        primitiveAbbreviations.put(Double.TYPE, Character.valueOf('D'));
        primitiveAbbreviations.put(Void.TYPE, Character.valueOf('V'));
        final Map<Character, Class<?>> reverseAbbreviations = new HashMap<Character, Class<?>>();
        for (Map.Entry<Class<?>, Character> e : primitiveAbbreviations.entrySet()) {
            reverseAbbreviations.put(e.getValue(), e.getKey());
        }
        PRIMITIVE_ABBREVIATIONS = Collections.unmodifiableMap(primitiveAbbreviations);
        REVERSE_ABBREVIATIONS = Collections.unmodifiableMap(reverseAbbreviations);
    }

    private static void appendTo(StringBuilder sb, Class<?> type) {
        if (type.isPrimitive()) {
            sb.append(PRIMITIVE_ABBREVIATIONS.get(type));
        } else if (type.isArray()) {
            sb.append("[");
            appendTo(sb, type.getComponentType());
        } else {
            sb.append('L').append(type.getName().replace('.', '/')).append(';');
        }
    }

    private static class SignaturePosition extends ParsePosition {

        /**
         * Create a new ParsePosition with the given initial index.
         */
        public SignaturePosition() {
            super(0);
        }

        SignaturePosition next() {
            return plus(1);
        }

        SignaturePosition plus(int addend) {
            setIndex(getIndex() + addend);
            return this;
        }
    }

    private static Pair<String, Class<?>[]> parse(String internal) {
        Validate.notBlank(internal, "Cannot parse blank method signature");
        final SignaturePosition pos = new SignaturePosition();
        int lparen = internal.indexOf("(",pos.getIndex());
        Validate.isTrue(lparen > 0, "Method signature \"%s\" requires parentheses", internal);
        final String name = internal.substring(0, lparen).trim();
        Validate.notBlank(name, "Method signature \"%s\" has blank name", internal);

        pos.setIndex(lparen + 1);

        boolean complete = false;
        final List<Class<?>> params = new ArrayList<Class<?>>();
        while (pos.getIndex() < internal.length())
        {
            final char c = internal.charAt(pos.getIndex());
            if (Character.isWhitespace(c))
            {
                pos.next();
                continue;
            }
            final Character k = Character.valueOf(c);
            if (REVERSE_ABBREVIATIONS.containsKey(k))
            {
                params.add(REVERSE_ABBREVIATIONS.get(k));
                pos.next();
                continue;
            }
            if (')' == c)
            {
                complete = true;
                pos.next();
                break;
            }
            try
            {
                params.add(parseType(internal, pos));
            }
            catch (ClassNotFoundException e)
            {
                throw new IllegalArgumentException(String.format("Method signature \"%s\" references unknown type",
                        internal), e);
            }
        }
        Validate.isTrue(complete, "Method signature \"%s\" is incomplete", internal);
        Validate.isTrue(StringUtils.isBlank(internal.substring(pos.getIndex())),
                "Method signature \"%s\" includes unrecognized content beyond end", internal);

        return Pair.of(name, params.toArray(ArrayUtils.EMPTY_CLASS_ARRAY));
    }

    private static Class<?> parseType(String internal, SignaturePosition pos) throws ClassNotFoundException
    {
        final int here = pos.getIndex();
        final char c = internal.charAt(here);

        switch (c)
        {
            case '[':
                pos.next();
                final Class<?> componentType = parseType(internal, pos);
                return Array.newInstance(componentType, 0).getClass();
            case 'L':
                pos.next();
                final int type = pos.getIndex();
                final int semi = internal.indexOf(';', type);
                Validate.isTrue(semi > 0, "Type at index %d of method signature \"%s\" not terminated by semicolon",
                        Integer.valueOf(here), internal);
                final String className = internal.substring(type, semi).replace('/', '.');
                Validate.notBlank(className, "Invalid classname at position %d of method signature \"%s\"",
                        Integer.valueOf(type), internal);
                pos.setIndex(semi + 1);
                return Class.forName(className);
            default:
                throw new IllegalArgumentException(String.format(
                        "Unexpected character at index %d of method signature \"%s\"",
                        Integer.valueOf(here), internal));
        }
    }

    //******************************************************************************************************************
    // Fields
    //******************************************************************************************************************

    /**
     * Stored as a Java method descriptor minus return type.
     */
    private final String internal;

    //******************************************************************************************************************
    // Constructors
    //******************************************************************************************************************

    /**
     * Create a new MethodSignature instance.
     *
     * @param method
     */
    public MethodSignature(Method method)
    {
        final StringBuilder buf = new StringBuilder(method.getName()).append('(');
        for (Class<?> p : method.getParameterTypes())
        {
            appendTo(buf, p);
        }
        buf.append(')');
        this.internal = buf.toString();
    }

    //******************************************************************************************************************
    // Methods
    //******************************************************************************************************************

    /**
     * Get the corresponding {@link Method} instance from the specified {@link Class}.
     *
     * @param type
     * @return Method
     */
    public Method toMethod(Class<?> type)
    {
        final Pair<String, Class<?>[]> info = parse(internal);
        return MethodUtils.getAccessibleMethod(type, info.getLeft(), info.getRight());
    }

    //******************************************************************************************************************
    // Canonical Methods
    //******************************************************************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (o == this)
        {
            return true;
        }
        if (o.getClass() != getClass())
        {
            return false;
        }
        MethodSignature other = (MethodSignature) o;
        return other.internal.equals(internal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(internal).build().intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return internal;
    }

}
