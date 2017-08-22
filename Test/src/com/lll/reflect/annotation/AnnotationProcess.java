package com.lll.reflect.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 17/8/22.
 * Description  APT注解处理器
 * copyright generalray4239@gmail.com
 */
public class AnnotationProcess extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
