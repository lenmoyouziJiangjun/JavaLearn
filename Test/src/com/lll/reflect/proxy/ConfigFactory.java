package com.lll.reflect.proxy;/**
 * Created by liaoxueyan on 17/6/15.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * Version 1.0
 * Created by lll on 17/6/15.
 * Description 利用动态代理载入配置文件，并将每一个配置映射成方法，方便我们使用追踪
 *             1、动态代理参考网站：https://www.ibm.com/developerworks/cn/java/j-jtp08305.html
 *
 * copyright generalray4239@gmail.com
 */
public class ConfigFactory {
    private ConfigFactory() {
    }

    public static IConfig create(final InputStream in) throws IOException {
        final Properties properties = new Properties();
        properties.load(in);

        Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IConfig.class}, new PropertyMapper(properties));

        return (IConfig) obj;
    }

    /**
     * 创建一个Handler对象
     */
    public static final class PropertyMapper implements InvocationHandler {

        private final Properties mProperties;

        public PropertyMapper(Properties config) {
            mProperties = config;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final Value value = method.getAnnotation(Value.class);
            if (value == null) {
                return null;
            }
            String property = mProperties.getProperty(value.value());
            if (property == null) {
                return null;
            }
            final Class<?> returnType = method.getReturnType();
            if (returnType.isPrimitive()) {
                if (returnType.equals(int.class)) return (Integer.valueOf(property));
                else if (returnType.equals(long.class)) return (Long.valueOf(property));
                else if (returnType.equals(double.class)) return (Double.valueOf(property));
                else if (returnType.equals(float.class)) return (Float.valueOf(property));
                else if (returnType.equals(boolean.class)) return (Boolean.valueOf(property));
            }
            return property;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        IConfig config = ConfigFactory.create(new FileInputStream("config/config.properties"));
        String dbUrl = config.dbUrl();
        boolean isLoginValidated = config.isValidated();
        int dbPoolSize = config.poolSize();

    }


}
