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

        (new Trap()).trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});

        (new Permute()).permute(new int[]{1,2,3});

        (new LengthOfLIS()).lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});

        (new NumIslands()).numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}});


        (new LargestRectangleArea()).largestRectangleArea(new int[]{2,1,5,6,2,3});

        (new Rotate48()).rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});

        //(new Rotate48()).rotate(new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});

        (new Subsets()).subsets(new int[]{1,2,3});

        (new CoinChange()).coinChange(new int[]{1,2,5},11);

        (new MaximalSquare()).maximalSquare_dp(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}});

        (new CombinationSum()).combinationSum(new int[]{2,3,5},8);
    }
}
