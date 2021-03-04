package com.leetbook.test.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/3 16:10
 * @Description:
 * @tag:数学
 * 分数到小数
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/x2vpk7/
 * 参考题解：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/fen-shu-dao-xiao-shu-by-leetcode/
 * 解题思路：核心思想是当余数出现循环的时候，对应的商也会循环
 */
public class FractionToDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

//    public String fractionToDecimal(int numerator, int denominator) {
//
//        double res = numerator * 1.0 / denominator * 1.0;
//
//        String s = String.valueOf(res);
//
//        String result = "";
//        if(s.indexOf(".")!=-1){
//            String s1 = s.substring(s.indexOf(".")+1,s.length());
//            if(s1.equals("0")){
//                return s.substring(0,s.indexOf("."));
//            }
//            String curr = "";
//            boolean flag = false;
//            for(int i=1;i<s1.length();i++){
//                curr = s1.substring(0,i);
//                if((i+curr.length())>s1.length() || (i+curr.length()+curr.length())>s1.length()){
//                    break;
//                }
//                String t1 = s1.substring(i,i+curr.length());
//                String t2 = s1.substring(i+curr.length(),i+curr.length()+curr.length());
//
//                if(curr.equals(t1) && curr.equals(t2)){
//                    result = curr;
//                    flag = true;
//                    break;
//                }
//            }
//            if(flag){
//                return s.substring(0,s.indexOf("."))+".("+result+")";
//            }
//        }
//        return s;
//    }
}
