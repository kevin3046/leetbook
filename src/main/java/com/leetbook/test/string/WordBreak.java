package com.leetbook.test.string;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/14 22:03
 * @Description:
 */
public class WordBreak {

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa503c/
     *
     * @tag:动态规划,回溯法,完全背包 参考资料：https://github.com/youngyangyang04/leetcode-master
     * 参考资料：https://leetcode-cn.com/problems/word-break/solution/139-dan-ci-chai-fen-hui-su-fa-wan-quan-b-0zwf/
     * 单词拆分（中等题）
     * 解题思路：
     * 参考答案：https://leetcode-cn.com/problems/word-break/solution/javahui-su-dao-ji-yi-hua-hui-su-zai-dao-dong-tai-g/
     * 记忆化回溯
     * 首先将字典List加入到HashSet当中，这样每次判断有无单词均摊时间为O(1)。
     * <p>
     * 逐个增加单词的字符数量，直到字典中有了该单词之后，递归判断剩下的字符串，如果无法匹配返回false，如果最后剩下空字符串说明全部匹配成功返回true。
     * 由于java的subString生成了新的字符串对象，因此不会对原来的字符串产生影响，因此回溯之后不需要做任何处理来复原原状态。
     * 增加一个boolean数组表示当前位置之后的字符串是否遍历过了，如果遍历过了并且没有提前递归的返回true说明，
     * 这个位置后面的匹配是不会成功的，因此直接返回false。
     * @param s
     * @param wordDict
     * @return
     */
    boolean[] mem;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.mem = new boolean[s.length()];
        Set<String> set = new HashSet<>(wordDict);
        return backtracking(s, 0, set);
        //或者
        //return backtrack_2(s, 0, set);
    }

    /**
     * 回溯法，记忆化递归
     * 解题思路：使用了和分割回文串，相同的回溯手法，进行了解题，只是不记录path而已
     *
     * @param s
     * @param start
     * @param set
     * @return
     */
    public boolean backtracking(String s, int start, Set<String> set) {
        if (s.length() == start) {
            return true;
        }
        if (mem[start]) {
            return false;
        }
        for (int i = start; i < s.length(); i++) {
            String a1 = s.substring(start, i + 1);
            if (!set.contains(a1)) {
                continue;
            }
            if (backtracking(s, i + 1, set)) {
                return true;
            }
        }
        mem[start] = true;
        return false;

    }


    /**
     * 使用了字符串截取 和 backtrack 一样的回溯法，异曲同工之妙
     *
     * @param s
     * @param start
     * @param set
     * @return
     */
    public boolean backtrack_2(String s, int start, Set<String> set) {
        if (s.length() == 0) {
            return true;
        }
        if (mem[start]) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            String a1 = s.substring(0, i + 1);
            if (set.contains(a1)) {
                String a2 = s.substring(i + 1);
                if (backtrack_2(a2, start + i + 1, set)) {
                    return true;
                }
            }
        }
        mem[start] = true;
        return false;
    }

    /**
     * 单词拆分（中等题）
     * 动态规划解法：https://leetcode-cn.com/problems/word-break/solution/javahui-su-dao-ji-yi-hua-hui-su-zai-dao-dong-tai-g/
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakDp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
