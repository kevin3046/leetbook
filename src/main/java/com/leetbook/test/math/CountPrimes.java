package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 15:38
 * @Description:
 * @tag:数学
 * 计数质数
 * 质数，除了
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x20yuc/
 * 参考题解：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
 */
public class CountPrimes {

    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
