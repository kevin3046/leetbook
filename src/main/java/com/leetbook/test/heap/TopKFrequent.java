package com.leetbook.test.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 14:17
 * @Description:
 * @tag:哈希表 前 K 个高频元素
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xau4ci/
 * 解题思路：哈希表按照value排序
 */
public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        // List<Integer> res = new ArrayList<>();
        // for(int i=0;i<k;i++){
        //     res.add(list.get(i).getKey());
        // }
        // return res.stream().mapToInt(x->x).toArray();
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}
