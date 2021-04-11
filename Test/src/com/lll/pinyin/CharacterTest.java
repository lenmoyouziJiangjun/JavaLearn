package com.lll.pinyin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 2020-09-02.
 * Description
 * copyright generalray4239@gmail.com
 */
public class CharacterTest {


    static class PinChar {
        String sourceName;
        String charName;


        public PinChar(String sourceName) {
            this.sourceName = sourceName;
            try {
                this.charName = CharacterParser.getInstance().getSpelling(sourceName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "PinChar{" +
                    "sourceName='" + sourceName + '\'' +
                    ", charName='" + charName + '\'' +
                    '}';
        }
    }

    public static void sortArray() {
        List<PinChar> chars = new ArrayList<>();

        chars.add(new PinChar("-12-通道1"));
        chars.add(new PinChar("123-通道2"));
        chars.add(new PinChar("-123-通道3"));
        chars.add(new PinChar("123-通道4"));

        chars.add(new PinChar("H123-通道4"));
        chars.add(new PinChar("A123-通道4"));
        chars.add(new PinChar("B123-通道4"));

        chars.add(new PinChar("新设备-通道1"));
        chars.add(new PinChar("新设备2-通道1"));
        chars.add(new PinChar("新设备3-通道1"));
        chars.add(new PinChar("新设备4-通道1"));
        chars.add(new PinChar("新设备-通道2"));
        chars.add(new PinChar("-新设备-通道3"));
        chars.add(new PinChar("测试1-通道1"));
        chars.add(new PinChar("测试2-通道2"));
        chars.add(new PinChar("ad测试"));
        chars.add(new PinChar("a测试"));
        chars.add(new PinChar("测试2-通道2"));


        chars.sort(new Comparator<PinChar>() {
            @Override
            public int compare(PinChar o1, PinChar o2) {
//                String c1 = o1.sourceName.substring(0, 1);
//                String c2 = o2.sourceName.substring(0, 1);
//                System.out.println(c1 + "--------------" + c2);
//                if (c1.matches("[\u4e00-\u9fa5]") && !c2.matches("[\u4e00-\u9fa5]")) {
//                    return 1;
//                } else if (!c1.matches("[\u4e00-\u9fa5]") && c2.matches("[\u4e00-\u9fa5]")) {
//                    return -1;
//                } else {
//                    return o1.charName.compareTo(o2.charName);
//                }

                return compareTo(o1.sourceName, o2.sourceName);
            }
        });
        for (PinChar pc : chars) {
            System.out.println(pc.toString());
        }
    }

    /**
     * 数字 -> 英文 -> 中文
     *
     * @param sourceStr
     * @param anotherString
     * @return
     */
    public static int compareTo(String sourceStr, String anotherString) {
        int len1 = sourceStr.length();
        int len2 = anotherString.length();
        int lim = Math.min(len1, len2);
        char v1[] = sourceStr.toCharArray();
        char v2[] = anotherString.toCharArray();
        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            String s1 = String.valueOf(c1).toLowerCase();
            String s2 = String.valueOf(c2).toLowerCase();
            if (s1.matches("[\u4e00-\u9fa5]") && !s2.matches("[\u4e00-\u9fa5]")) {
                System.out.println(" s1 汉字：：：" + s1);
                return 1;
            } else if (!s1.matches("[\u4e00-\u9fa5]") && s2.matches("[\u4e00-\u9fa5]")) {
                System.out.println(" s2 汉字：：：" + s2);
                return -1;
            } else if (s1.matches("[\u4e00-\u9fa5]") && s2.matches("[\u4e00-\u9fa5]")) {
                System.out.println(" s1 汉字：：：" + s1 + "s2  汉字：：：" + s2);
                int num = CharacterParser.getInstance().getFirstChar(s1) - CharacterParser.getInstance().getFirstChar(s2);
                if (num != 0) {
                    return num;
                }
            } else if (!s1.equals(s2)) {
                return  s1.toCharArray()[0] - s2.toCharArray()[0];
            }
            k++;
        }
        return len1 - len2;
    }

    public static void main(String[] args) {
        CharacterTest.sortArray();
    }
}
