package com.leetbook.test.weishi;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/24 16:16
 * @Description:
 * 大数据处理题目：
 * 1、https://www.pdai.tech/md/algorithm/alg-domain-bigdata-devide-and-hash.html
 * 2、https://www.cnblogs.com/yocichen/p/11356922.html
 * 3、https://segmentfault.com/a/1190000015127969
 * 4、https://www.jianshu.com/p/0aef53a3caf6
 */
public class WeiShiSolution {

    public static void main(String[] args){

        System.out.println((new WeiShiTest()).myPow(2.0,1000));

        String a = "0111 1111 0000 0000 0000 0000 0000 0000";
        Integer a0 = Integer.parseInt(a.replaceAll(" ",""),2);
        System.out.println(a0);

        String b = "0011 1111 0000 0000 0000 0000 0000 0000";
        Integer b0 = Integer.parseInt(b.replaceAll(" ",""),2);
        System.out.println(b0);

        //取a0的高8位
        StringBuilder str = new StringBuilder();
        for(int i=31;i>31-8;i--){
            int mask = 1 << i;
            str.append((a0 & mask) != 0 ? "1" : "0");
        }

        //取a0的高8位 参考：https://bbs.csdn.net/topics/393020283
        System.out.println( (a0 >> 24) & 0xff );// 将高8位移动到低位，再取模，得到8位数字。0xff代表16进制得256 （& 0xff，相当于 % 256）

        System.out.println( (a0 >> 16) & 0xff );// 次高8位

        System.out.println( (a0 >> 8) & 0xff );// 再次高8位

        System.out.println( a0  & 0xff );// 再次高8位,低8位


        /**
         * 一、10亿 50MB情况下模拟结果：
         * 构造文件耗时:98155 ms
         * 1073777265
         * 查找中位数耗时:552028 ms
         *
         * 二、1亿 5MB情况下模拟结果:
         * 构造文件耗时:10727 ms
         * 1073744830
         * 查找中位数耗时:62549 ms
         */
        String filename = "/tmp/10mill.dat";
        Long numsCount = 100000000L;//1亿
        Long heapSize = 5 * 1024 * 1024L;//5MB大小的内存,转换为字节byte（注：1个整数占4个byte）

        Long start = System.currentTimeMillis();
        (new WeiShiTest()).buildFile(filename,numsCount);
        System.out.println("构造文件耗时:"+(System.currentTimeMillis() - start)+" ms");


        start = System.currentTimeMillis();
        System.out.println((new WeiShiTest()).findMedian2(filename,numsCount,heapSize));
        System.out.println("查找中位数耗时:"+(System.currentTimeMillis() - start)+" ms");


        //1000万以下进行验证
//        start = System.currentTimeMillis();
//        List<Integer> list = (new WeiShiTest()).readFile(filename);
//        list.sort(((o1, o2) -> o1.compareTo(o2)));
//        int index = list.size()/2;
//        Long temp = (long) list.get(index) + list.get(index+1);
//        Integer ret = list.size()%2 == 0 ?((int)(temp/2)):list.get(index);
//        System.out.println(ret);
//        System.out.println("查找中位数耗时:"+(System.currentTimeMillis() - start)+" ms");

    }




}
