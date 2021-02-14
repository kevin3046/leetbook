package com.leetbook.test.string;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/13 20:58
 * @Description:前缀树节点
 */
public class TrieNode {

    // R links to node children
    private TrieNode[] children;
    public String word; //节点直接存当前的单词

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        children = new TrieNode[R];
        word = null;
        for (int i = 0; i < R; i++) {
            children[i] = null;
        }
    }

    public boolean containsKey(char ch) {
        return children[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return children[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        children[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
