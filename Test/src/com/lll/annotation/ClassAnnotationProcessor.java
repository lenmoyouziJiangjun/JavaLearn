package com.lll.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 25/01/2018.
 * Description
 * <pre>
 *     编译时注解处理
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ClassAnnotationProcessor extends AbstractProcessor {

  /**
   * @param processingEnv 解析环境
   */
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    processingEnv.getElementUtils();//获取Elements工具类

  }

  /**
   * 获取指定的Java版本
   *
   * @return
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
//        return super.getSupportedSourceVersion();
  }

  /**
   * 获取支持的注解
   *
   * @return
   */
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return super.getSupportedAnnotationTypes();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    //获取某个注解修饰的元素
//        roundEnv.getElementsAnnotatedWith("");

    return false;
  }
}
