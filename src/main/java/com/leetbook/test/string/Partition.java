package com.leetbook.test.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:02
 * @Description:
 */
public class Partition {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaxi62/
     * @tag:深度优先搜索,动态规划,回溯算法
     * 分割回文串
     * 解题思路：采用回溯法
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        Deque<String> path = new ArrayDeque<>();
        backtracking(s,0,len,path,res);

        return res;
    }

    /**
     * 回溯法可以解决的问题：组合，切割字符串，子集，排列，棋盘（N皇后，解数独）
     * 回溯法模版：
     * void backtracking(参数){
     *     if(终止条件){
     *         收集结果
     *         return;
     *     }
     *     //单层搜索
     *     for(集合的元素集){
     *         1、处理节点
     *         2、递归函数
     *         3、回溯操作（撤销处理的节点）
     *
     *     }
     *     return;
     * }
     * @param s
     * @param start
     * @param len
     * @param path
     * @param res
     */

    private void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {

        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {

            // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
            // 不是的话，剪枝
            String a1 = s.substring(start,i+1);

            if (!checkPalindrome(s, start, i)) {
                continue;
            }

            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, path, res);
            path.removeLast();
        }

    }

    private boolean checkPalindrome(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
