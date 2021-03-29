package com.leetbook.test.weishi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
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
        Long numsCount = 10000000L;//1亿
        Long heapSize = 5 * 1024 * 1024L;//5MB大小的内存,转换为字节byte（注：1个整数占4个byte）

        Long start = System.currentTimeMillis();
        IOHelper.buildRandomFile(filename, numsCount);
        System.out.println("构造文件耗时:" + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println((new BinaryMedian(filename, numsCount, heapSize)).find());
        System.out.println("二分切割文件,查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");

//        start = System.currentTimeMillis();
//        System.out.println((new BucketMedian(filename, numsCount, heapSize)).find());
//        System.out.println("桶排序切割文件,查找中位数耗时:" + (System.currentTimeMillis() - start) + " ms");

        //1000万以下进行验证
        if (numsCount <= 10000000L) {
            start = System.currentTimeMillis();
            List<Integer> list = IOHelper.readFileToList(filename);
            int ret;
            Collections.sort(list);
            if (numsCount % 2 == 0) {
                ret = (int) (((long) list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2);
            } else {
                ret = list.get(list.size() / 2);
            }
            System.out.println(ret);
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


    public static class BinaryMedian {

        private BinarySource binarySource;

        private BinaryOut binaryOut0;

        private BinaryOut binaryOut1;

        private Long numsCount;

        private Long offset;

        private Long maxProcessSize;

        private Integer bit = 31;

        private Integer mask = 1 << 31;

        private boolean unsign = false;//是否处理了负数文件

        public BinaryMedian(String filename, Long numsCount, Long heapSize) {

            this.binarySource = new BinarySource(filename);
            this.binaryOut0 = buildBinaryOut(this.binarySource, 0);
            this.binaryOut1 = buildBinaryOut(this.binarySource, 1);
            this.numsCount = numsCount;
            this.offset = numsCount / 2;
            this.maxProcessSize = heapSize / 4;

        }

        private BinaryOut buildBinaryOut(BinarySource binarySource, Integer number) {

            return new BinaryOut(binarySource.getFilename() + "_" + number);
        }

        private BinaryOut switchOut(Integer number) {
            int n = (number & this.mask) != 0 ? 1 : 0;
            if (n == 0) {
                return this.binaryOut0;
            }
            return this.binaryOut1;
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
         */
        public int find() {

            if (this.numsCount <= this.maxProcessSize) {
                return findMedianHelper();
            }
            while (true) {

                try {
                    //首次处理把负数处理为正数
                    if (bit == 31) {
                        while (this.binarySource.hasNext()) {
                            Integer number = this.binarySource.next();
                            switchOut(number).write(Math.abs(number));
                        }
                    } else {
                        while (this.binarySource.hasNext()) {
                            Integer number = this.binarySource.next();
                            switchOut(number).write(number);
                        }
                    }
                    this.binarySource.close();
                    this.binaryOut0.close();
                    this.binaryOut1.close();
                    if (this.offset > this.binaryOut0.getCounts()) {
                        this.offset = this.offset - this.binaryOut0.getCounts();
                        this.binarySource = new BinarySource(this.binaryOut1.getFilename());
                    } else {
                        //首次切割完,专门处理负数
                        if (this.bit == 31) {
                            this.offset = this.offset - this.binaryOut1.getCounts();
                        }
                        this.binarySource = new BinarySource(this.binaryOut0.getFilename());
                    }
                    //第一次完成后,使用正数还是负数文件
                    if (this.binarySource.getFilename().equals(this.binaryOut1.getFilename())) {
                        this.unsign = true;
                    }
                    if (this.binaryOut0.getCounts() > 0 && this.binaryOut0.getCounts() < this.maxProcessSize) {
                        break;
                    }
                    if (this.binaryOut1.getCounts() > 0 && this.binaryOut1.getCounts() < this.maxProcessSize) {
                        break;
                    }
                    //
                    this.binaryOut0 = buildBinaryOut(this.binarySource, 0);
                    this.binaryOut1 = buildBinaryOut(this.binarySource, 1);
                    this.maskReset();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                this.binarySource.close();
                return findMedianHelper();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

        private void maskReset() {
            this.bit--;
            this.mask = 1 << bit;
        }

        private Integer findMedianHelper() {
            List<Integer> list = IOHelper.readFileToList(binarySource.getFilename());
            Collections.sort(list);
            if (numsCount % 2 == 0) {
                if (this.unsign) {
                    return -(int) (((long) list.get(offset.intValue()) + list.get(offset.intValue() - 1)) / 2);
                }
                return (int) (((long) list.get(offset.intValue()) + list.get(offset.intValue() - 1)) / 2);
            }
            if (this.unsign) {
                return -list.get(offset.intValue());
            }
            return list.get(offset.intValue());
        }
    }

    public static class BucketMedian {

        private BinarySource binarySource;

        private Map<Integer, BinaryOut> binaryOutMap = new LinkedHashMap<>(256);

        private Long numsCount;

        private Long offset;

        private Long maxProcessSize;

        private Integer bit = 24;

        public BucketMedian(String filename, Long numsCount, Long heapSize) {

            this.binarySource = new BinarySource(filename);
            this.buildBinaryOut();
            this.numsCount = numsCount;
            this.offset = numsCount / 2;
            this.maxProcessSize = heapSize / 4;
        }

        private void buildBinaryOut() {
            for (int i = 0; i < 256; i++) {
                binaryOutMap.put(i, new BinaryOut(this.binarySource.getFilename() + "_" + i));
            }
        }

        private void closeBinaryOut() throws IOException {

            for (Integer n : this.binaryOutMap.keySet()) {
                this.binaryOutMap.get(n).close();
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
         */
        public int find() {

            //内存足够
            if (this.numsCount < this.maxProcessSize) {
                return findMedianHelper();
            }
            //内存不足
            while (true) {

                try {
                    while (this.binarySource.hasNext()) {
                        Integer number = this.binarySource.next();
                        switchOut(number).write(number);
                    }
                    this.closeBinaryOut();
                    Long totalCounts = 0L;
                    Integer currentIndex = -1;
                    for (Integer n : this.binaryOutMap.keySet()) {
                        totalCounts += this.binaryOutMap.get(n).getCounts();
                        if (totalCounts >= this.offset) {
                            this.binarySource = new BinarySource(this.binaryOutMap.get(n).getFilename());
                            //减去前面桶累加的和
                            this.offset = this.offset - (totalCounts - this.binaryOutMap.get(n).getCounts());
                            currentIndex = n;
                            break;
                        }
                    }

                    if (currentIndex > -1 && this.binaryOutMap.get(currentIndex).getCounts() <= this.maxProcessSize) {
                        break;
                    }
                    this.buildBinaryOut();
                    this.bitReset();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                this.binarySource.close();
                return findMedianHelper();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

        private BinaryOut switchOut(Integer number) {
            int n = (number >> this.bit) & 0xff;
            return this.binaryOutMap.get(n);
        }

        private void bitReset() {
            this.bit = this.bit - 8;
        }

        private int findMedianHelper() {

            List<Integer> list = IOHelper.readFileToList(this.binarySource.getFilename());
            //偶数情况下,越界再取上一个文件
            if (this.offset - 1 < 0) {
                addOneBeforeFileNumber(list, getBeforeFileName(this.binarySource.getFilename()));
            }
            Collections.sort(list);
            if (this.numsCount % 2 == 0) {
                return (int) (((long) list.get(this.offset.intValue()) + list.get(this.offset.intValue() - 1)) / 2);
            }
            return list.get(this.offset.intValue());
        }

        private void addOneBeforeFileNumber(List<Integer> list, String filename) {
            List<Integer> temp = IOHelper.readFileToList(filename);
            list.add(temp.get(temp.size() - 1));
        }

        private String getBeforeFileName(String filename) {

            String t1 = filename.substring(0, filename.lastIndexOf("_"));
            String t2 = filename.substring(filename.lastIndexOf("_"), filename.length());
            Integer index = Integer.valueOf(t2);

            return t1 + "_" + (index - 1);
        }
    }
}
