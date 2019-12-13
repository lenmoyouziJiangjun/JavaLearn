package org.apache.commons.proxy2.invoker;

import main.org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.proxy2.ProxyUtils;

import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class RecordedInvocation {

  private final Method invokedMethod;

  private final Object[] arguments;

  public RecordedInvocation(Method invokedMethod, Object[] arguments) {
    this.invokedMethod = invokedMethod;
    this.arguments = ArrayUtils.nullToEmpty(ArrayUtils.clone(arguments));
  }

  /**
   * Get the invokedMethod.
   *
   * @return Method
   */
  public Method getInvokedMethod() {
    return invokedMethod;
  }

  //******************************************************************************************************************
  // Canonical Methods
  //******************************************************************************************************************

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(invokedMethod.getDeclaringClass().getName());
    buffer.append("");
    buffer.append(invokedMethod.getName());
    buffer.append("(");
    int count = arguments.length;
    for (int i = 0; i < count; i++) {
      Object arg = arguments[i];
      if (i > 0) {
        buffer.append(", ");
      }
      convert(buffer, arg);
    }
    buffer.append(")");
    return buffer.toString();
  }

  //******************************************************************************************************************
  // Other Methods
  //******************************************************************************************************************

  /**
   * Add a string representation of <code>input</code> to <code>buffer</code>.
   *
   * @param buffer
   * @param input
   */
  protected void convert(StringBuilder buffer, Object input) {
    if (input == null) {
      buffer.append("<null>");
      return;
    }

    // Primitive types, and non-object arrays
    // use toString().
    if (!(input instanceof Object[])) {
      buffer.append(input.toString());
      return;
    }
    buffer.append("(");
    buffer.append(ProxyUtils.getJavaClassName(input.getClass()));
    buffer.append("){");
    Object[] array = (Object[]) input;
    int count = array.length;
    for (int i = 0; i < count; i++) {
      if (i > 0) {
        buffer.append(", ");
      }
      // We use convert() again, because it could be a multi-dimensional array
      // where each element must be converted.
      convert(buffer, array[i]);
    }
    buffer.append("}");
  }

  /**
   * Get the arguments.
   *
   * @return Object[]
   */
  public Object[] getArguments() {
    return ArrayUtils.clone(arguments);
  }

}
