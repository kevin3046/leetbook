package com.leetbook.test.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/17 15:37
 * @Description:
 * @tag:回溯法,排列问题
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permute {

    /**
     * 排列问题，不再使用组合问题的start变量，而采用used变量
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean used[] = new boolean[nums.length];
        backtracking(nums,used,res,path);

        return res;
    }

    public void backtracking(int[] nums, boolean[] used, List<List<Integer>> res, Deque<Integer> path){

        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            backtracking(nums,used,res,path);
            path.removeLast();
            used[i] = false;

        }
    }
}
