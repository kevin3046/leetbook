package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/19 15:12
 * @Description:
 * @tag:滑动窗口
 * 209. 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 */
public class MinSubArrayLen {

    /**
     * 暴力解法
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {

        int current = target;
        int result = Integer.MAX_VALUE;
        int r = 0;
        for (int i = 0; i < nums.length; i++) {

            r = i;
            current = target;
            while (current > 0 && r < nums.length) {
                current -= nums[r];
                if (current <= 0) {
                    result = Math.min(result, r - i + 1);
                    break;
                }
                r++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 滑动窗口解法
     * 参考：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        //移动end指针,当sum满足条件后，移动start指针
        //任意时刻，只有一个指针在移动。
        while (end < n) {
            sum += nums[end];
            while (sum >= target) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
