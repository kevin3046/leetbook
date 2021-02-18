package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:30
 * @Description:
 */
public class FirstUniqChar {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaph0j/
     * 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        for(int i=0;i<s.length();i++){
            if(table[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
