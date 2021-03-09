package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 15:05
 * @Description:
 * 颠倒二进制位
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2dx36/
 */
public class ReverseBits {

    public int reverseBits(int n) {
        //参考了题解，与十进制反转类似
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n = n >> 1;
        }
        return res;
    }
}
