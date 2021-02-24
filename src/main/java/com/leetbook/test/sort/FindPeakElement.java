package com.leetbook.test.sort;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 16:23
 * @Description:
 * 寻找峰值
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xacqw5/
 * 参考题解：https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode/
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]){
                return i;
            }
        }
        return nums.length - 1;
    }
}
