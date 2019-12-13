package org.apache.commons.lang3;/**
 * Created by liaoxueyan on 17/6/29.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StringUtils {

  /**
   * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
   * <p>
   * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   * <p>
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is null, empty or whitespace only
   * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
   */

  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (Character.isWhitespace(cs.charAt(i)) == false) {
        return false;
      }
    }
    return true;
  }
}
