package com.leetbook.test.weishi;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/26 14:00
 * @Description:
 * 题目一：求2的1000次方
 * 题目二：100亿的32位整数（1个文件），机器内存500MB，求中位数
 */
public class WeiShiTest0324 {

    public static void main(String[] args) {

        System.out.println((new WeiShiTest0324()).myPow(2, 10000));

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
        (new WeiShiTest0324()).buildFile(filename, numsCount);
        System.out.println("构造文件耗时:" + (System.currentTimeMillis() - start) + " ms");


        start = System.currentTimeMillis();
        System.out.println((new WeiShiTest0324()).findMedian(filename, numsCount, heapSize));
        System.out.println("查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");

        //1000万以下进行验证
//        start = System.currentTimeMillis();
//        List<Integer> list = (new WeiShiTest0324()).readFile(filename);
//
//        list.sort(((o1, o2) -> o1.compareTo(o2)));
//
//        int index = list.size()/2;
//        Long temp = (long) list.get(index) + list.get(index-1);
//        Integer ret = list.size()%2 == 0 ?((int)(temp/2)):list.get(index);
//
//        System.out.println(ret);
//        System.out.println("查找中位数耗时:"+(System.currentTimeMillis() - start)+" ms");

    }

    /**
     * 题目一：求2的1000次方
     * 快速幂解法，求x的n次方
     *
     * @param x
     * @param n
     * @return
     */
    public String myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul((new BigDecimal(x)), N).toString() : (new BigDecimal(1.0)).divide(quickMul(new BigDecimal(x), -N)).toString();
    }

    private BigDecimal quickMul(BigDecimal x, long N) {
        if (N == 0) {
            return new BigDecimal(1.0);
        }
        BigDecimal y = quickMul(x, N / 2);
        return N % 2 == 0 ? y.multiply(y) : y.multiply(y).multiply(x);
    }


    /**
     * 题目二：100亿的32位整数（1个文件），机器内存500MB，求中位数
     * 解题思路：读取文件，每个数按照二进制高位，进行文件分割，直到内存可以进行读取，并排序
     * 举例：
     * 1、100亿数字，其中位数为100亿个数字排序后的第50亿个数字
     * 2、对100亿个数字进行切割，取高位1个数字，例如分为：40亿=>10mill.dat_0 和 60亿=>10mill.dat_1
     *    二进制高位 0 代表正数 1代表负数 所以 目标中位数在 10mill.dat_1 文件中
     *    偏移量为：50亿-40亿=10亿
     * 3、对10mill.dat_1文件，再取二进制的次高位，进行切割，例如分为：30亿=>10mill.dat_1_0 和 30亿=> 10mill.dat_1_1
     *    可知目标中位数在10mill.dat_1_0 中
     *    偏移量不变
     * 4、对10mill.dat_1_0文件，再取二进制的次高位，进行切割，例如分为：5亿=>10mill.dat_1_0_0 和 25亿=> 10mill.dat_1_0_1
     *    可知目标中位数在10mill.dat_1_0_1 中
     *    偏移量为：10亿-5亿=5亿
     * 5、按照这样的规律一直分割下去，直到目标文件可以读入内存，进行排序，再根据偏移量，取出中位数。
     *
     * @param filename  文件名
     * @param numsCount 整数的个数
     * @param heapSize  内存限制
     * @return
     */
    public int findMedian(String filename, Long numsCount, Long heapSize) {

        //初始化中位数索引
        Long mid = numsCount / 2;
        //初始化切割文件之后的中位数索引
        Long offset = mid;
        //当前需要切割的文件名
        String cutFile = filename;
        String cutFile0 = cutFile + "_0";
        String cutFile1 = cutFile + "_1";
        Integer cutTimes = 1;
        //切割后的文件file_0,file_1的大小
        Long[] temp = new Long[2];
        Long file0Counts = 0L;
        Long file1Counts = 0L;
        //最终内存可处理的数组大小,由heapSize计算得来
        Long maxProcessSize = heapSize / 4;

        //如果内存足够,直接读取文件进行查找
        if (numsCount <= maxProcessSize) {
            return findMedianHelper(cutFile, mid, numsCount);
        }

        //内存不足,开始切割文件,进行处理
        while (true) {
            temp = cutFile(cutFile, cutFile0, cutFile1, cutTimes);
            file0Counts = temp[0];
            file1Counts = temp[1];

            if (offset > file0Counts) {
                offset = offset - file0Counts;
                cutFile = cutFile1;
            } else {
                cutFile = cutFile0;
            }
            //下一次要切割的文件目标名
            cutFile0 = cutFile + "_0";
            cutFile1 = cutFile + "_1";
            //切割次数+1
            cutTimes++;

            if (file0Counts > 0 && file0Counts <= maxProcessSize) {
                break;
            }
            if (file1Counts > 0 && file1Counts <= maxProcessSize) {
                break;
            }
        }

        return findMedianHelper(cutFile, offset, numsCount);
    }

    /**
     * 内存排序辅助类
     *
     * @param cutFile
     * @param offset
     * @param numsCount
     * @return
     */
    private Integer findMedianHelper(String cutFile, Long offset, Long numsCount) {
        List<Integer> list = readFile(cutFile);
        list.sort(((o1, o2) -> o1.compareTo(o2)));
        int index = Integer.valueOf(String.valueOf(offset));
        //偶数取均值
        if (numsCount % 2 == 0) {
            Long temp = (long) list.get(index) + list.get(index - 1);
            return (int) (temp / 2);
        } else {
            return list.get(index);
        }
    }

    /**
     * 切割cutFile文件,根据数字的二进制高位,分成cutFile0和cutFile1
     *
     * @param cutFile
     * @param cutFile0
     * @param cutFile1
     * @param cutTimes
     * @return
     */
    private Long[] cutFile(String cutFile, String cutFile0, String cutFile1, Integer cutTimes) {

        Long file0Counts = 0L;//0号文件计数
        Long file1Counts = 0L;//1号文件计数
        Integer bit = 32 - cutTimes;//切割文件高位掩码计算值,每次切割之后减少1位

        try {
            File file0 = new File(cutFile0);
            file0.createNewFile(); // 创建新文件
            BufferedWriter out0 = new BufferedWriter(new FileWriter(file0));

            File file1 = new File(cutFile1);
            file1.createNewFile(); // 创建新文件
            BufferedWriter out1 = new BufferedWriter(new FileWriter(file1));


            BufferedReader in = new BufferedReader(new FileReader(cutFile));
            String str;
            int mask = 1 << bit;
            Integer number;
            Integer n;
            while ((str = in.readLine()) != null) {
                number = Integer.valueOf(str);
                n = (number & mask) != 0 ? 1 : 0;
                if (n == 0) {
                    file0Counts++;
                    out0.write(number + "\r\n");
                } else {
                    file1Counts++;
                    out1.write(number + "\r\n");
                }
            }
            out0.flush();
            out0.close();

            out1.flush();
            out1.close();

            return new Long[]{file0Counts, file1Counts};

        } catch (IOException e) {
            e.printStackTrace();
            return new Long[]{};
        }
    }

    /**
     * 按行读取文件到数组
     *
     * @param filename
     * @return
     */
    public List<Integer> readFile(String filename) {
        try {
            List<Integer> res = new ArrayList<>();
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            while ((str = in.readLine()) != null) {
                res.add(Integer.valueOf(str));
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 写入numsCount个整数到文件
     */
    public void buildFile(String filename, Long numsCount) {

        try {
            /* 写入Txt文件 */
            File writename = new File(filename); // 相对路径，如果没有则要建立一个新的output。txt文件
            if (writename.exists()) {
                System.out.println("文件已存在,忽略构造");
                return;
            }
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            Random random = new Random();
            for (Long i = 0L; i < numsCount; i++) {
                out.write(random.nextInt(Integer.MAX_VALUE) + "\r\n"); // \r\n即为换行
                //out.write((i+1)+"\r\n"); // \r\n即为换行
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
