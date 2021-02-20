package com.leetbook.test.array;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/16 12:52
 * @Description:
 */
public class ArraySolution {

    public static void main(String[] args){

        (new MaxProduct()).maxProductIndex(new int[]{2,3,-2,4});

        (new MajorityElement()).majorityElement(new int[]{3,2,3});

        (new Rotate()).rotate(new int[]{-1,-2,-3},2);

        (new MoveZeroes()).moveZeroes2(new int[]{0,1,0,3,12});

        (new IncreasingTriplet()).increasingTriplet(new int[]{20,100,10,12,5,13});
        (new IncreasingTriplet()).increasingTriplet(new int[]{1,5,0,4,1,3});

        (new IncreasingTriplet()).increasingTriplet(new int[]{5,4,3,2,1});

        (new SearchMatrix()).searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30},{40,50,60,70,80}},5);

        (new ProductExceptSelf()).productExceptSelf(new int[]{1,2,3,4});
    }
}
