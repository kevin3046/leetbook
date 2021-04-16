package com.leetbook.test.string;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/11 13:01
 * @Description:
 * @tag:哈希表+排序 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class GroupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

//    private Set<String> stringSet = new HashSet<>();
//
//    public List<List<String>> groupAnagrams(String[] strs) {
//
//        List<String> temp = new ArrayList<>();
//        for (int i = 0; i < strs.length; i++) {
//            stringSet.add(strs[i]);
//        }
//        List<List<String>> result = new ArrayList<>();
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < strs.length; i++) {
//            List<String> res = new ArrayList<>();
//            Deque<Character> path = new ArrayDeque<>();
//            boolean[] used = new boolean[strs.length + 1];
//            backtracking(strs[i], used, res, path);
//
//            List<String> res2 = new ArrayList<>();
//            for (String item : res) {
//                if (set.add(item)) {
//                    res2.add(item);
//                }
//            }
//            if (res2.size() > 0) {
//                result.add(res2);
//            }
//
//        }
//        if (temp.size() > 0) {
//            result.add(temp);
//        }
//        return result;
//    }
//
//    public void backtracking(String str, boolean[] used, List<String> res, Deque<Character> path) {
//
//        if (path.size() == str.length()) {
//            StringBuilder sb = new StringBuilder();
//            Iterator iterator = path.iterator();
//            while (iterator.hasNext()) {
//                sb.append(iterator.next());
//            }
//            if (this.stringSet.contains(sb.toString())) {
//                res.add(sb.toString());
//            }
//            return;
//        }
//        for (int i = 0; i < str.length(); i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
//            path.addLast(str.charAt(i));
//            backtracking(str, used, res, path);
//            path.removeLast();
//            used[i] = false;
//        }
//    }
}
