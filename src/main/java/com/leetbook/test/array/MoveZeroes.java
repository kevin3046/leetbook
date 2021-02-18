package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/17 18:07
 * @Description:
 */
public class MoveZeroes {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmy9jh/
     * 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        int len = nums.length;
        int left = 0,right = len;
        while (left<right){
            if(nums[left] == 0){
                for(int j=left;j<len-1;j++){
                    nums[j] = nums[j+1];
                }
                nums[len-1] = 0;
                right--;
            }else{
                left++;
            }
        }
    }

    /**
     * 参考题解：https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

}
