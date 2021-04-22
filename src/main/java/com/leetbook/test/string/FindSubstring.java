package com.leetbook.test.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/16 13:55
 * @Description:
 * @tag:滑动窗口 30. 串联所有单词的子串
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 */
public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int oneLen = words[0].length();
        int allLen = words[0].length() * words.length;
        //words的每个单词长度相同,遍历时截取allLen的字符串出来,组成map进行比较
        //不依赖顺序
        for (int i = 0; i <= s.length() - allLen; i++) {

            String temp = s.substring(i, i + allLen);
            Map<String, Integer> tmap = new HashMap<>();
            int l = 0;
            while (l <= temp.length() - oneLen) {
                String tstr = temp.substring(l, l + oneLen);
                tmap.put(tstr, tmap.getOrDefault(tstr, 0) + 1);
                l += oneLen;
            }
            if (map.equals(tmap)) {
                result.add(i);
            }

        }
        return result;
    }
}
