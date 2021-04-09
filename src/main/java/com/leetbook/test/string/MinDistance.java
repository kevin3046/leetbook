package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/8 19:45
 * @Description:
 * @tag:动态规划
 * 72. 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 * 参考视频：https://www.bilibili.com/video/BV15h411Z7Qd?from=search&seid=3168756619255785139
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int left = dp[i][j - 1];
                    int up = dp[i - 1][j];
                    int left_up = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(left_up, Math.min(left, up)) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
