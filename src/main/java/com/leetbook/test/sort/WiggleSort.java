package com.leetbook.test.sort;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 15:53
 * @Description: 摆动排序 II
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaygd7/
 * 解题思路：排序以后，从中心开始扩散
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {

        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(temp);
        int k1 = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        int k2 = nums.length - 1;


        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = temp[k1];
                k1--;
            } else {
                nums[i] = temp[k2];
                k2--;
            }
        }
    }
}
