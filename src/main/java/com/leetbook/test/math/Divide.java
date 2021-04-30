package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/26 17:07
 * @Description:
 * @tag:二分查找,快速乘法
 * 29. 两数相除
 * https://leetcode-cn.com/problems/divide-two-integers/
 * 参考题解：https://leetcode-cn.com/problems/divide-two-integers/solution/shua-chuan-lc-er-fen-bei-zeng-cheng-fa-j-m73b/
 */
public class Divide {

    public int divide(int a, int b) {
        long x = a, y = b;
        boolean isNeg = false;
        if ((x > 0 && y < 0) || (x < 0 && y > 0)) {
            isNeg = true;
        }
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        long l = 0, r = x;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * 「快速乘法」模板，采用了倍增的思想
     *
     * @param a
     * @param k
     * @return
     */
    long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) {
                ans += a;
            }
            k >>= 1;
            a += a;
        }
        return ans;
    }
}
