package com.leetbook.test.array;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/17 18:05
 * @Description:
 */
public class ContainsDuplicate {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xm1rfd/
     * 存在重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if(nums.length==0) {
            return false;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                return true;
            }
        }
        return false;
    }
}
