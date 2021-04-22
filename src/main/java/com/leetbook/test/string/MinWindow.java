package com.leetbook.test.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/9 19:37
 * @Description:
 * @tag:滑动窗口 76. 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * 参考了题解：https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
 */
public class MinWindow {

    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        //1、两个指针，l和r，任意时刻，只有一个指针在运动
        //2、r指针用来扩展窗口，l指针用来收缩窗口
        //3、当r指针不断扩张，包含目标字符串的时候，来收缩l指针
        //4、判断收缩之后的窗口，是否能包含目标字符串，收缩到一定程度之后，求最小。
        //5、不关注t的顺序
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
}
