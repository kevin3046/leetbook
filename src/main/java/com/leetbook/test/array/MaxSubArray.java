package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/16 12:43
 * @Description:
 */
public class MaxSubArray {

    /**
     * https://leetcode-cn.com/problems/maximum-subarray/
     * 最大子序列和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        //贪心法
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            //小于0的，赋值为当前数，重新开始计算
            if (current <= 0) {
                current = nums[i];
            } else {
                current += nums[i];
            }
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    /**
     * 动态规划优化版
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {

        int[] maxF = new int[nums.length];
        System.arraycopy(nums, 0, maxF, 0, nums.length);
        int max = maxF[0];
        for (int i = 1; i < nums.length; i++) {
            maxF[i] = Math.max(maxF[i - 1] + nums[i], nums[i]);
            max = Math.max(maxF[i], max);
        }
        return max;
    }

    /**
     * 最大子序列和，返回最大值，开始和结束的索引
     *
     * @param nums
     * @return
     */
    public int[] maxSubArrayIndex(int[] nums) {

        //贪心法
        int max = Integer.MIN_VALUE;
        int current = 0;
        int start = 0, end = 0;
        for (int i = 0; i < nums.length; i++) {
            //小于0的，赋值为当前数，重新开始计算
            if (current <= 0) {
                current = nums[i];
                start = end = i;
            } else {
                current += nums[i];
            }
            if (current > max) {
                max = current;
                end = i;
            }
        }
        return new int[]{max, start, end};
    }

}
