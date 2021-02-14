package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:01
 * @Description:
 */
public class IsPalindrome {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/
     * @tag:双指针
     * 验证回文串
     * 解题思路：采用双指针法
     * @param s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while (i<=j){
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
                continue;
            }
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
