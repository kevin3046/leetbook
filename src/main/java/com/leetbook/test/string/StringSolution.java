package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/4 16:04
 * @Description:字符串相关
 */
public class StringSolution {

    public static void main(String[] args){

        (new IsPalindrome()).isPalindrome("A man, a plan, a canal: Panama");

        (new Partition()).partition("aab");

        (new WordBreak()).wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));

        (new WordBreak()).wordBreak("leetcode", Arrays.asList("leet", "code"));

        (new WordBreak()).wordBreakDp("leetcode", Arrays.asList("leet", "code"));

        (new WordBreak2()).wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));

        Trie trie = new Trie();

        trie.insert("apple");
        trie.search("apple");   // 返回 true
        trie.search("app");     // 返回 false
        trie.startsWith("app"); // 返回 true
        trie.insert("app");
        trie.search("app");     // 返回 true

        (new IsAnagram()).isAnagram("anagram","nagaram");
    }

}
