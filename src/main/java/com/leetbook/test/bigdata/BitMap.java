package com.leetbook.test.bigdata;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/29 19:05
 * @Description:bitmap的java实现
 * https://www.jianshu.com/p/9e7f8f33a61a
 * 单bitmap：Integer.MAX_VALUE = 2^31 需要内存大小 2^31/32*4/1024/1024=256MB内存
 * 双bitmap：Integer.MAX_VALUE = 2^31 需要内存大小 2^31/16*4/1024/1024=512MB内存
 * 包含正负数的情况下：2^32 单bitmap需要512MB内存，双bitmap需要1G内存
 *
 * 单bitmap可以解决的问题：
 * 1、给40亿个不重复的unsigned int的整数，没排过序的，然后再给一个数，如何快速判断这个数是否在那40亿个数当中？
 *
 * 双bitmap可以解决的问题：
 * 1、在2.5亿个整数中找出不重复的整数
 * 2、海量数据中寻找中位数（解法：我们依次读取10G数据，然后转换为位图表示，去掉所有bit位为00的位置，找到中间下标就是中位数）
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
