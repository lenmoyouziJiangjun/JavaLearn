package algorithms.chars;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-01-07.
 * Description
 * <pre>
 *     串联所有单词的子串
 * </pre>
 * <p>
 * copyright generalray4239@gmail.com
 */
public class LeetCode30 {


    /**
     * <pre>
     *     给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     *     注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
     *
     *
     * 输入：
     *   s = "barfoothefoobarman",
     *   words = ["foo","bar"]
     *   输出：[0,9]
     * 解释：
     *    从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     *    输出的顺序不重要, [9,0] 也是有效答案。
     *
     *
     * </pre>
     *
     * @param s
     * @param words
     * @return
     */

    List<String> allListStr = new ArrayList<>(); //记录字符串的全排列

    private List<Integer> findSubstring(String s, String[] words) {
        //第一步计算words数组生成所有子字符串
        // 计算全排列
        if (words == null || words.length == 0 || s == null) {//数据合法性校验
            return null;
        }
        getAllString(words, 0);

        //第二步字符串匹配
        return null;
    }


    /**
     * @param words
     * @param next
     * @return
     */
    private void getAllString(String[] words, int next) {
        if (next == words.length - 1) {
            //最后一个元素，不用交换了，
            printWords(words);
        } else {
            for (int i = next; i < words.length; i++) {
                if (words[next] != words[i]) {
                    String temp = words[i];
                    words[i] = words[next];
                    words[next] = temp;
                    printWords(words);
                    getAllString(words, next + 1);
                    temp = words[next];
                    words[next] = words[i];
                    words[i] = temp;
                    printWords(words);
                }
            }
        }
    }

    private void addString(String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
        }
        allListStr.add(sb.toString());
    }


    private void printWords(String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
        }
        System.out.println("----------------");
        System.out.println("str ===   " + sb.toString());
    }

    private void printList(List<Integer> list) {
        System.out.println(list.toString());
    }


    public void test() {
//        String str = "barfoothefoobarman";
        String str = "wordgoodbetterwordgoodgood";
//        String[] words = new String[]{"foo", "bar"};
        String[] words = new String[]{"word", "good", "better", "word"};

//        findSubstring(str, words);

        printList(findAllSubstring(str, words));
    }


    /**
     * 思路就是： 将 s 分成 words单词长度个 子串，如果子串在wrods列表中，继续下一个子串，如果下一个也在，继续。如果不在就跳出
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findAllSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length(); //这里有个bug，单词长度不能这么写死
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }


    /**
     * 相对于上面每次移动一个字符，这个每次移动一个单词的长度
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }

        }
        return res;
    }

}
