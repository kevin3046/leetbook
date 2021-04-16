package com.leetbook.test.string;

import java.util.Stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/11 11:46
 * @Description:
 * @tag:栈 394. 字符串解码
 * https://leetcode-cn.com/problems/decode-string/
 */
public class DecodeString {

    public String decodeString(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    Character c = stack.pop();
                    if (c == '[') {
                        break;
                    }
                    sb.append(c);
                }
                StringBuilder sb2 = new StringBuilder();
                while (true) {
                    if (stack.isEmpty()) {
                        break;
                    }
                    if (!Character.isDigit(stack.peek())) {
                        break;
                    }
                    sb2.append(stack.pop());

                }
                int nums = Integer.valueOf(sb2.reverse().toString());
                for (int j = 0; j < nums; j++) {
                    for (int k = sb.length() - 1; k >= 0; k--) {
                        stack.push(sb.charAt(k));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
