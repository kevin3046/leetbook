package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:27
 * @Description:
 */
public class IsAnagram {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xar9lv/
     * 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
