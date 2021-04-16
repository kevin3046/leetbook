package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/15 20:12
 * @Description:
 * @tag:牛顿迭代法
 * 69. x 的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class MySqrt {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
