package exception;

/**
 * Version 1.0
 * Created by lll on 2019/3/5.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ExceptionLearn {


  /**
   * 一、try catch 只捕捉有必要的代码段，尽量不要一个大的try包住整段代码。
   * 同时利用异常控制代码流程，也不是一个好主意，远比if else要低效
   * 1、try catch 代码段会产生额外的性能开销，它往往会影响JVM对代码进行优化，
   */
  public void testException() {
    try {//1、不要try 整段代码，只捕捉必要代码段
      //对于error 或者throwable

    } catch (Exception e) { // 2、不要抛出笼统的异常，最好是捕获特定异常。这样对于读代码的人来时，非常清晰。同时对于一些
      e.printStackTrace();// 3、printStackTrace 是一个标准输出，最好是输出到自己的日志系统。
      // 4、一定不要生吞异常，就是try起来什么都不做。最低要求都是打印出异常
    }
  }


}
