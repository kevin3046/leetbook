package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/25 16:55
 * @Description:
 * @tag:二分查找
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int[] result = new int[]{-1, -1};

        if (index == -1) {
            return result;
        }
        for (int i = index; i >= 0; i--) {

            if (nums[i] != target) {
                break;
            }
            result[0] = i;
        }
        for (int i = index; i < nums.length; i++) {

            if (nums[i] != target) {
                break;
            }
            result[1] = i;
        }
        return result;
    }
}
