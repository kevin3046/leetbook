package com.leetbook.test.array;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/19 10:45
 * @Description:
 */
public class IncreasingTriplet {

    private boolean res = false;
    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmb141/
     * 递增的三元子序列
     * 20,100,10,12,5,13
     * 1,5,0,4,1,3
     * 解题思路1：使用回溯法，可以求解，只击败5%的用户
     * 解题思路2：参考：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/c-xian-xing-shi-jian-fu-za-du-xiang-xi-jie-xi-da-b/
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len < 3){
            return false;
        }
        Integer small = Integer.MAX_VALUE,middle = Integer.MAX_VALUE;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]<=small){
                small = nums[i];
            }else if(nums[i]<=middle){
                middle = nums[i];
            }else {
                return true;
            }
        }
        return false;

    }

    public boolean increasingTriplet_back(int[] nums) {
        int len = nums.length;
        if(len < 3){
            return false;
        }
        Deque<Integer> path = new ArrayDeque<>();
        backtracking(nums,0,len,path);
        return res;
    }

    private void backtracking(int[] nums, int start, int len, Deque<Integer> path){
        if(res){
            return;
        }
        if(path.size() == 3){
            List<Integer> list = new ArrayList<>(path);
            boolean result =  list.get(0) < list.get(1) && list.get(1) < list.get(2);
            if(result){
                res = true;
            }
            return;
        }

        for(int i=start;i<len;i++){
            if(!path.isEmpty() && path.getLast()>nums[i]){
                continue;
            }
            path.addLast(nums[i]);
            backtracking(nums,i+1,len,path);
            path.removeLast();
        }
    }
}
