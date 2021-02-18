package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/16 18:30
 * @Description:
 */
public class MajorityElement {

    /**
     * @tag:摩尔投票法
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmz79t/
     * 多数元素
     * 参考题解：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
     * 解题思路：摩尔投票法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
