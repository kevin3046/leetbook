package com.leetbook.test.array;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/17 19:24
 * @Description:
 */
public class Intersect {

    /**
     * 两个数组的交集 II
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmcbym/
     * 该种方法适合nums2较大的情况
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            Integer count = map.getOrDefault(nums1[i], 0) + 1;
            map.put(nums1[i], count);

        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            Integer count = map.getOrDefault(nums2[i], 0);
            if (count > 0) {
                res.add(nums2[i]);
                count--;
                if (count > 0) {
                    map.put(nums2[i], count);
                } else {
                    map.remove(nums2[i]);
                }
            }
        }
        return res.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 进阶，数组已经排序完成
     * <p>
     * 解题思路：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/
     * <p>
     * 方法二：排序 + 双指针
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     * <p>
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
     * <p>
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，
     * 如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，
     * 并将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
