package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/19 15:19
 * @Description:
 * @tag:深度优先遍历
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        dfs(n, "", res, 0, 0);
        return res;
    }

    private void dfs(int n, String path, List<String> res, int open, int close) {
        if (open > n || close > open) {
            return;
        }

        if (path.length() == 2 * n) {
            res.add(path);
            return;
        }

        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }
//    public List<String> generateParenthesis(int n) {
//
//        int[] nums = new int[n*2];
//        for(int i=1;i<=n*2;i++){
//            nums[i-1] = i;
//        }
//
//
//        List<List<Integer>> list = new ArrayList<>();
//        Deque<Integer> path = new ArrayDeque<>();
//        boolean used[] = new boolean[nums.length];
//        backtracking(nums,used,list,path);
//
//
//        Set<String> set = new HashSet<>();
//
//        for(List<Integer> item:list){
//            String str = build(item);
//            if(isVaild(str)){
//                set.add(str);
//            }
//        }
//        return new ArrayList<>(set);
//    }
//
//    public void backtracking(int[] nums, boolean[] used, List<List<Integer>> res, Deque<Integer> path){
//
//        if(path.size() == nums.length){
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for(int i=0;i<nums.length;i++){
//            if(used[i]){
//                continue;
//            }
//            used[i] = true;
//            path.addLast(nums[i]);
//            backtracking(nums,used,res,path);
//            path.removeLast();
//            used[i] = false;
//
//        }
//    }
//
//    public String build(List<Integer> path){
//        StringBuilder sb = new StringBuilder();
//        for(Integer item:path){
//            sb.append(item%2==0?"(":")");
//        }
//        return sb.toString();
//    }
//
//    public boolean isVaild(String str){
//        Stack<Character> stack = new Stack<>();
//        for(int i=0;i<str.length();i++){
//            if(str.charAt(i) == '('){
//                stack.push(str.charAt(i));
//            }else if(!stack.isEmpty()){
//                stack.pop();
//            }
//        }
//        return stack.isEmpty();
//    }
}
