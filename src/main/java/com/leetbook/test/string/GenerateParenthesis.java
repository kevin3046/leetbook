package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/19 15:19
 * @Description:
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
//        List<String> res = new ArrayList<>();
//        if (n <= 0) {
//            return res;
//        }
//        dfs(n, "", res);
//        return res;
//    }
//
//    private void dfs(int n, String path, List<String> res) {
//        if (path.length() == 2 * n) {
//            res.add(path);
//            return;
//        }
//
//        dfs(n, path + "(", res);
//        dfs(n, path + ")", res);
//    }


//    Set<String> set = new HashSet<>();
//
//    public List<String> generateParenthesis(int n) {
//
//        char[] nums = new char[n*2];
//        for(int i=0;i<n*2;i++){
//            nums[i] = i%2==0?'(':')';
//        }
//
//
//        List<List<Character>> list = new ArrayList<>();
//        Deque<Character> path = new ArrayDeque<>();
//        boolean used[] = new boolean[nums.length];
//        backtracking(nums,used,list,path);
//
//        return new ArrayList<>(set);
//    }
//
//    public void backtracking(char[] nums, boolean[] used, List<List<Character>> res, Deque<Character> path){
//
//        if(path.size() == nums.length){
//            String str = build(path);
//            if(!set.contains(str) && isVaild(str)) {
//                set.add(str);
//                res.add(new ArrayList<>(path));
//            }
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
//    public String build(Deque<Character> path){
//        StringBuilder sb = new StringBuilder();
//        for(Character item:path){
//            sb.append(item);
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
