package com.lll.reflect.apt;

import com.lll.reflect.annotation.fruit.FruitName;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 17/8/22.
 * Description 编译时注解(APT)测试
 * 只能在Java7里面测试，J8已经不支持APT了
 * copyright generalray4239@gmail.com
 */
public class AptProcesser extends AbstractProcessor {


  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    try {
      Set<? extends Element> anns = roundEnv.getElementsAnnotatedWith(FruitName.class);
      while (anns.iterator().hasNext()) {


        anns.iterator().next();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
