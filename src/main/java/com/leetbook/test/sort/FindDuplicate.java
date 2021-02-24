package com.leetbook.test.sort;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 16:44
 * @Description:
 * 寻找重复数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xabtn6/
 */
public class FindDuplicate {

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] == nums[i+1]){
                return nums[i];
            }
        }
        return 0;
    }
}
