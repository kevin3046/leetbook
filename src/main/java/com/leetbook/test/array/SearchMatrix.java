package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/19 13:01
 * @Description:
 */
public class SearchMatrix {

    /**
     * @param matrix
     * @param target
     * @return
     * @tag:二分查找
     * 搜索二维矩阵 II
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmjzs7/
     * 解题思路：二分查找binarySearch
     * 参考题解：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-4/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] > target) {
                break;
            }
            if (matrix[i][matrix[i].length - 1] < target) {
                continue;
            }
            int col = binarySearch(matrix[i], target);
            if (col != -1) {
                return true;
            }
        }
        return false;
    }

    //二分查找
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


}
