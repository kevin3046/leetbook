package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:05
 * @Description:
 */
public class WordBreak2 {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa9v8i/
     * 单词拆分 II（困难题）
     * 回溯法，超出时间限制
     * 解题思路：使用了分割回文串和单词拆分I的组合解法
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {

        HashSet<String> set = new HashSet<>(wordDict);
        int len = s.length();
        List<String> res = new ArrayList<>();
        if(len == 0){
            return res;
        }
        //加入了优先判断是否可以进行分割,避免无效回溯超出时间限制
        if(!(new WordBreak()).wordBreakDp(s,wordDict)){
            return res;
        }

        Deque<String> path = new ArrayDeque<>();
        backtracking(s,0,len,path,res,set);

        return res;
    }

    private void backtracking(String s, int start, int len, Deque<String> path, List<String> res,HashSet<String> set) {

        if (start == len) {
            res.add(String.join(" ",path));
            return;
        }

        for (int i = start; i < len; i++) {

            // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
            // 不是的话，剪枝
            String a1 = s.substring(start,i+1);

            if(!set.contains(a1)){
                continue;
            }

            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, path, res,set);
            path.removeLast();
        }

    }
}
