package com.leetbook.test.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 14:51
 * @Description: 最大数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa1401/
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {

        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Comparator cmp = new MyComparator();
        Arrays.sort(strings, cmp);

        if (strings[0].equals("0")) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        for (String s : strings) {
            res.append(s);
        }
        return res.toString();
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String order1 = o1 + o2;
            String order2 = o2 + o1;
            return order2.compareTo(order1);
        }
    }
}
