package main.org.apache.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Version 1.0
 * Created by lll on 17/7/3.
 * Description
 * copyright generalray4239@gmail.com
 */
public class AnnotationUtils {


    /**
     * <p>Generate a string representation of an Annotation, as suggested by
     * {@link Annotation#toString()}.</p>
     *
     * @param a the annotation of which a string representation is desired
     * @return the standard string representation of an annotation, not
     * {@code null}
     */
    public static String toString(final Annotation a) {
        final ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);
        for (final Method m : a.annotationType().getDeclaredMethods()) {
            if (m.getParameterTypes().length > 0) {
                continue; //wtf?
            }
            try {
                builder.append(m.getName(), m.invoke(a));
            } catch (final RuntimeException ex) {
                throw ex;
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return builder.build();
    }


    public static boolean equals(final Annotation a1, final Annotation a2) {
        if (a1 == a2) {
            return true;
        }
        if (a1 == null || a2 == null) {
            return false;
        }
        final Class<? extends Annotation> a1Type = a1.annotationType();
        final Class<? extends Annotation> a2Type = a2.annotationType();
        Validate.notNull(a1Type, "Annotation %s with null annotationType()", a1);
        Validate.notNull(a2Type, "Annotation %s with null annotationType()", a2);
        if (!a1Type.equals(a2Type)) {
            return false;
        }

        try {
            for (final Method method : a1Type.getDeclaredMethods()) {
                if (method.getParameterTypes().length == 0
                        && isValidAnnotationMemberType(method.getReturnType())) {
                    final Object v1 = method.invoke(a1);
                    final Object v2 = method.invoke(a2);
                    if (!memberEquals(method.getReturnType(), v1, v2)) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidAnnotationMemberType(Class<?> type) {
        if (type == null) {
            return false;
        }
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return type.isPrimitive() || type.isEnum() || type.isAnnotation()
                || String.class.equals(type) || Class.class.equals(type);
    }


    /**
     * <p>Generate a hash code for the given annotation using the algorithm
     * presented in the {@link Annotation#hashCode()} API docs.</p>
     *
     * @param annotation the Annotation for a hash code calculation is desired, not
     *                   {@code null}
     * @return the calculated hash code
     * @throws RuntimeException      if an {@code Exception} is encountered during
     *                               annotation member access
     * @throws IllegalStateException if an annotation method invocation returns
     *                               {@code null}
     */
    public static int hashCode(Annotation annotation) {
        int result = 0;
        final Class<? extends Annotation> type = annotation.annotationType();
        for (Method method : type.getDeclaredMethods()) {
            try {
                final Object value = method.invoke(annotation);
                if (value == null) {
                    throw new IllegalStateException(
                            String.format("Annotation method %s returned null", method));
                }
                result += hashMember(method.getName(), value);
            } catch (final RuntimeException ex) {
                throw ex;
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

    /**
     * Helper method for generating a hash code for a member of an annotation.
     *
     * @param name  the name of the member
     * @param value the value of the member
     * @return a hash code for this member
     */
    private static int hashMember(final String name, final Object value) {
        final int part1 = name.hashCode() * 127;
        if (value.getClass().isArray()) {
            return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
        }
        if (value instanceof Annotation) {
            return part1 ^ hashCode((Annotation) value);
        }
        return part1 ^ value.hashCode();
    }

    /**
     * Helper method for checking whether two objects of the given type are
     * equal. This method is used to compare the parameters of two annotation
     * instances.
     *
     * @param type the type of the objects to be compared
     * @param o1   the first object
     * @param o2   the second object
     * @return a flag whether these objects are equal
     */
    private static boolean memberEquals(final Class<?> type, final Object o1, final Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (type.isArray()) {
            return arrayMemberEquals(type.getComponentType(), o1, o2);
        }
        if (type.isAnnotation()) {
            return equals((Annotation) o1, (Annotation) o2);
        }
        return o1.equals(o2);
    }

    /**
     * Helper method for comparing two objects of an array type.
     *
     * @param componentType the component type of the array
     * @param o1            the first object
     * @param o2            the second object
     * @return a flag whether these objects are equal
     */
    private static boolean arrayMemberEquals(final Class<?> componentType, final Object o1, final Object o2) {
        if (componentType.isAnnotation()) {
            return annotationArrayMemberEquals((Annotation[]) o1, (Annotation[]) o2);
        }
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        return Arrays.equals((Object[]) o1, (Object[]) o2);
    }

    /**
     * Helper method for comparing two arrays of annotations.
     *
     * @param a1 the first array
     * @param a2 the second array
     * @return a flag whether these arrays are equal
     */
    private static boolean annotationArrayMemberEquals(final Annotation[] a1, final Annotation[] a2) {
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (!equals(a1[i], a2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method for generating a hash code for an array.
     *
     * @param componentType the component type of the array
     * @param o             the array
     * @return a hash code for the specified array
     */
    private static int arrayMemberHash(final Class<?> componentType, final Object o) {
        if (componentType.equals(Byte.TYPE)) {
            return Arrays.hashCode((byte[]) o);
        }
        if (componentType.equals(Short.TYPE)) {
            return Arrays.hashCode((short[]) o);
        }
        if (componentType.equals(Integer.TYPE)) {
            return Arrays.hashCode((int[]) o);
        }
        if (componentType.equals(Character.TYPE)) {
            return Arrays.hashCode((char[]) o);
        }
        if (componentType.equals(Long.TYPE)) {
            return Arrays.hashCode((long[]) o);
        }
        if (componentType.equals(Float.TYPE)) {
            return Arrays.hashCode((float[]) o);
        }
        if (componentType.equals(Double.TYPE)) {
            return Arrays.hashCode((double[]) o);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return Arrays.hashCode((boolean[]) o);
        }
        return Arrays.hashCode((Object[]) o);
    }
}
