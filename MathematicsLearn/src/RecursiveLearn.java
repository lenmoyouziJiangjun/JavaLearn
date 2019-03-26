import java.util.ArrayList;

/**
 * Version 1.0
 * Created by lll on 2019/2/15.
 * Description 递归复习
 * <p>
 *     1、递归的实际就是：N个for循环嵌套。
 *     2、既然递归的函数值返回过程和基于循环的迭代法一致，我们直接用迭代法不就好了，为什么要用递归的数学思想呢？
          1、递归更容易实现，
 *     2、递归的实现：在特定条件的时候return，递归到特定条件
 * <p>
 * copyright generalray4239@gmail.com
 */
public class RecursiveLearn {


    /**
     *递归的使用场景
     *  1、在限定总和的情况下，求所有可能的加和方式
     *  2、递归实现数学归纳法
     *
     */

    /**
     * *
     */

    /**
     * 四种纸币，
     */
    public static int[] rewards = new int[]{1, 2, 5, 10};

    public static int num;

    public static void get(int totalReward, ArrayList result) {
        if (totalReward == 0) {//余数为0了，表示完成
            System.out.println(result);
            num+=1;
            return;
        } else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList newResult = (ArrayList) result.clone();
                newResult.add(rewards[i]);
                get(totalReward-rewards[i],newResult);
            }
        }
    }

    public static void testMoney(){
        get(10,new ArrayList());
        System.out.println("共有"+num+"种组合");
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        testMoney();
    }
}
