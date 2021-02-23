package com.leetbook.test.stack;

import java.util.Stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 14:03
 * @Description:
 * 逆波兰表达式求值
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaqlgj/
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n1,n2;

        for (String token : tokens) {
            switch (token) {
                case "+":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 + n1);

                    break;
                case "-":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 - n1);

                    break;
                case "*":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 * n1);

                    break;
                case "/":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 / n1);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.peek();
    }

}
