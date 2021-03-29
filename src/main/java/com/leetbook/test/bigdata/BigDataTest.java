package com.leetbook.test.bigdata;

import com.leetbook.test.weishi.BinaryOut;
import com.leetbook.test.weishi.BinarySource;

import java.io.IOException;
import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/29 11:07
 * @Description:
 */
public class BigDataTest {

    public static class IpBin implements Comparable<IpBin> {
        private String ip;
        private int counts = 0;

        public IpBin(String ip, int counts) {
            this.ip = ip;
            this.counts = counts;
        }

        @Override
        public int compareTo(IpBin o) {
            return this.getCounts() - o.getCounts();
        }

        public int getCounts() {
            return counts;
        }
    }

    public static class IpTop {

        private Map<Integer, BinarySource> binarySourceMap = new LinkedHashMap<>();

        private Map<Integer, BinaryOut> binaryOutMap = new LinkedHashMap<>();

        private Integer maxProcessSize;

        private Integer top;

        public IpTop(String filename, Long ipCounts, Long heapSize, Integer top) {
            this.binarySourceMap.put(0, new BinarySource(filename));
            //对象头（8 字节）+ 引用 (4 字节 )  + char 数组（16 字节）+ 1个 int（4字节）+ 1个long（8字节）= 40 字节。
            //String占用内存计算公式：40 + 2*n，n为字符串长度。
            Long size = 40 + 2 * 15L;
            Long totalMem = ipCounts * size;//总内存大小
            this.maxProcessSize = (int) (totalMem / heapSize);//总文件个数
            this.buildBinaryOut();
            this.top = top;
        }

        private void buildBinaryOut() {
            for (int i = 0; i < maxProcessSize; i++) {
                binaryOutMap.put(i, new BinaryOut(this.binarySourceMap.get(0).getFilename() + "_" + i));
            }
        }

        private BinaryOut switchOut(String ip) {
            int index = Math.abs(ip.hashCode()) % this.maxProcessSize;
            return binaryOutMap.get(index);
        }

        /**
         * 题目一：海量日志数据，提取出某日访问次数最多的那个IP
         * 1、按照内存大小，切割为n个小文件
         * 2、每个小文件，做频次统计，再做top汇总
         *
         * @return
         */
        public List<IpBin> find() {

            try {
                //大文件分割为小文件
                BinarySource binarySource = this.binarySourceMap.get(0);
                while (binarySource.hasNext()) {
                    String ip = this.binarySourceMap.get(0).nextString();
                    switchOut(ip).writeString(ip);
                }
                binarySource.close();
                this.closeBinaryOut();

                //把小文件做为输入
                for (int i = 0; i < this.maxProcessSize; i++) {
                    this.binarySourceMap.put(i, new BinarySource(this.binaryOutMap.get(i).getFilename()));
                }
                //统计频次 && 计算top
                PriorityQueue<IpBin> queue = new PriorityQueue<>();
                for (int i = 0; i < maxProcessSize; i++) {
                    Map<String, Integer> map = new HashMap<>();

                    binarySource = this.binarySourceMap.get(i);
                    while (binarySource.hasNext()) {
                        String ip = this.binarySourceMap.get(i).nextString();
                        map.put(ip, map.getOrDefault(ip, 0) + 1);
                    }
                    binarySource.close();

                    for (String ip : map.keySet()) {
                        queue.add(new IpBin(ip, map.get(ip)));
                        if (queue.size() > this.top) {
                            queue.poll();
                        }
                    }

                }
                return new ArrayList<>(queue);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void closeBinaryOut() throws IOException {

            for (Integer n : this.binaryOutMap.keySet()) {
                this.binaryOutMap.get(n).close();
            }
        }
    }
}
