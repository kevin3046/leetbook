package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/7 14:53
 * @Description:
 * 84. 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * 参考题解（暴力法）：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
 */
public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {

            // 找左边最后一个大于等于 heights[i] 的下标
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后一个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }
}
