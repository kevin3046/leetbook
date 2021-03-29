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

        String filename = "/tmp/ip_10mill.dat";
        Long numsCounts = 1000000L;
        Long heapSize = 1 * 1024 * 1024L;
        Integer top = 5;
        IOHelper.buildRandomIpFile(filename,numsCounts);

        (new BigDataTest.IpTop(filename,numsCounts,heapSize,top)).find();

        //PriorityQueue<Integer> queue = new PriorityQueue<>((x,y) -> (y - x));
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        int[] temp = new int[]{1,1,1,1,1,1,1,1,30,10,30};
//        for(int i=0;i<temp.length;i++){
//            queue.add(temp[i]);
//            if(queue.size()>3){
//                queue.poll();
//            }
//        }
//
//        System.out.println(queue.size());

    }
}
