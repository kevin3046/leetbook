package com.leetbook.test.array;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:40
 * @Description:
 */
public class MaxProduct {

    /**
     * @param nums
     * @return
     * @tag:回溯法 https://leetcode-cn.com/leetbook/read/top-interview-questions/xmk3rv/
     * 乘积最大子数组
     * 参考题解：https://leetcode-cn.com/problems/maximum-product-subarray/solution/duo-chong-si-lu-qiu-jie-by-powcai-3/
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        int pre_max = nums[0];
        int pre_min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur_max = Math.max(Math.max(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            int cur_min = Math.min(Math.min(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            res = Math.max(res, cur_max);
            pre_max = cur_max;
            pre_min = cur_min;
        }
        return res;
    }

    /**
     * 使用回溯法遍历出所有组合
     *
     * @param nums
     * @return
     */
    public int[] maxProductIndex(int[] nums) {
        int len = nums.length;
        List<List<int[]>> res = new ArrayList<>();
        if (len == 0) {
            return new int[]{0, 0, 0};
        }
        Deque<int[]> path = new ArrayDeque<>();
        backtracking(nums, 0, len, path, res);

        int max = nums[0];
        int start = 0, end = 0;
        for (List<int[]> item : res) {
            for (int[] items : item) {
                int[] temp = Arrays.copyOfRange(nums, items[0], items[1]);
                int ans = temp[0];
                for (int i = 1; i < temp.length; i++) {
                    ans *= temp[i];
                }
                if (ans > max) {
                    max = ans;
                    start = items[0];
                    end = items[1];
                }
            }

        }
        return new int[]{max, start, end};
    }

    private void backtracking(int[] nums, int start, int len, Deque<int[]> path, List<List<int[]>> res) {
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < len; i++) {
            path.addLast(new int[]{start, i + 1});
            backtracking(nums, i + 1, len, path, res);
            path.removeLast();
        }
    }
}
