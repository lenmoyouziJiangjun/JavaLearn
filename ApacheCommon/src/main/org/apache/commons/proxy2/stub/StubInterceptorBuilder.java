package main.org.apache.commons.proxy2.stub;

import main.org.apache.commons.proxy2.Interceptor;
import main.org.apache.commons.proxy2.ProxyFactory;
import main.org.apache.commons.proxy2.interceptor.SwitchInterceptor;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class StubInterceptorBuilder {

    private final ProxyFactory proxyFactory;
    private final SwitchInterceptor interceptor = new SwitchInterceptor();

    public StubInterceptorBuilder(ProxyFactory factory) {
        this.proxyFactory = factory;
    }

    public Interceptor build(){
        return interceptor;
    }

    public <T> StubInterceptorBuilder train(Trainer<T> trainer)




}
