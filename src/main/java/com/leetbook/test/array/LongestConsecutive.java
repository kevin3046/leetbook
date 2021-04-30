package com.leetbook.test.array;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/25 16:24
 * @Description:
 */
public class LongestConsecutive {


    public int longestConsecutive(int[] nums) {

        if (nums.length <= 0) {
            return 0;
        }
        Arrays.sort(nums);
        int current = nums[0];
        int currentLen = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (current + 1 == nums[i]) {
                currentLen++;
                current++;
            } else {
                //跳过重复元素
                if (nums[i] == current) {
                    continue;
                }
                current = nums[i];
                currentLen = 1;
            }
            maxLen = Math.max(currentLen, maxLen);
        }
        return maxLen;
    }
}
