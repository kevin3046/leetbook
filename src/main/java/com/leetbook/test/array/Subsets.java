package com.leetbook.test.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/19 09:44
 * @Description:
 * @tag:回溯法,组合应用
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        Deque<Integer> path = new ArrayDeque<>();
        for(int i=1;i<=nums.length;i++) {
            backtracking(nums, 0, res, path, i);
        }
        return res;
    }


    public void backtracking(int[] nums, int start, List<List<Integer>> res, Deque<Integer> path,int len){
        if(path.size() == len){
            res.add(new ArrayList<>(path));
        }
        for(int i=start;i<nums.length;i++){
            path.add(nums[i]);
            backtracking(nums,i+1,res,path,len);
            path.removeLast();
        }
    }
}
