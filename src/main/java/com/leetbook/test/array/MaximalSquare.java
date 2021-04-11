package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/11 10:06
 * @Description: 221. 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class MaximalSquare {

    private int calCap(char[][] matrix, int r, int c) {

        int sum = 0;
        int len = 1;
        while (true) {
            for (int i = r; i <= r + len; i++) {
                for (int j = c; j <= c + len; j++) {
                    sum += isOne(matrix, i, j);
                }
            }
            if (sum < Math.pow(len + 1, 2)) {
                sum = (int) Math.pow(len, 2);
                break;
            }
            sum = 0;
            len++;
        }
        return sum;
    }

    private int isOne(char[][] matrix, int r, int c) {
        int nr = matrix.length;
        int nc = matrix[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || matrix[r][c] == '0') {
            return 0;
        }
        return 1;
    }

    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int nr = matrix.length;
        int nc = matrix[0].length;
        int max = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (matrix[r][c] == '1') {
                    max = Math.max(max, calCap(matrix, r, c));
                }
            }
        }

        return max;
    }

    public int maximalSquare_dp(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
