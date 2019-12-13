package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
public abstract class CommonPattern {
  abstract CommonMatcher matcher(CharSequence t);

  abstract String pattern();

  abstract int flags();

  // Re-declare these as abstract to force subclasses to override.
  @Override
  public abstract String toString();

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object o);
}
