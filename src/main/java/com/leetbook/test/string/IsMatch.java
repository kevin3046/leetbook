package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/17 16:23
 * @Description:
 * 10. 正则表达式匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class IsMatch {

    //ab .*
    // *c*a*b aab
    //        "mississippi"
    //        "mis*is*p*."

    private int[][] mem;

    public boolean isMatch(String s, String p) {
        mem = new int[s.length() + 1][p.length() + 1];
        return backtracking(s, p, 0, 0);
    }

    public boolean backtracking(String s, String p, int s1, int p1) {

        if (p1 >= p.length()) {
            return s1 == s.length();
        }
        if (mem[s1][p1] != 0) {
            return mem[s1][p1] > 0;
        }

        boolean flag = s1 < s.length() && (s.charAt(s1) == p.charAt(p1) || p.charAt(p1) == '.');

        if (p1 + 2 <= p.length() && p.charAt(p1 + 1) == '*') {
            boolean ret = backtracking(s, p, s1, p1 + 2) || (flag && backtracking(s, p, s1 + 1, p1));
            if (ret) {
                mem[s1][p1] = 1;
            } else {
                mem[s1][p1] = -1;
            }
            return ret;
        }

        boolean ret1 = flag && backtracking(s, p, s1 + 1, p1 + 1);
        if (ret1) {
            mem[s1][p1] = 1;
        } else {
            mem[s1][p1] = -1;
        }
        return ret1;
    }


    public boolean isMatch2(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean flag = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch2(s, p.substring(2)) ||
                    (flag && isMatch2(s.substring(1), p));
        }
        return flag && isMatch2(s.substring(1), p.substring(1));
    }
}
