package org.apache.commons.proxy2.provider;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import main.org.apache.commons.lang3.exception.CloneFailedException;
import main.org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.proxy2.ObjectProvider;
import org.apache.commons.proxy2.exception.ObjectProviderException;

import java.io.Serializable;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class CloningProvider<T extends Cloneable> implements ObjectProvider<T>, Serializable {

    private final T cloneable;

    public CloningProvider(T cloneable) {
        Validate.notNull(cloneable, "Cloneable object cannot be null.");
        Validate.isTrue(MethodUtils.getAccessibleMethod(cloneable.getClass(), "clone") != null,
                String.format("Class %s does not override clone() method as public.", cloneable.getClass().getName()));
        this.cloneable = cloneable;
    }

    @Override
    public T getObject() {
        try
        {
            return ObjectUtils.clone(cloneable);
        }
        catch (CloneFailedException e) {
            throw new ObjectProviderException(e.getMessage(), e.getCause());
        }
    }
}
