package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/25 14:16
 * @Description:
 * @Remark:参考了题解：https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
 * 85. 最大矩形
 * https://leetcode-cn.com/problems/maximal-rectangle/
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, columns = matrix[0].length;
        //一次计算出左1的个数
        int dp[][] = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                }
            }
        }
        //遇到1的时候,向上进行扩展,计算出矩形的最大面积sequence
        int area = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    int t_area = dp[i][j];
                    int len = dp[i][j];
                    int h = 1;
                    for (int k = i - 1; k >= 0; k--) {
                        len = Math.min(dp[k][j], len);
                        h++;
                        t_area = Math.max(t_area, len * h);

                    }
                    area = Math.max(area, t_area);
                }
            }
        }

        return area;
    }
}
