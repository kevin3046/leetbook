package com.leetbook.test.string;

import java.util.Stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/8 15:14
 * @Description:
 * @tag:栈,双栈
 * 678. 有效的括号字符串
 * https://leetcode-cn.com/problems/valid-parenthesis-string/
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * <p>
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 */
public class CheckValidString {

    public boolean checkValidString(String s) {

        Stack<Integer> left = new Stack<>(), star = new Stack<>(); // index stack
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        while (!left.isEmpty() && !star.isEmpty()) {
            if (left.pop() > star.pop()) {
                return false;
            }
        }
        return left.isEmpty();
    }
}
