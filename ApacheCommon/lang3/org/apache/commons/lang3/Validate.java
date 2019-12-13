package org.apache.commons.lang3;/**
 * Created by liaoxueyan on 17/6/29.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Validate {

  private static final String DEFAULT_NOT_NAN_EX_MESSAGE =
          "The validated value is not a number";
  private static final String DEFAULT_FINITE_EX_MESSAGE =
          "The value is invalid: %f";
  private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE =
          "The value %s is not in the specified exclusive range of %s to %s";
  private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE =
          "The value %s is not in the specified inclusive range of %s to %s";
  private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
  private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
  private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
  private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE =
          "The validated array contains null element at index: %d";
  private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE =
          "The validated collection contains null element at index: %d";
  private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
  private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
  private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE =
          "The validated character sequence is empty";
  private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
  private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
  private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
  private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE =
          "The validated character sequence index is invalid: %d";
  private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE =
          "The validated collection index is invalid: %d";
  private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
  private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
  private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";

  /**
   * <p>Validate that the specified argument character sequence is
   * neither {@code null}, a length of zero (no characters), empty
   * nor whitespace; otherwise throwing an exception with the specified
   * message.
   * <p>
   * <pre>Validate.notBlank(myString, "The string must not be blank");</pre>
   *
   * @param <T>     the character sequence type
   * @param chars   the character sequence to check, validated not null by this method
   * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
   * @param values  the optional values for the formatted exception message, null array not recommended
   * @return the validated character sequence (never {@code null} method for chaining)
   * @throws NullPointerException     if the character sequence is {@code null}
   * @throws IllegalArgumentException if the character sequence is blank
   * @since 3.0
   */
  public static <T extends CharSequence> T notBlank(final T chars, final String message, final Object... values) {
    if (chars == null) {
      throw new NullPointerException(String.format(message, values));
    }
    if (StringUtils.isBlank(chars)) {
      throw new IllegalArgumentException(String.format(message, values));
    }
    return chars;
  }

  /**
   * <p>Validate that the argument condition is {@code true}; otherwise
   * throwing an exception with the specified message. This method is useful when
   * validating according to an arbitrary boolean expression, such as validating a
   * primitive number or using your own custom validation expression.</p>
   * <p>
   * <pre>Validate.isTrue(i &gt; 0.0, "The value must be greater than zero: &#37;d", i);</pre>
   *
   * <p>For performance reasons, the long value is passed as a separate parameter and
   * appended to the exception message only in the case of an error.</p>
   *
   * @param expression the boolean expression to check
   * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
   * @param value      the value to append to the message when invalid
   * @throws IllegalArgumentException if expression is {@code false}
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, String, double)
   * @see #isTrue(boolean, String, Object...)
   */
  public static void isTrue(final boolean expression, final String message, final long value) {
    if (expression == false) {
      throw new IllegalArgumentException(String.format(message, Long.valueOf(value)));
    }
  }

  /**
   * <p>Validate that the argument condition is {@code true}; otherwise
   * throwing an exception with the specified message. This method is useful when
   * validating according to an arbitrary boolean expression, such as validating a
   * primitive number or using your own custom validation expression.</p>
   * <p>
   * <pre>Validate.isTrue(d &gt; 0.0, "The value must be greater than zero: &#37;s", d);</pre>
   *
   * <p>For performance reasons, the double value is passed as a separate parameter and
   * appended to the exception message only in the case of an error.</p>
   *
   * @param expression the boolean expression to check
   * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
   * @param value      the value to append to the message when invalid
   * @throws IllegalArgumentException if expression is {@code false}
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, String, long)
   * @see #isTrue(boolean, String, Object...)
   */
  public static void isTrue(final boolean expression, final String message, final double value) {
    if (expression == false) {
      throw new IllegalArgumentException(String.format(message, Double.valueOf(value)));
    }
  }

  /**
   * <p>Validate that the argument condition is {@code true}; otherwise
   * throwing an exception with the specified message. This method is useful when
   * validating according to an arbitrary boolean expression, such as validating a
   * primitive number or using your own custom validation expression.</p>
   * <p>
   * <pre>
   * Validate.isTrue(i &gt;= min &amp;&amp; i &lt;= max, "The value must be between &#37;d and &#37;d", min, max);
   * Validate.isTrue(myObject.isOk(), "The object is not okay");</pre>
   *
   * @param expression the boolean expression to check
   * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
   * @param values     the optional values for the formatted exception message, null array not recommended
   * @throws IllegalArgumentException if expression is {@code false}
   * @see #isTrue(boolean)
   * @see #isTrue(boolean, String, long)
   * @see #isTrue(boolean, String, double)
   */
  public static void isTrue(final boolean expression, final String message, final Object... values) {
    if (expression == false) {
      throw new IllegalArgumentException(String.format(message, values));
    }
  }

  /**
   * <p>Validate that the argument condition is {@code true}; otherwise
   * throwing an exception. This method is useful when validating according
   * to an arbitrary boolean expression, such as validating a
   * primitive number or using your own custom validation expression.</p>
   * <p>
   * <pre>
   * Validate.isTrue(i &gt; 0);
   * Validate.isTrue(myObject.isOk());</pre>
   * <p>
   * <p>The message of the exception is &quot;The validated expression is
   * false&quot;.</p>
   *
   * @param expression the boolean expression to check
   * @throws IllegalArgumentException if expression is {@code false}
   * @see #isTrue(boolean, String, long)
   * @see #isTrue(boolean, String, double)
   * @see #isTrue(boolean, String, Object...)
   */
  public static void isTrue(final boolean expression) {
    if (expression == false) {
      throw new IllegalArgumentException(DEFAULT_IS_TRUE_EX_MESSAGE);
    }
  }


  /**
   * <p>Validate that the specified argument is not {@code null};
   * otherwise throwing an exception.
   * <p>
   * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
   *
   * <p>The message of the exception is &quot;The validated object is
   * null&quot;.</p>
   *
   * @param <T>    the object type
   * @param object the object to check
   * @return the validated object (never {@code null} for method chaining)
   * @throws NullPointerException if the object is {@code null}
   * @see #notNull(Object, String, Object...)
   */
  public static <T> T notNull(final T object) {
    return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
  }

  /**
   * <p>Validate that the specified argument is not {@code null};
   * otherwise throwing an exception with the specified message.
   * <p>
   * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
   *
   * @param <T>     the object type
   * @param object  the object to check
   * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
   * @param values  the optional values for the formatted exception message
   * @return the validated object (never {@code null} for method chaining)
   * @throws NullPointerException if the object is {@code null}
   * @see #notNull(Object)
   */
  public static <T> T notNull(final T object, final String message, final Object... values) {
    if (object == null) {
      throw new NullPointerException(String.format(message, values));
    }
    return object;
  }
}
