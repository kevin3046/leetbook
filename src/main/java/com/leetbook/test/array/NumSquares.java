package com.leetbook.test.array;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/25 17:11
 * @Description:
 * @tag：动态规划
 * 279. 完全平方数
 * https://leetcode-cn.com/problems/perfect-squares/
 * 解题思路：和零钱兑换思路一样
 */
public class NumSquares {

    public int numSquares(int n) {

        int sqrt_index = (int) Math.sqrt(n);
        int[] coins = new int[sqrt_index];
        for (int i = 1; i <= sqrt_index; i++) {
            coins[i - 1] = i * i;
        }
        int max = n + 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }
}
