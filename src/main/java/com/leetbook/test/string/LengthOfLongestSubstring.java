package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/16 10:16
 * @Description:
 * @tag:滑动窗口 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        StringBuilder sb = new StringBuilder();
        //向窗口中添加字符串,如果窗口中,出现了该字符串,则移除其左边的字符
        for (int i = 0; i < s.length(); i++) {
            if (sb.indexOf(String.valueOf(s.charAt(i))) >= 0) {
                int index = sb.indexOf(String.valueOf(s.charAt(i)));
                sb.delete(0, index + 1);
            }
            sb.append(s.charAt(i));
            max = Math.max(max, sb.length());
        }
        return max;
    }
}
