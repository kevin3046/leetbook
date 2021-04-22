package com.leetbook.test.mq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/22 15:11
 * @Description:
 */
public class MqTestSolution {

    public static void main(String[] args) {

        System.out.println(allocate("A01", Arrays.asList("01", "02", "03", "04"), Arrays.asList("A01", "A01")));

        System.out.println(allocate("A01", Arrays.asList("01", "02", "03", "04", "05"), Arrays.asList("A01", "A02")));

        System.out.println(allocate("A02", Arrays.asList("01", "02", "03", "04", "05"), Arrays.asList("A01", "A02")));

    }

    /**
     * rocketmq consumer端分配MessageQueue的算法
     *
     * @param currentCID
     * @param mqAll
     * @param cidAll
     * @return
     */
    public static List<String> allocate(String currentCID, List<String> mqAll, List<String> cidAll) {
        if (currentCID == null || currentCID.length() < 1) {
            throw new IllegalArgumentException("currentCID is empty");
        }
        if (mqAll == null || mqAll.isEmpty()) {
            throw new IllegalArgumentException("mqAll is null or mqAll empty");
        }
        if (cidAll == null || cidAll.isEmpty()) {
            throw new IllegalArgumentException("cidAll is null or cidAll empty");
        }

        List<String> result = new ArrayList<String>();

        // 当前clientId所在的下标
        int index = cidAll.indexOf(currentCID);
        int mod = mqAll.size() % cidAll.size();
        int averageSize =
                mqAll.size() <= cidAll.size() ? 1 : (mod > 0 && index < mod ? mqAll.size() / cidAll.size()
                        + 1 : mqAll.size() / cidAll.size());
        int startIndex = (mod > 0 && index < mod) ? index * averageSize : index * averageSize + mod;
        int range = Math.min(averageSize, mqAll.size() - startIndex);
        for (int i = 0; i < range; i++) {
            result.add(mqAll.get((startIndex + i) % mqAll.size()));
        }
        return result;
    }
}
