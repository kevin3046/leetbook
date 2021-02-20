package com.leetbook.test.heap;

import java.util.LinkedList;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 11:03
 * @Description:
 */
public class HeapSolution {
    public static void main(String[] args) {

        (new FindKthLargest()).findKthLargest(new int[]{3,2,1,5,6,4},2);

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(4);
        medianFinder.addNum(5);
        medianFinder.addNum(6);
        medianFinder.addNum(7);
        medianFinder.findMedian();


        (new MaxSlidingWindow()).maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7,4,3,2,5,7},3);

        //(new MaxSlidingWindow()).maxSlidingWindow(new int[]{1,-1},1);

        System.out.println("xx");

    }
}
