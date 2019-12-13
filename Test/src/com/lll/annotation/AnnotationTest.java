package com.lll.annotation;

import java.lang.reflect.Method;

/**
 * Version 1.0
 * Created by lll on 2019-12-07.
 * Description
 * copyright generalray4239@gmail.com
 */
public class AnnotationTest {

  @SourceAnnotation("张三")
  public String name;

  @ClassAnnotation(description = "打印名字")
  public String printName() {
     return name;
  }

  @RuntimeAnnotation(description = "设置名字")
  public void setName(String name) {
    this.name = name;
  }


  public static void main(String[] args) {
    try {
      AnnotationTest test = new AnnotationTest();
      Method method = test.getClass().getMethod("printName");
      ClassAnnotation classAnnotation = method.getAnnotation(ClassAnnotation.class);
      if(classAnnotation == null){
        System.out.println("没有获取到ClassAnnotation");
      }

      Method runtimeMethod = test.getClass().getMethod("setName", new Class[]{String.class});
      RuntimeAnnotation runtimeAnnotation = runtimeMethod.getAnnotation(RuntimeAnnotation.class);
      if(runtimeAnnotation == null){
        System.out.println("没有获取到runtimeAnnotation");
      } else {
        System.out.println("找到了runtimeAnnotation");
      }
    } catch ( Exception e) {
      e.printStackTrace();
    }

  }

}
