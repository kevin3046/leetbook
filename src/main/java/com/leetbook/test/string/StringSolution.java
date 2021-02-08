package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/4 16:04
 * @Description:字符串相关
 */
public class StringSolution {

    public static void main(String[] args){
        StringSolution stringSolution = new StringSolution();

        stringSolution.isPalindrome("A man, a plan, a canal: Panama");

        stringSolution.partition("aab");

        //stringSolution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));

        stringSolution.wordBreak("leetcode", Arrays.asList("leet", "code"));
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/
     * @tag:双指针
     * 验证回文串
     * 解题思路：采用双指针法
     * @param s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while (i<=j){
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
                continue;
            }
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


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

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa503c/
     * @tag:动态规划,回溯法,完全背包
     * 参考资料：https://github.com/youngyangyang04/leetcode-master
     * 参考资料：https://leetcode-cn.com/problems/word-break/solution/139-dan-ci-chai-fen-hui-su-fa-wan-quan-b-0zwf/
     * 单词拆分
     * 解题思路：
     * 参考答案：https://leetcode-cn.com/problems/word-break/solution/javahui-su-dao-ji-yi-hua-hui-su-zai-dao-dong-tai-g/
     * 记忆化回溯
     * 首先将字典List加入到HashSet当中，这样每次判断有无单词均摊时间为O(1)。
     *
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
        return backtrack(s, 0, set);
    }

    /**
     * 回溯法，记忆化递归
     * @param s
     * @param start
     * @param set
     * @return
     */
    public boolean backtrack(String s, int start, Set<String> set) {
        if (s.length() == 0){
            return true;
        }
        if (mem[start]){
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            String a1 = s.substring(0,i+1);
            if (set.contains(a1)){
                String a2 = s.substring(i + 1);
                if (backtrack(a2, start + i + 1, set)){
                    return true;
                }
            }
        }
        mem[start] = true;
        return false;
    }


}
