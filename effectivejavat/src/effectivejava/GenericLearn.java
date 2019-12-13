package effectivejava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Version 1.0
 * Created by lll on 2019/2/27.
 * Description
 * copyright generalray4239@gmail.com
 */
public class GenericLearn {


  public void test() {
    Set<String> sets = new HashSet<>();
  }

  /**
   * 使用泛型的时候，列表优先于数组
   */
  public void test2() {
    //1、数组代码在编译的时候通过，运行报错
    // 因为数组是具体化的，在运行时才知道并检查他们的元素类型约束
//        Object[] objs = new Long[100];
//        objs[0] = "12342hgffjha";

    //2、列表在编译的时候就直接报错
//        List<Object> objList = new ArrayList<Long>();
//        objList.add("hahhahahaha");
  }
}
