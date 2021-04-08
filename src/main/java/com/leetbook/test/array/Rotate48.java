package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/7 19:06
 * @Description: 48. 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class Rotate48 {

    public void rotate(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int rf = 0;
        int cf = 0;
        while (true) {
            rotateHelper(matrix, rows, cols, rf, cf);
            rf++;
            cf++;
            if ((cols - 2 * cf) < 2) {
                break;
            }
        }
    }

    private void rotateHelper(int[][] matrix, int rows, int cols, int rf, int cf) {

        for (int wheels = 0; wheels < cols - 1 - (2 * cf); wheels++) {
            /**
             * 向右 保存 3
             * 1 1 2
             * 4 5 6
             * 7 8 9
             */
            int temp = matrix[rf][cols - 1 - cf];
            for (int i = cols - 1 - cf; i > cf; i--) {
                matrix[rf][i] = matrix[rf][i - 1];
            }

            /**
             * 向下 保存 9
             * 1 1 2
             * 4 5 2
             * 7 8 6
             */
            int temp2 = matrix[rows - 1 - rf][cols - 1 - cf];
            for (int i = rows - 1 - rf; i > rf; i--) {
                matrix[i][cols - 1 - cf] = matrix[i - 1][cols - 1 - cf];
            }

            /**
             * 回填 3
             * 1 1 2
             * 4 5 3
             * 7 8 6
             */
            matrix[1 + rf][cols - 1 - cf] = temp;

            /**
             * 向左 保存 7
             * 1 1 2
             * 4 5 3
             * 8 6 6
             */
            temp = matrix[rows - 1 - rf][cf];
            for (int i = cf; i < cols - 2 - cf; i++) {
                matrix[rows - 1 - rf][i] = matrix[rows - 1 - rf][i + 1];
            }

            /**
             * 回填 9
             * 1 1 2
             * 4 5 3
             * 8 9 6
             */
            matrix[rows - 1 - rf][cols - 2 - cf] = temp2;

            /**
             * 向上
             * 4 1 2
             * 8 5 3
             * 8 9 6
             */
            for (int i = rf; i < rows - 2 - rf; i++) {
                matrix[i][cf] = matrix[i + 1][cf];
            }
            /**
             * 回填 7
             * 4 1 2
             * 7 5 3
             * 8 9 6
             */
            matrix[rows - 2 - rf][cf] = temp;
        }
    }
}
