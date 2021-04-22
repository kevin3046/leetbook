package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/20 13:03
 * @Description:
 * @tag:滑动窗口
 * 727. 最小窗口子序列
 * https://leetcode-cn.com/problems/minimum-window-subsequence/
 */
public class MinWindow2 {

    /**
     * 暴力解法
     * 超时
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + t.length(); j <= s.length(); j++) {
                String window = s.substring(i, j);
                if (window.length() >= t.length() && isSubseq(window, t)) {
                    if (result.length() == 0) {
                        result = window;
                    } else if (window.length() < result.length()) {
                        result = window;
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 判断window里，是否包含t，切顺序一致
     *
     * @param t
     * @param window
     * @return
     */
    public boolean isSubseq(String window, String t) {
        int i = 0;
        int j = 0;
        while (i < window.length() && j < t.length()) {
            if (window.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        boolean ret = i == window.length() && j == t.length();

        return ret;
    }

    /**
     * 参考题解:https://leetcode-cn.com/problems/minimum-window-subsequence/solution/727-zui-xiao-chuang-kou-zi-xu-lie-by-alexer-660/
     *
     * @param s
     * @param t
     * @return
     * @tag:滑动窗口
     */
    public String minWindow2(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        int start = 0;
        int end = s.length() - 1;
        int i = 0;
        int j = 0;
        //不断增加右指针,找到匹配的字符之后,收缩左指针
        while (i < s.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            if (j == t.length()) {
                int right = i;
                j--;
                while (j >= 0) {
                    if (s.charAt(i) == t.charAt(j)) {
                        j--;
                    }
                    i--;
                }
                i++;
                if (right - i + 1 < end - start + 1) {
                    start = i;
                    end = right;
                }
                j = 0;
            }
            i++;
        }
        String result = end - start + 1 == s.length() ? "" : s.substring(start, end + 1);

        return result;
    }


}
