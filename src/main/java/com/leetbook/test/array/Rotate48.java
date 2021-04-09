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

        int rowsStart = rf;
        int rowsEnd = rows - 1 - rf;
        int colsStart = cf;
        int colsEnd = cols - 1 - cf;
        for (int wheels = 0; wheels < cols - 1 - (2 * cf); wheels++) {
            /**
             * 向右 保存 3
             * 1 1 2
             * 4 5 6
             * 7 8 9
             */
            int temp = matrix[rowsStart][colsEnd];
            for (int i = colsEnd; i > colsStart; i--) {
                matrix[rowsStart][i] = matrix[rowsStart][i - 1];
            }

            /**
             * 向下 保存 9
             * 1 1 2
             * 4 5 2
             * 7 8 6
             */
            int temp2 = matrix[rowsEnd][colsEnd];
            for (int i = rowsEnd; i > rowsStart; i--) {
                matrix[i][colsEnd] = matrix[i - 1][colsEnd];
            }

            /**
             * 回填 3
             * 1 1 2
             * 4 5 3
             * 7 8 6
             */
            matrix[1 + rowsStart][colsEnd] = temp;

            /**
             * 向左 保存 7
             * 1 1 2
             * 4 5 3
             * 8 6 6
             */
            temp = matrix[rowsEnd][colsStart];
            for (int i = colsStart; i < colsEnd - 1; i++) {
                matrix[rowsEnd][i] = matrix[rowsEnd][i + 1];
            }

            /**
             * 回填 9
             * 1 1 2
             * 4 5 3
             * 8 9 6
             */
            matrix[rowsEnd][colsEnd - 1] = temp2;

            /**
             * 向上
             * 4 1 2
             * 8 5 3
             * 8 9 6
             */
            for (int i = rowsStart; i < rowsEnd - 1; i++) {
                matrix[i][colsStart] = matrix[i + 1][colsStart];
            }
            /**
             * 回填 7
             * 4 1 2
             * 7 5 3
             * 8 9 6
             */
            matrix[rowsEnd - 1][colsStart] = temp;
        }
    }
}
