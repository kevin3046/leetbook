package com.leetbook.test.bigdata;

import com.leetbook.test.weishi.IOHelper;

import java.util.PriorityQueue;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/29 11:10
 * @Description:
 */
public class BigDataSolution {

    public static void main(String[] args){

//        String filename = "/tmp/ip_10mill.dat";
//        Long numsCounts = 1000000L;
//        Long heapSize = 1 * 1024 * 1024L;
//        Integer top = 5;
//        IOHelper.buildRandomIpFile(filename,numsCounts);
//
//        (new BigDataTest.IpTop(filename,numsCounts,heapSize,top)).find();

        long length = Integer.MAX_VALUE * 1L;
        System.out.println((int) (length >> 5) + ((length & 31) > 0 ? 1 : 0));

        long n = 400000000L * 1L;

        int index = (int) n>>5;

        int offset = (int) n & 31;

        System.out.println(index+"="+offset);


        int a = ~(0x3 << 2);
        System.out.println( 4 & a);

    }
}
