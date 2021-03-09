package com.leetbook.test.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/4 16:04
 * @Description:
 * @tag:哈希表
 * 缺失数字
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x27sii/
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        //很简单，使用hash表，来查询即可
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
}
