package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:36
 * @Description:
 */
public class ReverseString {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xapbdt/
     * 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while (left<=right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
