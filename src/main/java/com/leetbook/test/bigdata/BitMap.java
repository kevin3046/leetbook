package com.leetbook.test.bigdata;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/29 19:05
 * @Description:bitmap的java实现
 * https://www.jianshu.com/p/9e7f8f33a61a
 */
public class BitMap {

    private long length;
    private static int[] bitsMap;

    //构造函数中传入数据中的最大值
    public BitMap(long length) {
        this.length = length + 1;
        // 根据长度算出，所需数组大小
        bitsMap = new int[(int) (this.length >> 5) + ((this.length & 31) > 0 ? 1 : 0)];
    }

    public int getBit(long index) {
        int intData = bitsMap[(int) ( index >> 5)];
        int offset = (int) ( index & 31);
        return intData >> offset & 0x01;
    }


    public void setBit(long index) {
        // 求出该index - 1所在bitMap的下标
        int belowIndex = (int) (index >> 5);
        // 求出该值的偏移量(求余)
        int offset = (int) (index & 31);
        int inData = bitsMap[belowIndex];
        bitsMap[belowIndex] = inData | (0x01 << offset);
    }

    public static void main(String[] args) {

        BitMap bitMap = new BitMap(65536);

        //去重的一个应用
        int[] array = {255, 1024, 1024, 0, 65536, 0, 1024, 8888, 9999, 1111, 8888};
        Arrays.sort(array);
        System.out.println(array[array.length/2]);
        int index = 0;
        for(int i=0;i<array.length;i++){
            if(bitMap.getBit(array[i])!=1){
                array[index] = array[i];
                index = index+1;
                bitMap.setBit(array[i]);
            }
        }
        array = Arrays.copyOf(array,index);
        System.out.println(Arrays.toString(array));
        System.out.println(array[array.length/2]);
    }
}
