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
     * @tag:双指针
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }

}
