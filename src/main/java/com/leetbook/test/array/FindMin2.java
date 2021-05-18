package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/14 10:36
 * @Description:
 * @tag:二分查找
 * 154. 寻找旋转排序数组中的最小值 II
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMin2 {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high){
            int mid = (low + high) / 2;
            //中位数比最后一位大,说明有序段,位于后半段。
            if(nums[mid] > nums[high]){
                low = mid+1;
            }else if(nums[mid] < nums[high]){
                high = mid;
            }else {
                high -= 1;
            }
        }
        return nums[low];
    }
}
