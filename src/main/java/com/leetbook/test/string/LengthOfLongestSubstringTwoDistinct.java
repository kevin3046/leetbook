package com.leetbook.test.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/16 16:02
 * @Description:
 * @tag:滑动窗口 159. 至多包含两个不同字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LengthOfLongestSubstringTwoDistinct {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {

            //不存在的话，则添加
            if (sb.indexOf(String.valueOf(s.charAt(i))) < 0) {
                sb.append(s.charAt(i));
                count++;
                if (count > 2) {
                    //超出两位的时候,删除之前的字符串
                    int index = sb.lastIndexOf(String.valueOf(s.charAt(i - 1)));
                    //特殊处理i-1,i-2...相同的情况
                    for (int j = i - 2; j >= 0; j--) {
                        if (s.charAt(i - 1) == s.charAt(j)) {
                            index--;
                        } else {
                            break;
                        }
                    }
                    sb.delete(0, index);
                    count--;
                }

            } else {
                sb.append(s.charAt(i));
            }
            max = Math.max(max, sb.length());
        }
        return max;
    }

    /**
     * 参考题解：https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/solution/java-hua-dong-chuang-kou-by-lyl0724-2/
     *
     * @param s
     * @return
     * @tag:滑动窗口
     */
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        //扩展右指针,满足条件后,移除左侧元素
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > 2) {
                char leftChar = s.charAt(l++);
                int value = map.get(leftChar) - 1;
                if (value == 0) {
                    map.remove(leftChar);
                } else {
                    map.put(leftChar, value);
                }
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
