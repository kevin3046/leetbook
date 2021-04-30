package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/25 11:09
 * @Description:
 * 75. 颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 */
public class SortColors {

    public void sortColors(int[] nums) {

        //维护一个左索引，依次循环进行交换
        int left = 0;
        int current = 0;
        while (current <= 2) {
            for (int i = left; i < nums.length; i++) {
                if (nums[i] == current) {
                    int temp = nums[left];
                    nums[left] = nums[i];
                    nums[i] = temp;
                    left++;
                }
            }
            current++;
        }
    }
}
