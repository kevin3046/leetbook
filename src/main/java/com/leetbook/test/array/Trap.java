package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/11 16:20
 * @Description: 42. 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class Trap {

    public int trap2(int[] height) {

        int res = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int rightIndex = 0;

        for (int i = 1; i < height.length; i++) {

            if (height[i - 1] >= maxLeft) {
                maxLeft = 0;
                for (int j = i - 1; j >= 0; j--) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }
            }

            if (i >= rightIndex) {
                maxRight = 0;
                for (int j = i + 1; j < height.length; j++) {
                    if (height[j] > maxRight) {
                        maxRight = height[j];
                        rightIndex = j;
                    }
                }
            }


            int min = Math.min(maxLeft, maxRight);

            if (min > height[i]) {
                res += min - height[i];
            }

        }
        return res;
    }

    public int trap(int[] height) {

        int res_1 = cal(height);
        int[] height2 = new int[height.length];
        int i = 0;
        for (int j = height.length - 1; j >= 0; j--) {
            height2[i] = height[j];
            i++;
        }
        int res_2 = cal(height2);
        return Math.max(res_1, res_2);
    }

    private int cal(int[] height) {

        int res = 0;
        int left = 0;
        int right = height.length;
        while (left < right) {
            int current = left + 2;
            int pop = 1;
            while (current < right) {
                if (height[current] >= height[left] && height[left] != 0 && height[current] != 0) {
                    int x = current - left - 1;
                    int y = Math.min(height[left], height[current]);
                    int sum = x * y;
                    int i = left + 1;
                    while (i < current) {
                        sum -= height[i];
                        i++;
                    }
                    res += sum;
                    pop = current;
                    break;
                }
                current++;
            }
            if (pop > 1) {
                left = pop;
            } else {
                left++;
            }
        }

        return res;
    }

}
