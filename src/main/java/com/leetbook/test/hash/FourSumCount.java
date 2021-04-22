package com.leetbook.test.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 11:21
 * @Description:
 * @tag:哈希表 四数相加 II
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xakn6r/
 * 参考题解：https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
 */
public class FourSumCount {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>(A.length);
        for (int u : A) {
            for (int v : B) {
                map.put(u + v, map.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (map.containsKey(-u - v)) {
                    ans += map.get(-u - v);
                }
            }
        }
        return ans;
    }
}
