package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2018/10/30.
 * Description
 * copyright generalray4239@gmail.com
 */
public class InsertNum {

  public static List<Integer> numbers = new ArrayList<>();


  public static List<Integer> insertNum() {
    int index = 0;
    List<Integer> nums = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      if (i < numbers.get(index)) {
        nums.add(0);
      } else if (i == numbers.get(index)) {
        System.out.println("执行到了具体某一个数据了....." + i);
        nums.add(numbers.get(index));
        index++;
      } else if (i > numbers.get(index)) {
        nums.add(0);
      }
    }
    return nums;
  }


  public static List<Integer> insertNum2() {
    int startNum = 0;
    List<Integer> nums = new ArrayList<>();
    for (Integer num : numbers) {
      if ((num - startNum) > 1) {
        for (int j = startNum; j < num; j++) {
          nums.add(0);
        }
      } else if ((num - startNum) == 1) {
        nums.add(num);
      }
    }
    return nums;
  }

  public static void main(String args[]) {
    numbers.add(3);
    numbers.add(5);
    numbers.add(6);
    numbers.add(11);
    numbers = insertNum();
    for (int i = 0; i < numbers.size(); i++) {
      System.out.print("  " + numbers.get(i));
    }
  }

}
