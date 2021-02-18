package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/16 19:17
 * @Description:
 */
public class Rotate {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xm42hs/
     * 旋转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if(nums.length==0 || k == 0) {
            return;
        }
        int n = nums.length;
        for(int i=0;i<k;i++){
            int temp = nums[n-1];
            for(int j=n-1;j>0;j--){
                nums[j] = nums[j-1];
            }
            nums[0] = temp;
        }
    }

    /**
     * 第二种解法
     * 参考题解：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
