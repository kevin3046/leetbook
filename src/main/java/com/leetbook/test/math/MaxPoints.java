package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/3 14:56
 * @Description:
 * @tag:直线方程 直线上最多的点数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2n2g1/
 * 解题思路：根据(x1,y1)(x2,y2)检查(x,y)点是否在该直线上
 */
public class MaxPoints {

    public int maxPoints(int[][] points) {

        if (points.length < 3) {
            return points.length;
        }
        int x1, y1, x2, y2, x, y;
        int max = 2, current = 2;

        for (int i = 0; i < points.length; i++) {
            x1 = points[i][0];
            y1 = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                x2 = points[j][0];
                y2 = points[j][1];
                current = 2;
                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    x = points[k][0];
                    y = points[k][1];
                    if (caluLine(x1, y1, x2, y2, x, y)) {
                        current++;
                    }
                }
                max = Math.max(max, current);
            }
        }
        return max;
    }

    /**
     * 直线方程运算
     * x-x1 / x1-x2 = y-y1 / y1-y2
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x
     * @param y
     * @return
     */
    private boolean caluLine(int x1, int y1, int x2, int y2, int x, int y) {
        return (long) (x - x1) * (y1 - y2) == (long) (x1 - x2) * (y - y1);
    }
}
