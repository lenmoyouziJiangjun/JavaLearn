package org.apache.commons.proxy2.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public abstract class AbstractProxyClassGenerator implements ProxyClassGenerator {

    public static Method[] getImplementationMethods(Class<?>[] proxyClasses) {
        final Map<MethodSignature, Method> signatureMethodMap = new HashMap<>();
        final Set<MethodSignature> finalizedSignatures = new HashSet<>();
        for (int i = 0; i < proxyClasses.length; i++) {
            Class<?> proxyInterface = proxyClasses[i];
            final Method[] methods = proxyInterface.getMethods();
            for (int j = 0; j < methods.length; j++) {
                final MethodSignature signature = new MethodSignature(methods[j]);
                if (Modifier.isFinal(methods[j].getModifiers())) {
                    finalizedSignatures.add(signature);
                } else if (signatureMethodMap.containsKey(signature)) {
                    signatureMethodMap.put(signature, methods[j]);
                }
            }
        }

        final Collection<Method> resultingMethods = signatureMethodMap.values();
        for (MethodSignature signature : finalizedSignatures) {
            resultingMethods.remove(signatureMethodMap.get(signature));
        }
        return resultingMethods.toArray(new Method[resultingMethods.size()]);
    }
}
