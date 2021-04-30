package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/26 17:51
 * @Description:
 * 38. 外观数列
 * https://leetcode-cn.com/problems/count-and-say/
 * 参考题解：https://leetcode-cn.com/problems/count-and-say/solution/shua-chuan-lc-100-mo-ni-ti-shi-yong-shao-w8jl/
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            s = nextString(s);
        }
        return s;
    }

    private String nextString(String s) {
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int num = 1;
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == c) {
                num++;
            } else {
                sb.append(num);
                sb.append(c);
                c = cur;
                num = 1;
            }
        }
        sb.append(num);
        sb.append(c);
        return sb.toString();
    }
}
