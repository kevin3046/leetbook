package com.leetbook.test.stack;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 10:05
 * @Description:
 * 最小栈
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa7r55/
 * 解题思路：使用了一个辅助栈
 * 参考：https://leetcode-cn.com/problems/min-stack/solution/zui-xiao-zhan-by-leetcode-solution/
 */
public class MinStack {

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

//    private List<Integer> nums = new ArrayList<>();
//    private Integer min = Integer.MAX_VALUE;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//
//    }
//
//    public void push(int x) {
//        nums.add(x);
//        min = Integer.MAX_VALUE;
//        for(Integer num:nums){
//            min = Math.min(num,min);
//        }
//    }
//
//    public void pop() {
//        nums.remove(nums.size()-1);
//        min = Integer.MAX_VALUE;
//        for(Integer num:nums){
//            min = Math.min(num,min);
//        }
//    }
//
//    public int top() {
//        return nums.get(nums.size()-1);
//    }
//
//    public int getMin() {
//        return min;
//    }
}
