package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/14 10:33
 * @Description:
 * @tag:二分查找
 * 153. 寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMin {

    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];

    }
}
