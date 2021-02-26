package com.leetbook.test.sort;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/25 16:19
 * @Description: 计算右侧小于当前元素的个数
 * @tag:离散化树状数组,线段树（区间树）,二分查找 https://leetcode-cn.com/leetbook/read/top-interview-questions/xajl22/
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 */
public class CountSmaller {

    /**
     * 自研，暴力求解超时
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    /**
     * 自研假二分，超时
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller2(int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[]{nums[i], i});
        }
        list.sort((o1, o2) -> o1[0] - o2[0]);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            res.add(count(list, nums[i], i));
        }
        return res;
    }

    private int count(List<int[]> list, int target, int index) {

        int count = 0;
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (list.get(mid)[0] == target) {
                for (int i = mid - 1; i >= 0; i--) {
                    if (list.get(i)[1] < index || list.get(i)[0] == target) {
                        continue;
                    }
                    count++;
                }
                return count;
            } else if (list.get(mid)[0] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找删除已经处理过的元素
     * 参考题解：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/si-chong-jie-fa-nan-du-yi-ci-di-zeng-by-littlefogc/
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller3(int[] nums) {
        // 排序好的数组
        List<Integer> sorted = new ArrayList<>();
        for (int num : nums) {
            sorted.add(num);
        }
        Collections.sort(sorted);

        List<Integer> counts = new ArrayList<>(nums.length);
        for (int n : nums) {
            // 通过二分法找到位置
            int left = 0;
            int right = sorted.size() - 1;
            int mid = (left + right) / 2; // nums[i] 在 sorted 中的位置
            while (left < right) {
                mid = (left + right) / 2;
                if (sorted.get(mid) == n) {
                    break;
                } else if (sorted.get(mid) > n) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (left == right) {
                mid = left;
            }
            while (mid > 0 && sorted.get(mid).equals(sorted.get(mid - 1))) {
                mid--;
            }
            counts.add(mid); // 第mid大，即有mid个比他小的
            sorted.remove(mid); // 比较结束，删掉
        }

        return counts;
    }

    /**
     * 离散树状数据，官方解法
     * 参考：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/ji-suan-you-ce-xiao-yu-dang-qian-yuan-su-de-ge-s-7/
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller4(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        DiscretizedTreeArray dtArray = new DiscretizedTreeArray(nums);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = dtArray.getId(nums[i]);
            resultList.add(dtArray.query(id - 1));
            dtArray.update(id);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    /**
     * 树状数组
     * 参考bilibili视频讲解：https://www.bilibili.com/video/BV16r4y1K7PM?p=3
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller5(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (Integer num : set) {
            map.put(num, rank++);
        }
        BinaryIndexdTree bit = new BinaryIndexdTree(rank - 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            rank = map.get(nums[i]);
            bit.update(rank, 1);
            list.add(bit.query(rank - 1));
        }
        Collections.reverse(list);
        return list;
    }
}
