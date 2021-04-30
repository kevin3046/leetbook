package com.leetbook.test.javatest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/28 13:21
 * @Description:
 * java MappedByteBuffer 测试
 * 参考：https://www.jianshu.com/p/f90866dcbffc
 */
public class MappedByteBufferTest {

    public static void main(String[] args) {
        File file = new File("/tmp/1.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }

            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scan.hasNext()) {
                System.out.print(scan.next() + " ");
            }


        } catch (IOException e) {
        }
    }

    public int coinChange(int[] coins, int amount) {

        int max = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i],dp[i- coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


}
