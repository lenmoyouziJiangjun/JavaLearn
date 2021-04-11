package algorithms.leetcode;

/**
 * Version 1.0
 * Created by lll on 2020-04-20.
 * Description
 * <pre>
 *     给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 *     数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 *     判断你是否能够到达最后一个位置
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode55 {


    /**
     * 示例
     * [2,3,1,1,4]
     * return true
     * <p>
     * <分析>
     * 记录每一步可以跳转到的最大位置，如果最大位置>=length,表示可以到达
     * </分析>
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        //记录数组中每个元素最远可到到位置
        int lastMax = 0;
        int len = nums.length - 1;
        for (int i = 0; i < len; i++) {
            System.out.println("the lastmax == " + lastMax + ",i====" + i);
            if (i <= lastMax) {//代表上一次数据可以跳转到当前i位置
                if (i == len) {//到达了位置
                    return true;
                } else {
                    int num = i + nums[i];
                    if (num >= len) { //可以到达
                        return true;
                    } else {
                        lastMax = Math.max(lastMax, num);
                    }
                }
            } else {
                System.out.println("the lastmax == " + lastMax + ",i====" + i);
                return false;
            }
        }
        return false;
    }


    public boolean canArrive(int currentIndex, int lastIndex, int[] nums) {

        System.out.println("the currentIndex == " + currentIndex + ", lastIndex===" + lastIndex + ", current value===" + nums[currentIndex]);


        if (currentIndex == lastIndex) {
            return true;
        }

        int num = nums[currentIndex];

        if (currentIndex + num > lastIndex) {
            return true;
        }

        for (int i = 1; i <= num; i++) {
            boolean result = canArrive(currentIndex + i, lastIndex, nums);
            if (result) {
                return result;
            }
        }
        return false;
    }

    public static void main(String args[]) {
//        int testArray[] = new int[]{2, 3, 1, 1, 4};
//        int testArray[] = new int[]{2, 3, 1, 1, 4};
        int testArray[] = new int[]{0};

        LeetCode55 code55 = new LeetCode55();
        boolean result = code55.canJump(testArray);
        System.out.println("the result == " + result);

    }
}
