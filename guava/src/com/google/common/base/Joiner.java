package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * An object which joins pieces of text (specified as an array, {@link Iterable}, varargs or even a
 * {@link Map}) with a separator. It either appends the results to an {@link Appendable} or returns
 * them as a {@link String}. Example: <pre>   {@code
 * <p>
 *   Joiner joiner = Joiner.on("; ").skipNulls();
 *    . . .
 *   return joiner.join("Harry", null, "Ron", "Hermione");}</pre>
 * <p>
 * <p>This returns the string {@code "Harry; Ron; Hermione"}. Note that all input elements are
 * converted to strings using {@link Object#toString()} before being appended.
 * <p>
 * <p>If neither {@link #skipNulls()} nor {@link #useForNull(String)} is specified, the joining
 * methods will throw {@link NullPointerException} if any given element is null.
 * <p>
 * <p><b>Warning: joiner instances are always immutable</b>; a configuration method such as {@code
 * useForNull} has no effect on the instance it is invoked on! You must store and use the new joiner
 * instance returned by the method. This makes joiners thread-safe, and safe to store as {@code
 * static final} constants. <pre>   {@code
 * <p>
 *   // Bad! Do not do this!
 *   Joiner joiner = Joiner.on(',');
 *   joiner.skipNulls(); // does nothing!
 *   return joiner.join("wrong", null, "wrong");}</pre>
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public class Joiner {
  /**
   * Returns a joiner which automatically places {@code separator} between consecutive elements.
   */
  public static Joiner on(String separator) {
    return new Joiner(separator);
  }

  /**
   * Returns a joiner which automatically places {@code separator} between consecutive elements.
   */
  public static Joiner on(char separator) {
    return new Joiner(String.valueOf(separator));
  }

  private final String separator;

  private Joiner(String separator) {
    this.separator = checkNotNull(separator);
  }

  private Joiner(Joiner prototype) {
    this.separator = prototype.separator;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   */
  public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
    return appendTo(appendable, parts.iterator());
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   *
   * @since 11.0
   */
  public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
    checkNotNull(appendable);
    if (parts.hasNext()) {
      appendable.append(toString(parts.next()));
      while (parts.hasNext()) {
        appendable.append(separator);
        appendable.append(toString(parts.next()));
      }
    }
    return appendable;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code appendable}.
   */
  public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
    return appendTo(appendable, Arrays.asList(parts));
  }

  /**
   * Appends to {@code appendable} the string representation of each of the remaining arguments.
   */
  public final <A extends Appendable> A appendTo(
          A appendable, @Nullable Object first, @Nullable Object second, Object... rest)
          throws IOException {
    return appendTo(appendable, iterable(first, second, rest));
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
    return appendTo(builder, parts.iterator());
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   *
   * @since 11.0
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
    try {
      appendTo((Appendable) builder, parts);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    }
    return builder;
  }

  /**
   * Appends the string representation of each of {@code parts}, using the previously configured
   * separator between each, to {@code builder}. Identical to
   * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
   */
  public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
    return appendTo(builder, Arrays.asList(parts));
  }

  /**
   * Appends to {@code builder} the string representation of each of the remaining arguments.
   * Identical to {@link #appendTo(Appendable, Object, Object, Object...)}, except that it does not
   * throw {@link IOException}.
   */
  public final StringBuilder appendTo(
          StringBuilder builder, @Nullable Object first, @Nullable Object second, Object... rest) {
    return appendTo(builder, iterable(first, second, rest));
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   */
  public final String join(Iterable<?> parts) {
    return join(parts.iterator());
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   *
   * @since 11.0
   */
  public final String join(Iterator<?> parts) {
    return appendTo(new StringBuilder(), parts).toString();
  }

  /**
   * Returns a string containing the string representation of each of {@code parts}, using the
   * previously configured separator between each.
   */
  public final String join(Object[] parts) {
    return join(Arrays.asList(parts));
  }

  /**
   * Returns a string containing the string representation of each argument, using the previously
   * configured separator between each.
   */
  public final String join(@Nullable Object first, @Nullable Object second, Object... rest) {
    return join(iterable(first, second, rest));
  }

  /**
   * Returns a joiner with the same behavior as this one, except automatically substituting {@code
   * nullText} for any provided null elements.
   */
  public Joiner useForNull(final String nullText) {
    checkNotNull(nullText);
    return new Joiner(this) {
      @Override
      CharSequence toString(@Nullable Object part) {
        return (part == null) ? nullText : Joiner.this.toString(part);
      }

      @Override
      public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified useForNull");
      }

      @Override
      public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
      }
    };
  }

  /**
   * Returns a joiner with the same behavior as this joiner, except automatically skipping over any
   * provided null elements.
   */
  public Joiner skipNulls() {
    return new Joiner(this) {
      @Override
      public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        checkNotNull(appendable, "appendable");
        checkNotNull(parts, "parts");
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(Joiner.this.toString(part));
            break;
          }
        }
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(separator);
            appendable.append(Joiner.this.toString(part));
          }
        }
        return appendable;
      }

      @Override
      public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified skipNulls");
      }

      @Override
      public MapJoiner withKeyValueSeparator(String kvs) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
      }
    };
  }

  /**
   * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
   * this {@code Joiner} otherwise.
   *
   * @since 20.0
   */
  public MapJoiner withKeyValueSeparator(char keyValueSeparator) {
    return withKeyValueSeparator(String.valueOf(keyValueSeparator));
  }

  /**
   * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
   * this {@code Joiner} otherwise.
   */
  public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
    return new MapJoiner(this, keyValueSeparator);
  }

  /**
   * An object that joins map entries in the same manner as {@code Joiner} joins iterables and
   * arrays. Like {@code Joiner}, it is thread-safe and immutable.
   * <p>
   * <p>In addition to operating on {@code Map} instances, {@code MapJoiner} can operate on {@code
   * Multimap} entries in two distinct modes:
   * <p>
   * <ul>
   * <li>To output a separate entry for each key-value pair, pass {@code multimap.entries()} to a
   * {@code MapJoiner} method that accepts entries as input, and receive output of the form
   * {@code key1=A&key1=B&key2=C}.
   * <li>To output a single entry for each key, pass {@code multimap.asMap()} to a {@code MapJoiner}
   * method that accepts a map as input, and receive output of the form {@code
   * key1=[A, B]&key2=C}.
   * </ul>
   *
   * @since 2.0
   */
  public static final class MapJoiner {
    private final Joiner joiner;
    private final String keyValueSeparator;

    private MapJoiner(Joiner joiner, String keyValueSeparator) {
      this.joiner = joiner; // only "this" is ever passed, so don't checkNotNull
      this.keyValueSeparator = checkNotNull(keyValueSeparator);
    }

    /**
     * Appends the string representation of each entry of {@code map}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     */
    public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
      return appendTo(appendable, map.entrySet());
    }

    /**
     * Appends the string representation of each entry of {@code map}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Map)}, except that it does not throw {@link IOException}.
     */
    public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
      return appendTo(builder, map.entrySet());
    }

    /**
     * Returns a string containing the string representation of each entry of {@code map}, using the
     * previously configured separator and key-value separator.
     */
    public String join(Map<?, ?> map) {
      return join(map.entrySet());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     *
     * @since 10.0
     */
    @Beta
    public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?, ?>> entries)
            throws IOException {
      return appendTo(appendable, entries.iterator());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code appendable}.
     *
     * @since 11.0
     */
    @Beta
    public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?, ?>> parts)
            throws IOException {
      checkNotNull(appendable);
      if (parts.hasNext()) {
        Map.Entry<?, ?> entry = parts.next();
        appendable.append(joiner.toString(entry.getKey()));
        appendable.append(keyValueSeparator);
        appendable.append(joiner.toString(entry.getValue()));
        while (parts.hasNext()) {
          appendable.append(joiner.separator);
          Map.Entry<?, ?> e = parts.next();
          appendable.append(joiner.toString(e.getKey()));
          appendable.append(keyValueSeparator);
          appendable.append(joiner.toString(e.getValue()));
        }
      }
      return appendable;
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
     *
     * @since 10.0
     */
    @Beta
    public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Map.Entry<?, ?>> entries) {
      return appendTo(builder, entries.iterator());
    }

    /**
     * Appends the string representation of each entry in {@code entries}, using the previously
     * configured separator and key-value separator, to {@code builder}. Identical to
     * {@link #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
     *
     * @since 11.0
     */
    @Beta
    public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Map.Entry<?, ?>> entries) {
      try {
        appendTo((Appendable) builder, entries);
      } catch (IOException impossible) {
        throw new AssertionError(impossible);
      }
      return builder;
    }

    /**
     * Returns a string containing the string representation of each entry in {@code entries}, using
     * the previously configured separator and key-value separator.
     *
     * @since 10.0
     */
    @Beta
    public String join(Iterable<? extends Map.Entry<?, ?>> entries) {
      return join(entries.iterator());
    }

    /**
     * Returns a string containing the string representation of each entry in {@code entries}, using
     * the previously configured separator and key-value separator.
     *
     * @since 11.0
     */
    @Beta
    public String join(Iterator<? extends Map.Entry<?, ?>> entries) {
      return appendTo(new StringBuilder(), entries).toString();
    }

    /**
     * Returns a map joiner with the same behavior as this one, except automatically substituting
     * {@code nullText} for any provided null keys or values.
     */
    public MapJoiner useForNull(String nullText) {
      return new MapJoiner(joiner.useForNull(nullText), keyValueSeparator);
    }
  }

  CharSequence toString(Object part) {
    checkNotNull(part); // checkNotNull for GWT (do not optimize).
    return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
  }

  private static Iterable<Object> iterable(
          final Object first, final Object second, final Object[] rest) {
    checkNotNull(rest);
    return new AbstractList<Object>() {
      @Override
      public int size() {
        return rest.length + 2;
      }

      @Override
      public Object get(int index) {
        switch (index) {
          case 0:
            return first;
          case 1:
            return second;
          default:
            return rest[index - 2];
        }
      }
    };
  }
}
