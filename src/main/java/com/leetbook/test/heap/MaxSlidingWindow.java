package com.leetbook.test.heap;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 14:55
 * @Description:
 * @tag:最大堆
 * 滑动窗口最大值
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xatgye/
 * 参考题解：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
 * 解题思路：最大堆变种
 */
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
//        PriorityQueue<Integer> maxheap = new PriorityQueue<>((x, y) -> y - x);
//        for(int i=0;i<nums.length;i++){
//            if(maxheap.size() == k){
//                res.add(maxheap.peek());
//                maxheap.remove(nums[i-k]);
//            }
//            maxheap.add(nums[i]);
//        }
//        res.add(maxheap.peek());
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            //这个值在数组 \textit{nums}nums 中的位置出现在滑动窗口左边界的左侧。
            // 因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
