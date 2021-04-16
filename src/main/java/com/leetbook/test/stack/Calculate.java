package com.leetbook.test.stack;

import java.util.Stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/22 11:31
 * @Description: 基本计算器 II
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa8q4g/
 * 参考题解1：https://leetcode-cn.com/problems/basic-calculator-ii/solution/chai-jie-fu-za-wen-ti-shi-xian-yi-ge-wan-zheng-ji-/
 * 参考题解2：https://leetcode-cn.com/problems/basic-calculator-ii/solution/java-ke-yi-tong-guo-224-227-772zhe-san-dao-ti-by-4/
 */
public class Calculate {

    private int i = 0;

    public int calculate(String s) {

        char[] chars = s.toCharArray();

        int res = dfs(chars);

        return res;
    }

    public int dfs(char[] chars) {

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int current = 0;
        int pre = 0;

        for (; i < chars.length; i++) {

            if (Character.isDigit(chars[i])) {
                current = current * 10 + (chars[i] - '0');
            }
            if (chars[i] == '(') {
                i++;
                current = dfs(chars);
                i++;
            }
            if ((!Character.isDigit(chars[i]) && chars[i] != ' ') || i == chars.length - 1) {
                switch (sign) {
                    case '+':
                        stack.push(current);
                        break;
                    case '-':
                        stack.push(-current);
                        break;
                    case '*':
                        pre = stack.peek();
                        stack.pop();
                        stack.push(pre * current);
                        break;
                    case '/':
                        pre = stack.peek();
                        stack.pop();
                        stack.push(pre / current);
                        break;
                    default:
                        break;
                }
                sign = chars[i];
                current = 0;
            }
            if (chars[i] == ')') {
                break;
            }
        }
        return sum(stack);

    }

    private int sum(Stack<Integer> stack) {
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.peek();
            stack.pop();
        }
        return res;
    }

}
