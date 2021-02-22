package com.leetbook.test.stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 10:06
 * @Description:
 */
public class StackSolution {

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());

        (new Calculate()).calculate("3/2 ");
    }
}