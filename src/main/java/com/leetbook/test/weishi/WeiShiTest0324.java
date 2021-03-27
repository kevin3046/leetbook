package com.leetbook.test.weishi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/26 14:00
 * @Description: 海量数据处理练习
 * 题目一：求2的1000次方
 * 题目二：100亿的32位整数（1个文件），机器内存500MB，求中位数
 */
public class WeiShiTest0324 {

    public static void main(String[] args) {

        System.out.println((new WeiShiTest0324()).myPow(2, 10000));

        /**
         * 一、10亿 50MB情况下，二分切割文件，模拟结果：
         * 构造文件耗时:98155 ms
         * 1073777265
         * 查找中位数耗时:552028 ms
         *
         * 二、1亿 5MB情况下，二分切割文件，模拟结果:
         * 构造文件耗时:10727 ms
         * 1073744830
         * 查找中位数耗时:62549 ms
         *
         * 三、桶排序 1亿 5MB情况下，桶排序切割文件，模拟结果:
         * 构造文件耗时:11605 ms
         * 1073739987
         * 桶排序查找中位数耗时:21926 ms
         * 对比来看，减少了3倍的时间
         */
        String filename = "/tmp/10mill.dat";
        Long numsCount = 100000000L;//1亿
        Long heapSize = 5 * 1024 * 1024L;//5MB大小的内存,转换为字节byte（注：1个整数占4个byte）

        Long start = System.currentTimeMillis();
        IOHelper.buildRandomFile(filename, numsCount);
        System.out.println("构造文件耗时:" + (System.currentTimeMillis() - start) + " ms");


//        start = System.currentTimeMillis();
//        System.out.println((new WeiShiTest0324()).findMedian(filename, numsCount, heapSize));
//        System.out.println("二分切割文件,查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println((new WeiShiTest0324()).findMedianByBucket(filename, numsCount, heapSize));
        System.out.println("桶排序切割文件,查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");

        //1000万以下进行验证
        if (numsCount <= 10000000L) {
            start = System.currentTimeMillis();
            List<Integer> list = IOHelper.readFileToList(filename);
            System.out.println((new WeiShiTest0324()).getListMedianHelper(list, (long) list.size() / 2, numsCount));

            System.out.println("查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");
        }
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
     * 解题思路：二分法，读取文件，每个数按照二进制高位，进行文件分割，直到内存可以进行读取，并排序
     * 举例：
     * 1、100亿数字，其中位数为100亿个数字排序后的第50亿个数字
     * <p>
     * 2、对100亿个数字进行切割，取高位1个数字，例如分为：40亿=>10mill.dat_0 和 60亿=>10mill.dat_1
     * 二进制高位 0 代表正数 1代表负数 所以 目标中位数在 10mill.dat_1 文件中
     * 偏移量为：50亿-40亿=10亿
     * <p>
     * 3、对10mill.dat_1文件，再取二进制的次高位，进行切割，例如分为：30亿=>10mill.dat_1_0 和 30亿=> 10mill.dat_1_1
     * 可知目标中位数在10mill.dat_1_0 中
     * 偏移量不变
     * <p>
     * 4、对10mill.dat_1_0文件，再取二进制的次高位，进行切割，例如分为：5亿=>10mill.dat_1_0_0 和 25亿=> 10mill.dat_1_0_1
     * 可知目标中位数在10mill.dat_1_0_1 中
     * 偏移量为：10亿-5亿=5亿
     * <p>
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
        List<Integer> list = IOHelper.readFileToList(cutFile);
        return getListMedianHelper(list, offset, numsCount);
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

            BufferedWriter writer0 = IOHelper.getBufferedWriter(cutFile0);

            BufferedWriter writer1 = IOHelper.getBufferedWriter(cutFile1);

            BufferedReader reader = IOHelper.getBufferedReader(cutFile);

            String str;
            int mask = 1 << bit;
            Integer number;
            Integer n;
            while ((str = reader.readLine()) != null) {
                number = Integer.valueOf(str);
                n = (number & mask) != 0 ? 1 : 0;
                if (n == 0) {
                    file0Counts++;
                    writer0.write(number + "\r\n");
                } else {
                    file1Counts++;
                    writer1.write(number + "\r\n");
                }
            }
            reader.close();
            writer0.flush();
            writer0.close();

            writer1.flush();
            writer1.close();

            return new Long[]{file0Counts, file1Counts};

        } catch (IOException e) {
            e.printStackTrace();
            return new Long[]{};
        }
    }

    /**
     * 题目二：100亿的32位整数（1个文件），机器内存500MB，求中位数
     * 解题思路：桶排序，读取文件，每个数取二进制的高8位，进行文件分割，直到内存可以进行读取，并排序
     * 举例：
     * 1、100亿数字，其中位数为100亿个数字排序后的第50亿个数字
     * <p>
     * 2、对100亿个数字进行切割，取高8位，进行分桶，8位二进制，可以表示0～255个桶 文件名：10mill.dat_0,10mill.dat_1 .... 10mill.dat_255
     * 对每个桶进行计数，例如0～127个桶，计数和为：49亿，再加第128个桶总和为51亿，那么中位数必然在第128个桶里。文件名：10mill.dat_128
     * 偏移量为：50亿-49亿（前0～127个桶计数和）= 1亿
     * <p>
     * 3、再对第128个桶，取每个数字的次高8位，再分为0～255个桶
     * 例如0～64个桶，计数和为0.9亿，再加第65个桶总和为1.1亿，那么中位数必然在第65个桶里。文件名：10mill.dat_128_65
     * 偏移量：1亿-0.9亿(前0～64个桶计数和)=0.1亿
     * <p>
     * 4、对第65个桶进行内存排序，取偏移量0.1亿，即为中位数
     *
     * @param filename  文件名
     * @param numsCount 整数的个数
     * @param heapSize  内存限制
     * @return
     */
    public int findMedianByBucket(String filename, Long numsCount, Long heapSize) {
        //待切割文件名
        String cutFile = filename;
        //切割次数
        Integer times = 1;
        //中位数的偏移量
        Long offset = numsCount / 2;
        //桶累加和
        Long currentCount;
        //目标桶
        Integer currentIndex = -1;
        //最大队列大小
        Long maxProcessSize = heapSize / 4;
        //切割完成临时数据
        List<Long> temp;

        //内存足够直接进行处理
        if (numsCount <= maxProcessSize) {
            return findMedianHelperByBucket(cutFile, offset, numsCount, currentIndex);
        }

        //内存不足做切割
        while (true) {
            temp = cutFileByBucket(cutFile, times);
            times++;
            currentCount = 0L;
            for (int i = 0; i < temp.size(); i++) {
                currentCount += temp.get(i);
                if (currentCount >= offset) {
                    cutFile = cutFile + "_" + i;
                    currentIndex = i;
                    //减去前面桶累加的和
                    offset = offset - (currentCount - temp.get(i));
                    break;
                }
            }
            if (currentIndex > -1 && temp.get(currentIndex) <= maxProcessSize) {
                break;
            }

        }
        return findMedianHelperByBucket(cutFile, offset, numsCount, currentIndex);
    }

    private int findMedianHelperByBucket(String cutFile, Long offset, Long numsCount, Integer currentIndex) {
        List<Integer> list = IOHelper.readFileToList(cutFile);
        //偶数情况下,越界再取上一个文件
        if (offset - 1 < 0) {
            addOneBeforeFileNumber(list, getBeforeFileName(cutFile, currentIndex));
        }
        return getListMedianHelper(list, offset, numsCount);
    }

    public int getListMedianHelper(List<Integer> list, Long offset, Long numsCount) {

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

    private void addOneBeforeFileNumber(List<Integer> list, String filename) {
        List<Integer> temp = IOHelper.readFileToList(filename);
        list.add(temp.get(temp.size() - 1));
    }

    private String getBeforeFileName(String cutFile, Integer currentIndex) {
        String t = cutFile.substring(0, cutFile.lastIndexOf("_"));
        return t + "_" + (currentIndex - 1);
    }

    public List<Long> cutFileByBucket(String cutFile, Integer times) {

        //初始化桶计数数组
        List<Long> counts = createBucketList();
        //初始化桶切割文件
        Map<Integer, BufferedWriter> writerMap = createBucketFile(cutFile);
        //初始化桶二进制位
        int bit = 32 - 8 * times;

        try {
            BufferedReader reader = IOHelper.getBufferedReader(cutFile);
            String str;
            Integer number;
            Integer n;
            while ((str = reader.readLine()) != null) {
                number = Integer.valueOf(str);
                //取高8位，进行分桶
                n = (number >> bit) & 0xff;
                //写入新文件
                writerMap.get(n).write(number + "\r\n");
                //累加桶里的数字个数
                counts.set(n, counts.get(n) + 1);
            }
            //关闭文件流
            reader.close();
            closeBucketFile(writerMap);
            return counts;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Long> createBucketList() {
        List<Long> counts = new ArrayList<>(256);
        for (int i = 0; i < 256; i++) {
            counts.add(0L);
        }
        return counts;
    }

    private Map<Integer, BufferedWriter> createBucketFile(String cutFile) {

        try {
            Map<Integer, BufferedWriter> writerMap = new HashMap<>();
            for (int i = 0; i < 256; i++) {
                writerMap.put(i, IOHelper.getBufferedWriter(cutFile + "_" + i));
            }
            return writerMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeBucketFile(Map<Integer, BufferedWriter> writerMap) {

        for (Integer index : writerMap.keySet()) {
            try {
                writerMap.get(index).flush();
                writerMap.get(index).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
