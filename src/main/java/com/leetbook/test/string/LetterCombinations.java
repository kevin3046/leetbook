package com.leetbook.test.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/11 15:34
 * @Description:
 * @tag:回溯法,组合应用
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

//    Set<List<Character>> set = new HashSet<>();
//    Map<Character, Integer> map1 = new HashMap<>();
//    Map<Integer, List<Character>> map = new HashMap<>();
//
//
//    public List<String> letterCombinations(String digits) {
//
//        List<String> result = new ArrayList<>();
//        if (digits.length() == 0) {
//            return result;
//        }
//        map.put(2, Arrays.asList('a', 'b', 'c'));
//        map.put(3, Arrays.asList('d', 'e', 'f'));
//        map.put(4, Arrays.asList('g', 'h', 'i'));
//        map.put(5, Arrays.asList('j', 'k', 'l'));
//        map.put(6, Arrays.asList('m', 'n', 'o'));
//        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
//        map.put(8, Arrays.asList('t', 'u', 'v'));
//        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
//        map1.put('a', 2);
//        map1.put('b', 2);
//        map1.put('c', 2);
//        map1.put('d', 3);
//        map1.put('e', 3);
//        map1.put('f', 3);
//        map1.put('g', 4);
//        map1.put('h', 4);
//        map1.put('i', 4);
//        map1.put('j', 5);
//        map1.put('k', 5);
//        map1.put('l', 5);
//        map1.put('m', 6);
//        map1.put('n', 6);
//        map1.put('o', 6);
//        map1.put('p', 7);
//        map1.put('q', 7);
//        map1.put('r', 7);
//        map1.put('s', 7);
//        map1.put('t', 8);
//        map1.put('u', 8);
//        map1.put('v', 8);
//        map1.put('w', 9);
//        map1.put('x', 9);
//        map1.put('y', 9);
//        map1.put('z', 9);
//
//
//        List<Character> comb = new ArrayList<>();
//        for (int i = 0; i < digits.length(); i++) {
//            comb.addAll(this.map.get(digits.charAt(i) - '0'));
//        }
//        Deque<Character> path = new ArrayDeque<>();
//        dfs(comb, 0, digits.length(), result, path);
//
//        return result;
//    }
//
//    private void dfs(List<Character> comb, int start, int len, List<String> result, Deque<Character> path) {
//        if (path.size() == len) {
//
//            List<Character> temp = new ArrayList<>();
//
//            Set<Integer> set1 = new HashSet<>();
//            Iterator<Character> iterator = path.iterator();
//            while (iterator.hasNext()) {
//                Character c = iterator.next();
//                if (set1.add(this.map1.get(c))) {
//                    temp.add(c);
//                }
//            }
//            if (path.size() != temp.size()) {
//                return;
//            }
//            Collections.sort(temp);
//            if (this.set.add(temp)) {
//                StringBuilder sb = new StringBuilder();
//                for (Character c : temp) {
//                    sb.append(c);
//                }
//                result.add(sb.toString());
//            }
//            return;
//        }
//        for (int i = start; i < comb.size(); i++) {
//            path.addLast(comb.get(i));
//            dfs(comb, i + 1, len, result, path);
//            path.removeLast();
//        }
//    }
}
