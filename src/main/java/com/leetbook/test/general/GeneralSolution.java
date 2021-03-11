package com.leetbook.test.general;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/10 09:46
 * @Description:
 */
public class GeneralSolution {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        queue.poll();
        queue.add("A");
        queue.offer("A");
        String ret = ((ArrayDeque<String>) queue).pop();


        Queue<String> queue1 = new LinkedList<String>();
    }
}
