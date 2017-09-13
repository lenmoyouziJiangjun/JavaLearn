package org.apache.commons.proxy2.stub;

import main.org.apache.commons.proxy2.Interceptor;
import org.apache.commons.proxy2.ProxyFactory;
import org.apache.commons.proxy2.interceptor.SwitchInterceptor;

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

    public Interceptor build() {
        return interceptor;
    }

    public <T> StubInterceptorBuilder train(Trainer<T> trainer) {
        final TrainingContext trainingContext = TrainingContext.join(proxyFactory);
        try {
            final T stub = trainingContext.push(trainer.traineeType, interceptor);
            trainer.train(stub);
        } finally {
            trainingContext.part();
        }
        return this;
    }


}
