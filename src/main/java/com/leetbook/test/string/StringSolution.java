package com.leetbook.test.string;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/4 16:04
 * @Description:字符串相关
 */
public class StringSolution {

    public static void main(String[] args) {

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

        (new IsAnagram()).isAnagram("anagram", "nagaram");


        boolean ret = (new IsMatch()).isMatch2("aab", "c*a*b");

        System.out.println(ret);

        (new GenerateParenthesis()).generateParenthesis(3);

        (new LongestValidParentheses()).longestValidParentheses(")(()");

        (new MinDistance()).minDistance("sea", "ate");

        (new MinWindow()).minWindow("ABAACBAB", "ABC");

        (new DecodeString()).decodeString("3[a]2[bc]");

        (new GroupAnagrams()).groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

        (new LetterCombinations()).letterCombinations("23");

        (new LengthOfLongestSubstring()).lengthOfLongestSubstring("pwwkew");

        (new FindSubstring()).findSubstring("barfoothefoobarman",new String[]{"foo","bar"});

        (new LengthOfLongestSubstringTwoDistinct()).lengthOfLongestSubstringTwoDistinct2("eceba");


        (new CheckInclusion()).checkInclusion2("adc","dcda");

        (new MinWindow2()).isSubseq("bcdeb","bdeb");

        //"jmeqksfrsdcmsiwvaovztaqenprpvnbstl"
        //"k"
        (new MinWindow2()).minWindow2("aabccdebdde","bde");

        (new RemoveInvalidParentheses()).removeInvalidParentheses("(a)())()");

        (new CountAndSay()).countAndSay(6);

    }

}
