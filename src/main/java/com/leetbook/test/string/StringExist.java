package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/12 14:57
 * @Description:
 * @tag:回溯法,前缀树
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 */
public class StringExist {

    private boolean flag = false;

    public boolean exist(char[][] board, String word) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(Character.toLowerCase(word.charAt(i)));
        }
        Trie trie = new Trie();
        trie.insert(sb.toString());
        int rows = board.length;
        if (rows == 0) {
            return false;
        }
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = Character.toLowerCase(board[i][j]);
            }
        }
        //从每个位置开始遍历
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, trie.root);
            }
        }
        return this.flag;
    }

    public void dfs(char[][] board, int row, int col, TrieNode node) {

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || this.flag) {
            return;
        }
        char cur = board[row][col];//将要遍历的字母
        //当前节点遍历过或者将要遍历的字母在前缀树中不存在
        if (cur == '$' || node.get(cur) == null) {
            return;
        }
        node = node.get(cur);
        //判断当前节点是否是一个单词的结束
        if (node.word != null) {
            //加入到结果中
            this.flag = true;
            //将当前单词置为 null，防止重复加入
            node.word = null;
        }
        char temp = board[row][col];
        //上下左右去遍历
        board[row][col] = '$';
        dfs(board, row - 1, col, node);
        dfs(board, row + 1, col, node);
        dfs(board, row, col - 1, node);
        dfs(board, row, col + 1, node);
        board[row][col] = temp;
    }
}
