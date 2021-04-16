package com.leetbook.test.heap;

import java.util.PriorityQueue;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 10:51
 * @Description:
 * @tag:最小堆,最大堆
 * 数组中的第K个最大元素
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xal9h6/
 * <p>
 * 最小堆参考：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/javadai-ma-de-2chong-da-an-by-sdwwld/
 * 最大堆参考：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
 */
public class FindKthLargest {

    /**
     * 最小堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}
