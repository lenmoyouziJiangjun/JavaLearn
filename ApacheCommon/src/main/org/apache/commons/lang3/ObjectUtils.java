package main.org.apache.commons.lang3;

import main.org.apache.commons.lang3.exception.CloneFailedException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ObjectUtils {

    /**
     * <p>Clone an object.</p>
     *
     * @param <T> the type of the object
     * @param obj the object to clone, null returns null
     * @return the clone if the object implements {@link Cloneable} otherwise {@code null}
     * @throws CloneFailedException if the object is cloneable and the clone operation fails
     */
    public static <T> T clone(final T obj) {

        if (obj instanceof Cloneable) {//the obj can clone;
            final Object result;
            if (obj.getClass().isArray()) {
                final Class<?> componentType = obj.getClass().getComponentType();
                if (!componentType.isPrimitive()) {
                    result = ((Object[]) obj).clone();
                } else {
                    int length = Array.getLength(obj);
                    result = Array.newInstance(componentType, length);
                    while (length-- > 0) {
                        Array.set(result, length, Array.get(obj, length));
                    }
                }
            } else {
                try {
                    final Method cloneMethod = obj.getClass().getMethod("clone");
                    result = cloneMethod.invoke(obj);
                } catch (final NoSuchMethodException e) {
                    throw new CloneFailedException("Cloneable type "
                            + obj.getClass().getName()
                            + " has no clone method", e);
                } catch (final IllegalAccessException e) {
                    throw new CloneFailedException("Cannot clone Cloneable type "
                            + obj.getClass().getName(), e);
                } catch (final InvocationTargetException e) {
                    throw new CloneFailedException("Exception cloning Cloneable type "
                            + obj.getClass().getName(), e.getCause());
                }
            }
            final T checked = (T) result;
            return checked;
        }
        return null;
    }
}
