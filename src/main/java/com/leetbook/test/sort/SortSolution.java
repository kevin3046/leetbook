package com.leetbook.test.sort;

import com.leetbook.test.heap.FindKthLargest;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 15:11
 * @Description:
 */
public class SortSolution {

    public static void main(String[] args) {
        (new LargestNumber()).largestNumber(new int[]{10,2});

        (new WiggleSort()).wiggleSort(new int[]{1,5,1,1,6,4});

        (new FindDuplicate()).findDuplicate2(new int[]{1,3,4,2,2});

        (new CountSmaller()).countSmaller5(new int[]{3,9,5,2,6,1,3});

        for(int i=0;i<100;i++){
            System.out.println("i="+i+"----i & (-i)="+(i & (-i)));
        }

        BinaryIndexdTree binaryIndexdTree = new BinaryIndexdTree(8);

        binaryIndexdTree.query(5);

        binaryIndexdTree.update(5,1);

        binaryIndexdTree.query(5);


    }
}
