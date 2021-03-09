package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 16:18
 * @Description:
 * @tag:数学
 * 3的幂
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2lkle/
 */
public class IsPowerOfThree {

    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

}
