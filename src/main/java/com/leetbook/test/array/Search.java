package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/14 11:20
 * @Description:
 * @tag:二分查找
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Search {

    //nums=4,5,6,7,0,1,2 target=5,target=2
    //nums=6,7,0,1,2,3,4,5 target=6,target=4
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //中位数比最后一位还大,说明前半段有序,否则就是后半段有序
            if (nums[mid] > nums[high]) {
                //判断target是否在前半段区间里,否则就是在后半段里
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            } else {
                //判断target是否在后半段区间里,否则就在前半段里
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
