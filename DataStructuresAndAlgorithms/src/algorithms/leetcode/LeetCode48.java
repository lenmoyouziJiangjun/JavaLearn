package algorithms.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static algorithms.leetcode.LeetCodeTest.printList;

/**
 * Version 1.0
 * Created by lll on 2020-03-31.
 * Description
 *
 *
 * <pre>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class LeetCode48 {


    /**
     * <pre>
     *     analysis：
     *
     * </pre>
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length < 1) {
            return null;
        }

        List<List<String>> result = new ArrayList<>();

        if (strs.length == 1) {
            List<String> item = new ArrayList<>();
            item.add(strs[0]);
            result.add(item);

        } else {
            int len = strs.length;
            List<Integer> skipItem = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (!skipItem.contains(i)) {
                    String item = strs[i];
                    List<String> allString = new ArrayList<>();
                    allString.add(item);
                    getAllString(item, item.length(), 0, allString);

                    System.out.println("the i === " + i + " item===" + item + "  all size==" + allString.size());

                    List<String> itemResult = new ArrayList<>();
                    for (int j = i; j < len; j++) {
                        if (allString.contains(strs[j])) {
                            itemResult.add(strs[j]);
                            skipItem.add(j);
                        }
                    }
                    result.add(itemResult);
                }
            }
        }

        printList(result);

        return result;
    }

    /**
     * generator   Permutation by the param args
     * 生成args的全排列
     *
     * @param args
     * @return
     */
    public static void getAllString(String args, int len, int index, List<String> itemResult) {
        if (index == len) {
            return;
        }
        for (int i = index; i < len; i++) {
            String newStr = String.copyValueOf(args.toCharArray());
            char[] newCharArray = newStr.toCharArray();
            if (newCharArray[i] != newCharArray[index]) {
                char temp = newCharArray[i];
                newCharArray[i] = newCharArray[index];
                newCharArray[index] = temp;
                newStr = new String(newCharArray); //新生成了一个字符串
                System.out.println("the new str   " + newStr + "  index ==" + index);
                if (!itemResult.contains(newStr)) { //去除重复
                    itemResult.add(newStr);
                }
            }
            getAllString(newStr, len, index + 1, itemResult); //新字符串继续交换
        }
    }


    /**
     * 实现方案二，对字符串进行排序，排序后在比较
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length < 1) {
            return null;
        }

        List<List<String>> result = new ArrayList<>();

        if (strs.length == 1) {
            List<String> item = new ArrayList<>();
            item.add(strs[0]);
            result.add(item);

        } else {
            int len = strs.length;
            List<Integer> skipItem = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (!skipItem.contains(i)) {
                    String item = strs[i];
                    List<String> itemResult = new ArrayList<>();
                    for (int j = i; j < len; j++) {
                        if (compareTwoString(item, strs[j])) {
                            itemResult.add(strs[j]);
                            skipItem.add(j);
                        }
                    }
                    result.add(itemResult);
                }
            }
        }

        printList(result);

        return result;

    }

    /**
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareTwoString(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] char1 = str1.toCharArray();
        Arrays.sort(char1);//对字符串按照字典顺序排序

        char[] char2 = str2.toCharArray();
        Arrays.sort(char2);

        return Arrays.equals(char1, char2);
    }


    public static void main(String[] args) {

        String[] testStr = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams2(testStr);

//        List<String> test = new ArrayList<>();
//        test.add("eae");
//        getAllString("eae", 3, 0, test);
//        System.out.println("the size====" + test.size());
//        printList(test);
    }
}
