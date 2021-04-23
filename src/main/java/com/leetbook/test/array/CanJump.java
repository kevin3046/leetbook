package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/23 16:27
 * @Description:
 * @tag:贪心算法
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 */
public class CanJump {


    /**
     * 参考了官方题解:https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //如果当前i的位置是可达的,那么就在这些可达的位置里,更新从这些位置为起跳点,最远可达的位置
            //如果最远可达的位置大于最后的位置，那么就返回true
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean flag = false;

    /**
     * dfs解法超时
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        dfs(nums, 0, nums.length - 1);
        return flag;
    }

    private void dfs(int[] nums, int index, int target) {

        if (this.flag) {
            return;
        }
        if (target == 0) {
            this.flag = true;
            return;
        }

        if (target < 0) {
            return;
        }

        if (index > nums.length - 1) {
            return;
        }
        int num = nums[index];
        if (num == 0) {
            return;
        }
        for (int i = num; i >= 1; i--) {
            dfs(nums, index + i, target - i);
        }
    }
}
