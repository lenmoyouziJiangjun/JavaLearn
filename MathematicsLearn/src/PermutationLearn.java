import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Version 1.0
 * Created by lll on 2019/3/8.
 * Description
 * <pre>
 *     一、排列
 *        排列的定义：从n个不同元素中，任取m(m≤n,m与n均为自然数,下同）个元素按照一定的顺序排成一列，叫做从n个不同元素中取出m个元素的一个排列；
 *        从n个不同元素中取出m(m≤n）个元素的所有排列的个数，叫做从n个不同元素中取出m个元素的排列数，用符号 A(n,m）
 *        A(n,m) = N!/(N-M)! 此外规定0!=1(n!表示n(n-1)(n-2)...1,也就是6！=6x5x4x3x2x1
 *
 *     二、组合C(n,m)；从n个不同数中取出m个数组成一组.没有顺序,主要元素出现在里面就可以
 *        结果等于：（N!/(N-M)!）/M!
 *        C(N,N) =1 ；
 *        全组合：对于所有的M取值的组合就是全组合：2^N
 *
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class PermutationLearn {


    // 设置齐王的马跑完所需时间
    public static HashMap<String, Double> q_horses_time = new HashMap<String, Double>() {
        {
            put("q1", 1.0);
            put("q2", 2.0);
            put("q3", 3.0);
        }
    };

    // 设置田忌的马跑完所需时间
    public static HashMap<String, Double> t_horses_time = new HashMap<String, Double>() {
        {
            put("t1", 1.5);
            put("t2", 2.5);
            put("t3", 3.5);
        }
    };

    public static ArrayList<String> q_horses = new ArrayList<String>(Arrays.asList("q1", "q2", "q3"));

    /**
     * @param horses- 目前还剩多少马没有出战，result- 保存当前已经出战的马匹及顺序
     * @return void
     * @Description: 使用函数的递归（嵌套）调用，找出所有可能的马匹出战顺序
     */

    public static void permutate(ArrayList<String> horses, ArrayList<String> result) {

        // 所有马匹都已经出战，判断哪方获胜，输出结果
        if (horses.size() == 0) {
            System.out.println(result);
            compare(result, q_horses);
            System.out.println();
            return;
        }

        for (int i = 0; i < horses.size(); i++) {
            // 从剩下的未出战马匹中，选择一匹，加入结果
            ArrayList<String> new_result = (ArrayList<String>) (result.clone());
            new_result.add(horses.get(i));

            // 将已选择的马匹从未出战的列表中移出
            ArrayList<String> rest_horses = ((ArrayList<String>) horses.clone());
            rest_horses.remove(i);

            // 递归调用，对于剩余的马匹继续生成排列
            permutate(rest_horses, new_result);
        }
    }

    public static void compare(ArrayList<String> t, ArrayList<String> q) {
        int t_won_cnt = 0;
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t_horses_time.get(t.get(i)) + " " + q_horses_time.get(q.get(i)));
            if (t_horses_time.get(t.get(i)) < q_horses_time.get(q.get(i))) {
                t_won_cnt++;
            }
        }

        if (t_won_cnt > (t.size() / 2)) {
            System.out.println(" 田忌获胜！");
        } else {
            System.out.println(" 齐王获胜！");
        }
        System.out.println();
    }

    /**
     *
     */
    public static int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * 打印nums的排列
     */
    public static void printPermutation(int[] remainArray, int[] result, int resultSize) {
        if (resultSize == 0) {
            printArrays(result);
        } else {
            for (int i = 0; i < remainArray.length; i++) {
                int[] resultCopy = new int[10];
                System.arraycopy(result, 0, resultCopy, 0, result.length);
                resultCopy[10 - resultSize] = remainArray[i];

                printPermutation(resultCopy, resultCopy, resultSize--);
            }
        }
    }

    private static void printArrays(int[] array) {
        for (Integer i : array) {
            System.out.print(i);
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * 使用函数的嵌套递归，找出所有可能的队伍组合
     *
     * @param teams  目前还剩多少队伍没有参与组合，
     * @param result 保存当前已经组合的队伍
     * @param m
     */
    private static void combine(ArrayList<String> teams, ArrayList<String> result, int m) {
        if (result.size() == m) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < teams.size(); i++) {
            //从剩下的队伍中，选择一队，加入结果
            ArrayList<String> newResult = (ArrayList<String>) result.clone();
            newResult.add(teams.get(i));

            //只考虑当前选择之后的所有队伍，
            ArrayList<String> remain_teams = new ArrayList<>(teams.subList(i + 1, teams.size()));
            combine(remain_teams, newResult, m);
        }
    }




    public static void main(String[] args) {

        ArrayList<String> horses = new ArrayList<String>(Arrays.asList("t1", "t2", "t3"));
        //输出排列：
        PermutationLearn.permutate(horses,new ArrayList<>());
        //输出组合：
        PermutationLearn.combine(horses, new ArrayList<String>(),3);
    }
}
