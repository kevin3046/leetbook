package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 14:43
 * @Description:
 * 阶乘后的零
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2we65/
 * 解题思路：查看n里面有多少个2*5的组合
 */
public class TrailingZeroes {

    public int trailingZeroes(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
