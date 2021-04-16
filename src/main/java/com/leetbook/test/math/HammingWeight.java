package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 15:19
 * @Description: 位1的个数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2dj82/
 */
public class HammingWeight {

    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }
        return result;
    }
}
