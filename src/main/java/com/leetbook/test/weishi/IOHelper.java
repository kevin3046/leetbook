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
}
