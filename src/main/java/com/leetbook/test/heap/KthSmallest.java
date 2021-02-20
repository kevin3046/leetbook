package com.leetbook.test.heap;

import java.util.PriorityQueue;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 14:10
 * @Description:
 * 有序矩阵中第K小的元素
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaicbc/
 * @tag:最大堆
 */
public class KthSmallest {

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((x, y) -> y - x);
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                maxheap.add(matrix[i][j]);
                if(maxheap.size()>k){
                    maxheap.poll();
                }
            }
        }
        return maxheap.peek();
    }
}
