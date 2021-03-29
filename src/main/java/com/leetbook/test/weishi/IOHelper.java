package com.leetbook.test.weishi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/27 11:57
 * @Description:
 */
public class IOHelper {


    public static BufferedReader getBufferedReader(String filename) throws IOException {
        return new BufferedReader(new FileReader(filename));
    }

    public static BufferedWriter getBufferedWriter(String filename) throws IOException {

        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile(); // 创建新文件
        }
        return new BufferedWriter(new FileWriter(file));
    }

    /**
     * 按行读取文件到数组
     *
     * @param filename
     * @return
     */
    public static List<Integer> readFileToList(String filename) {
        try {
            List<Integer> res = new ArrayList<>();
            BufferedReader reader = getBufferedReader(filename);
            String str;
            while ((str = reader.readLine()) != null) {
                res.add(Integer.valueOf(str));
            }
            reader.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 写入numsCount个随机数整数到文件
     */
    public static void buildRandomFile(String filename, Long numsCount) {

        try {

            BufferedWriter writer = getBufferedWriter(filename);
            Random random = new Random();
            for (Long i = 0L; i < numsCount; i++) {
                writer.write(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE,Integer.MAX_VALUE) + "\r\n"); // \r\n即为换行
                //writer.write((i+1)+"\r\n"); // \r\n即为换行
            }
            writer.flush(); // 把缓存区内容压入文件
            writer.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildRandomIpFile(String filename, Long numsCount) {

        try {
            /* 写入Txt文件 */
            File writename = new File(filename); // 相对路径，如果没有则要建立一个新的output。txt文件
            if (writename.exists()) {
                System.out.println("文件已存在,忽略构造");
                return;
            }
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            for (Long i = 0L; i < numsCount; i++) {
                out.write(getRandomIp() + "\r\n"); // \r\n即为换行
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRandomIp() {

        // ip范围
        int[][] range = {
                {607649792, 608174079}, // 36.56.0.0-36.63.255.255
                {1038614528, 1039007743}, // 61.232.0.0-61.237.255.255
                {1783627776, 1784676351}, // 106.80.0.0-106.95.255.255
                {2035023872, 2035154943}, // 121.76.0.0-121.77.255.255
                {2078801920, 2079064063}, // 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497}, // 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785}, // 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137}, // 182.80.0.0-182.92.255.255
                {-770113536, -768606209}, // 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random random = new Random();
        int index = random.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /*
     * 将十进制转换成IP地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String ipStr = "";
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        ipStr = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return ipStr;
    }
}
