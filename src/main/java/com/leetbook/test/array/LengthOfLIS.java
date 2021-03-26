package com.leetbook.test.array;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/19 10:04
 * @Description:
 * tag:递归+记忆化
 * 300. 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class LengthOfLIS {

    //nums = [10,9,2,5,3,7,101,18]
    int[] mem;

    public int lengthOfLIS(int[] nums) {

        this.mem = new int[nums.length + 1];
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i);
        }
        for (int i = 0; i < this.mem.length; i++) {
            maxLen = Math.max(maxLen, this.mem[i]);
        }
        return maxLen;
    }

    public int dfs(int[] nums, int index) {

        if (index >= nums.length) {
            return 0;
        }
        if (this.mem[index] > 0) {
            return this.mem[index];
        }

        int max = 1;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                max = Math.max(max, 1 + dfs(nums, i));
            }
        }

        this.mem[index] = max;
        return max;
    }

}
