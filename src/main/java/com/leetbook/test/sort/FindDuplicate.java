package com.leetbook.test.sort;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 16:44
 * @Description:
 * 寻找重复数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xabtn6/
 * 参考题解：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
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

    /**
     * 快慢指针
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {

        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }
}
