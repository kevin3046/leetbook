package com.leetbook.test.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/25 17:42
 * @Description:
 */
public class DiscretizedTreeArray {

    int[] a;
    int[] c;

    DiscretizedTreeArray(int[] nums) {
        discretization(nums);
        init(nums.length + 5);
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    /**
     * https://blog.csdn.net/oyoung_2012/article/details/79932394
     * 当一个数与其取负后的值相与， 如果这个数是偶数， 则结果是能整除这个偶数的最大的2的幂
     * (即： m = n & -n , 则 n % m = 0, 且 m = 2 ^ k)，
     * 如果这个数是奇数， 则结果必为1
     * 用途： 一般可以用来获取某个二进制数的LowBit
     * @param x
     * @return
     */
    private int lowBit(int x) {
        return x & (-x);
    }

    public void update(int pos) {
        while (pos < c.length) {
            c[pos] += 1;
            int t = lowBit(pos);
            pos += lowBit(pos);
        }
    }

    public int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    public int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }
}
