package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/11 09:49
 * @Description:
 */
public class LargestTriangleArea {

    public double largestTriangleArea(int[][] points) {

        double area = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    area = Math.max(area, getArea(points[i], points[j], points[k]));
                }
            }
        }
        return area;
    }

    //A:(x1, y1) , B:(x2, y2) , C:(x3, y3)
    //高斯面积公式：S三角形 = 0.5 * ((x1 * y2 + x2 * y3 + x3 * y1) - (y1 * x2 + y2 * x3 + y3* x1))
    private double getArea(int[] A, int[] B, int[] C) {

        int x1 = A[0];
        int y1 = A[1];
        int x2 = B[0];
        int y2 = B[1];
        int x3 = C[0];
        int y3 = C[1];

        double res = 0.5 * Math.abs((x1 * y2 + x2 * y3 + x3 * y1) - (y1 * x2 + y2 * x3 + y3 * x1));

        return res;
    }
}
