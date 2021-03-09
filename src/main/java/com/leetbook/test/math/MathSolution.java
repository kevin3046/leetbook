package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/3 14:57
 * @Description:
 */
public class MathSolution {

    public static void main(String[] args) {

        // [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
        (new MaxPoints()).maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}});

        (new FractionToDecimal()).fractionToDecimal(4,10);

        (new CountPrimes()).countPrimes(10);

        (new CountNumbersWithUniqueDigits()).countNumbersWithUniqueDigits(3);

        //104639
        //104651
        //234
        (new CanMeasureWater()).canMeasureWater2(104639,104651,234);
    }

}
