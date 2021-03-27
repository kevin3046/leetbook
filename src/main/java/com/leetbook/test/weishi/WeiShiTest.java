package com.leetbook.test.weishi;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/24 16:15
 * @Description: 题目一：求2的1000次方
 * 题目二：100亿的32位整数（1个文件），机器内存500MB，求中位数
 */
public class WeiShiTest {

    //1、2的1000次方
    public String test1() {
        return new BigDecimal(Math.pow(2.0, 1000.0)).toString();
    }

    public String test2() {
        double res = 1;
        for (int i = 0; i < 1000; i++) {
            res = res * 2.0;
        }
        return new BigDecimal(res).toString();
    }

    public String test3() {

        BigDecimal res = new BigDecimal(1.0);
        BigDecimal a = new BigDecimal(2.0);

        //1、循环干掉O（n）-> O(1) => 数学公式
        for (int i = 0; i < 1000; i++) {
            res = res.multiply(a);
        }

        return res.toString();

    }

    /**
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

    public BigDecimal quickMul(BigDecimal x, long N) {
        if (N == 0) {
            return new BigDecimal(1.0);
        }
        BigDecimal y = quickMul(x, N / 2);
        return N % 2 == 0 ? y.multiply(y) : y.multiply(y).multiply(x);
    }

    /**
     * 十进制转二进制格式化
     *
     * @param number
     * @param groupSize
     * @return
     */
    public static String intToString(int number, int groupSize) {
        StringBuilder result = new StringBuilder();

        for (int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            int t2 = i << 1;
            int t3 = i >> 1;
            result.append((number & mask) != 0 ? "1" : "0");

            if (i % groupSize == 0) {
                result.append(" ");
            }
        }
        result.replace(result.length() - 1, result.length(), "");

        return result.toString();
    }

    /**
     * 十进制转二进制,不格式化
     *
     * @param de
     * @return
     */
    public static String Decimal2Binary(int de) {
        String numstr = "";
        while (de > 0) {
            int res = de % 2; //除2 取余数作为二进制数
            numstr = res + numstr;
            de = de / 2;
        }
        return numstr;
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
            in.close();
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

    /**
     * https://zhuanlan.zhihu.com/p/75397875
     * 思路二：堆排序（转换为求前5G大的元素）
     * 我们知道利用堆排序处理海量数据的topK是非常合适不过了，因为它不用将所有的元素都进行排序，只需要比较和根节点的大小关系就可以了，同时也不需要一次性将所有的数据都加载到内存；对于海量数据而言，要求前k小/大的数，我们只需要构建一个k个大小的堆，然后将读入的数依次和根节点比较就行了（当然这里的前提是内存需要存的下k个数）
     * <p>
     * 最大堆求前n小，最小堆求前n大。
     * <p>
     * 1、前k小：构建一个k个数的最大堆，当读取的数大于根节点时，舍弃；当读取的数小于根节点时，替换根节点，重新塑造最大堆，然后继续读取，最后读取完所有的数据之后，最大堆中的数就是最小k个数
     * <p>
     * 2、前k大：构建一个k个数的最小堆，当读取的数小于根节点时舍弃；当读取的数大于根节点时，替换根节点，重新塑造最小堆，然后继续读取，读取完所有的数据之后，最小堆中的数就是最大k个数
     * <p>
     * 所以我们本题采用堆排序来求中位数
     * <p>
     * 对于10G的数据，它的中位数就是第5G个元素，按常理来说我们需要构建一个5G大小的堆，但是允许的内存只有两个G，所以我们先构建一个1G大小的大顶堆，然后求出第1G个元素（根节点），然后利用该元素构建一个新的1G大小的堆，求出第2G大的元素，依次类推，求出第5G大的元素
     * <p>
     * 每次构建一个堆求第几G大的元素，都需要重新遍历完所有10G的数据，相当于要遍历5 * 10G次，这需要频繁的IO操作，需要不断的从硬盘中读取数据
     *
     * @param list
     * @return
     */
    public static int findMedian(List<Integer> list) {
        int len = list.size();//100个大小
        int mid = len / 2;//中位数的位置
        PriorityQueue<Integer> queue = new PriorityQueue();
        int queueSize = 10;//优先队列的大小位：10个大小（模拟这个是限制内存大小）
        int times = mid / queueSize;//总共求多少次，可以求到中位数
        int num = Integer.MAX_VALUE;
        while (true) {
            for (int i = 0; i < len; i++) {
                if (list.get(i) < num) {
                    queue.add(list.get(i));
                }
                if (queue.size() > queueSize) {
                    queue.poll();
                }
            }
            num = queue.peek();
            times--;
            if (times == 0) {
                break;
            } else {
                queue.clear();
            }
        }
        return num;
    }


    /**
     * 海量数据求均值方法一
     * 参考思路：https://blog.csdn.net/qq_36770641/article/details/81395979
     *
     * @param filename  文件名
     * @param numsCount 整数的个数
     * @param heapSize  内存限制
     * @return
     */
    public int findMedian2(String filename, Long numsCount, Long heapSize) {

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
            return findMedian2Helper(cutFile, mid, numsCount);
        }

        //内存不足,开始切割文件,进行处理
        while (true) {
            temp = cutFile2(cutFile, cutFile0, cutFile1, cutTimes);
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

        return findMedian2Helper(cutFile, offset, numsCount);
    }

    /**
     * 内存排序辅助类
     *
     * @param cutFile
     * @param offset
     * @param numsCount
     * @return
     */
    private Integer findMedian2Helper(String cutFile, Long offset, Long numsCount) {
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
    private Long[] cutFile2(String cutFile, String cutFile0, String cutFile1, Integer cutTimes) {

        Long file0Counts = 0L;//0号文件计数
        Long file1Counts = 0L;//1号文件计数
        Integer bit = 32 - cutTimes;//切割文件高位掩码计算值,每次切割之后减少1位

        try {
            File file0 = new File(cutFile0); // 相对路径，如果没有则要建立一个新的output。txt文件
            file0.createNewFile(); // 创建新文件
            BufferedWriter out0 = new BufferedWriter(new FileWriter(file0));

            File file1 = new File(cutFile1); // 相对路径，如果没有则要建立一个新的output。txt文件
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
            in.close();
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
     * 桶排序
     * 参考文章：https://zhuanlan.zhihu.com/p/75397875
     * 参考文章桶排序：https://www.cnblogs.com/zer0Black/p/6169858.html#3
     *
     * @param filename
     * @param numsCount
     * @param heapSize
     * @return
     */
    public int findMedian3(String filename, Long numsCount, Long heapSize) {

        List<Long> res;
        String cutFile = filename;
        Integer times = 1;
        Long mid = numsCount / 2;
        Long offset = mid;
        Long currentCount;
        Integer currentIndex = -1;
        Long maxProcessSize = heapSize / 4;
        while (true) {
            res = cutFile3(cutFile, times);
            times++;
            currentCount = 0L;
            for (int i = 0; i < res.size(); i++) {
                currentCount += res.get(i);
                if (currentCount >= offset) {
                    cutFile = cutFile + "_" + i;
                    currentIndex = i;
                    //减去前面桶累加的和
                    offset = offset - (currentCount - res.get(i));
                    break;
                }
            }
            if (currentIndex > -1 && res.get(currentIndex) <= maxProcessSize) {
                break;
            }

        }
        //堆排序 测试结果未减少时间
//        PriorityQueue<Integer> queue = new PriorityQueue();
//        offset = offset-1;
//
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(cutFile));
//            String str;
//            while ((str = in.readLine()) != null) {
//                queue.add(Integer.valueOf(str));
//                if(queue.size()>offset){
//                    queue.poll();
//                }
//            }
//            in.close();
//            if(numsCount %2 == 0){
//                Long temp = (long)queue.poll() + queue.poll();
//                return (int)(temp/2);
//            }else{
//                queue.poll();
//                return queue.peek();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return 0;
//        }
        return findMedian3Helper(cutFile, offset, numsCount, currentIndex);

    }

    public int findMedian3Helper(String cutFile, Long offset, Long numsCount, Integer currentIndex) {
        List<Integer> list = readFile(cutFile);
        if (offset - 1 < 0) {
            String t = cutFile.substring(0, cutFile.lastIndexOf("_"));
            t += "_" + (currentIndex - 1);
            List<Integer> temp = readFile(t);
            list.add(temp.get(temp.size() - 1));
        }
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

    public List<Long> cutFile3(String cutFile, Integer times) {
        //times 24,16,8,0
        List<Long> counts = new ArrayList<>(256);
        Map<Integer, File> fileMap = new HashMap<>();
        Map<File, BufferedWriter> writerMap = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            createFile(i, cutFile + "_" + i, fileMap, writerMap);
            counts.add(0L);
        }
        int bit = 32 - 8 * times;
        try {
            BufferedReader in = new BufferedReader(new FileReader(cutFile));
            String str;
            Integer number;
            Integer n;
            while ((str = in.readLine()) != null) {
                number = Integer.valueOf(str);
                n = (number >> bit) & 0xff;
                writerMap.get(fileMap.get(n)).write(number + "\r\n");
                counts.set(n, counts.get(n) + 1);
            }
            //关闭文件流
            in.close();
            closeFile(fileMap, writerMap);
            return counts;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void createFile(Integer index, String filename, Map<Integer, File> fileMap, Map<File, BufferedWriter> writerMap) {
        if (fileMap.containsKey(index)) {
            return;
        }
        try {
            File file = new File(filename); //
            file.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            fileMap.put(index, file);
            writerMap.put(file, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeFile(Map<Integer, File> fileMap, Map<File, BufferedWriter> writerMap) {

        for (Integer index : fileMap.keySet()) {
            try {
                writerMap.get(fileMap.get(index)).flush();
                writerMap.get(fileMap.get(index)).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //测试第一版代码
    //filename:/tmp/101mill_output.txt
//    public int findMid(String filename){
//
//        String currentFile = filename;
//        String calFile = "";
//        int fileStart = 1;
//        int index = 0;
//        int flag = 2;
//        while (true){
//            List<Integer> res = cutFile(currentFile,fileStart);
//            bit--;
//
//            int file0Start = res.get(0);
//            int file0End = res.get(1);
//
//            int file1Start = res.get(2);
//            int file1End = res.get(3);
//
//            if((file0End - file0Start) > 0 && (file0End - file0Start)<=maxProcessSize && mid>= file0Start && mid<=file0End){
//                calFile = currentFile+"_0";
//                index = mid - file0Start + flag;
//                break;
//            }else if((file1End - file1Start) >0 && (file1End - file1Start)<=maxProcessSize && mid>= file1Start && mid<=file1End){
//                calFile = currentFile+"_1";
//                index = mid - file1Start + flag;
//                break;
//            }
//
//            if(mid>= file0Start && mid<=file0End){
//                currentFile = currentFile+"_0";
//                if(file0Start!=fileStart){
//                    flag++;
//                }
//                fileStart = file0Start;
//                continue;
//            }
//
//            if(mid>= file1Start && mid<=file1End){
//                currentFile = currentFile+"_1";
//                if(file1Start!=fileStart){
//                    flag++;
//                }
//                fileStart = file1Start;
//                continue;
//            }
//
//
//
//        }
//        List<Integer> list = readFile(calFile);
//
//        list.sort(((o1, o2) -> o1.compareTo(o2)));
//
//        System.out.println(list.get(index));
//
//
//        List<Integer> list2 = readFile(filename);
//
//        list2.sort(((o1, o2) -> o1.compareTo(o2)));
//
//        int index2 = list2.size()/2;
//        System.out.println(list2.get(index2));
//
//        return 0;
//    }
//    private List<Integer> cutFile(String filename,int fileStart){
//
//        int file0Counts = 0;
//        int file1Counts = 0;
//
//
//
//        try {
//            File file0 = new File(filename+"_0"); // 相对路径，如果没有则要建立一个新的output。txt文件
//            file0.createNewFile(); // 创建新文件
//            BufferedWriter out0 = new BufferedWriter(new FileWriter(file0));
//
//            File file1 = new File(filename+"_1"); // 相对路径，如果没有则要建立一个新的output。txt文件
//            file1.createNewFile(); // 创建新文件
//            BufferedWriter out1 = new BufferedWriter(new FileWriter(file1));
//
//
//            BufferedReader in = new BufferedReader(new FileReader(filename));
//            String str;
//            int mask = 1 << bit;
//            while ((str = in.readLine()) != null) {
//                Integer number = Integer.valueOf(str);
//                Integer index = (number & mask) != 0 ? 1 : 0;
//                if(index == 0){
//                    file0Counts++;
//                    out0.write(number+"\r\n");
//                }else{
//                    file1Counts++;
//                    out1.write(number+"\r\n");
//                }
//            }
//            out0.flush();
//            out0.close();
//
//            out1.flush();
//            out1.close();
//
//            int file0Start = fileStart;
//            int file0End = file0Start+file0Counts;
//
//            int file1Start = file0End+1;
//            int file1End = file1Start+file1Counts;
//
//            return Arrays.asList(file0Start,file0End,file1Start,file1End);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
