package com.leetbook.test.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/19 20:05
 * @Description:
 * @tag:滑动窗口
 * 567. 字符串的排列
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class CheckInclusion {

    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    /**
     * 暴力解法
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {

        for (int i = 0; i < s1.length(); i++) {
            ori.put(s1.charAt(i), ori.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int r = 0;
        int sLen = s2.length();
        while (r <= sLen - s1.length()) {
            if (ori.containsKey(s2.charAt(r))) {
                String temp = s2.substring(r, r + s1.length());
                cnt.clear();
                for (int i = 0; i < temp.length(); i++) {
                    cnt.put(temp.charAt(i), cnt.getOrDefault(temp.charAt(i), 0) + 1);
                }
                if (ori.equals(cnt)) {
                    return true;
                }
            }
            r++;
        }
        return false;
    }

    /**
     * 参考题解：https://leetcode-cn.com/problems/permutation-in-string/solution/zhu-shi-chao-xiang-xi-de-hua-dong-chuang-rc7d/
     * 参考了题解里的python代码
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {

        for (int i = 0; i < s1.length(); i++) {
            ori.put(s1.charAt(i), ori.getOrDefault(s1.charAt(i), 0) + 1);
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int sLen = s2.length();
        int left = 0;
        int right = s1.length() - 1;
        for (int i = 0; i < right; i++) {
            cnt.put(s2.charAt(i), cnt.getOrDefault(s2.charAt(i), 0) + 1);
        }
        //维护一个s1大小的cnt窗口。窗口右移的时候，则移除左边的一个元素
        while (right < sLen) {
            cnt.put(s2.charAt(right), cnt.getOrDefault(s2.charAt(right), 0) + 1);
            if (ori.equals(cnt)) {
                return true;
            }
            //窗口向右移动前，把当前 left 位置的元素出现次数 - 1
            cnt.put(s2.charAt(left), cnt.getOrDefault(s2.charAt(left), 0) - 1);
            //如果当前 left 位置的元素出现次数为 0， 需要从字典中删除
            if (cnt.getOrDefault(s2.charAt(left), 0) <= 0) {
                cnt.remove(s2.charAt(left));
            }
            left++;
            right++;
        }
        return false;
    }
}